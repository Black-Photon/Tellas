import java.nio.file.FileSystems

import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.{Cube, GLWrapper, KeyListener, Shape, SkyBox}
import src.block.{Air, Dirt, Grass, Model}
import src.util.{Vector3F, Vector3I}
import src.{ChunkLoader, Data, Image}

object Main extends App {
  type Texture = Int

  // Loads the C++
  System.load(
    FileSystems.getDefault
      .getPath("cmake-build-debug/libOpenGLProject.so") // Dynamic link ../../../
      .normalize.toAbsolutePath.toString)

  GLWrapper.test()

  message("Pre-Initialisation")
  GLWrapper.preInit(Data.width, Data.height, "Tellas")
  message("Initialisation")
  GLWrapper.init(true)

  addBlocks()

  Data.player.setPosition(Vector3F(3, -2, 0))

  Data.blocks(Air.ID) = Air
  Data.blocks(Dirt.ID) = Dirt
  Data.blocks(Grass.ID) = Grass

  var time = 0.0f

  val crosshair: Image = new Image("crosshair.png", true)
  val sun: Texture = GLWrapper.generateTexture("sun.png", false)

  var lastTime = 0.0f
  var iterations = 0

  message("Drawing")
  while(!GLWrapper.shouldClose) {
    val deltaT = GLWrapper.deltaT
    KeyListener.processInput(deltaT)
    GLWrapper.prerender(0.2f, 0.2f, 0.7f)

    Data.player.frame(deltaT)

    time += deltaT
    iterations += 1
    if((time - deltaT).floor < time.floor) {
      println("FPS currently at " + (iterations / (time - lastTime)).toInt)
      lastTime = time
      iterations = 0
    }

    draw(time)

    if (Data.player.getPosition.y < -50) Data.player.setPosition(Vector3F(3, -2, 0))

    GLWrapper.postrender()
    GLWrapper.swapBuffers()
    GLWrapper.pollEvents()
    GLWrapper.checkError()
  }
  message("Closing")
  GLWrapper.close()

  def addBlocks(): Unit = {
    for(x <- -20 to 20; z <- -20 to 20) {
      new Grass(Vector3I(x, -2, z))
    }
    for(x <- -20 to 20; y <- -20 to -3; z <- -20 to 20) {
      new Dirt(Vector3I(x, y, z))
    }
  }

  def draw(time: Float): Unit = {
    val angle = (time * 25) % 360

    GLWrapper.useTexture(sun, 0)
    SkyBox.bindBuffer()
    SkyBox.activateShader()
    SkyBox.draw(Data.player.getPosition, angle, 15)

    Cube.activateShader(angle)
    drawBlocks()

    crosshair.draw(1000, 500)
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }


  /**
    * Draws all the blocks of this type
    */
  def drawBlocks(): Unit = {
    Shape.bindBuffer(Model.CUBE)
    for (cx <- -2 to 2; cy <- -2 to 2; cz <- -2 to 2) {
      val chunk = ChunkLoader.getChunk(Vector3I(cx * 16, cy * 16, cz * 16))
      for (x <- 0 to 15; y <- 0 to 15; z <- 0 to 15) {
        val block = chunk.getBlock(Vector3I(x, y, z)) // More Slow
        if (block.ID != 0) {
          val position = Vector3I(16 * cx + x, 16 * cy + y, 16 * cz + z)

          val playerDir: Vector3F = Data.player.lookDirection
          playerDir.normalize()
          val objectDir: Vector3F = position.toFloat - (Data.player.getPosition - playerDir*5)
          objectDir.normalize()
          val angle = playerDir :* objectDir
          if(angle > 0.5 || position.toFloat == Data.player.getPosition) {
            for ((texture, list) <- block.textures) {
              GLWrapper.useTexture(texture, 0) // Slow
              for (side <- list) {
                if (!ChunkLoader.isBlock(shiftPos(position, side)))
                  drawFace(position, side)
              }
            }
          }
        }
      }
    }

    /**
      * Draws an individual block
      * @param position Location to draw at
      */
    def drawFace(position: Vector3I, face: Side): Unit = {
      Cube.drawFace(position, face)
    }

    def shiftPos(pos: Vector3I, side: Side): Vector3I = {
      side match {
        case FRONT => pos + Vector3I(0, 0, -1)
        case BACK => pos + Vector3I(0, 0, 1)
        case LEFT => pos + Vector3I(-1, 0, 0)
        case RIGHT => pos + Vector3I(1, 0, 0)
        case BOTTOM => pos + Vector3I(0, -1, 0)
        case TOP => pos + Vector3I(0, 1, 0)
      }
    }
  }
}

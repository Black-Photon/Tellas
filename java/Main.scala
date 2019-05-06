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
    val cubeDims = 64
    for(x <- -cubeDims to cubeDims; z <- -cubeDims to cubeDims) {
      new Grass(Vector3I(x, -2, z), false)

    }
    for(x <- -cubeDims to cubeDims; y <- -cubeDims to -3; z <- -cubeDims to cubeDims) {
      new Dirt(Vector3I(x, y, z), false)
    }
  }

  def draw(time: Float): Unit = {
    val angle = 0//time % 360

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
    val chunkDepth = 3
    val playerChunk = Data.player.getPosition / 16 floor

    for (cx <- -chunkDepth + playerChunk.x to chunkDepth + playerChunk.x; cy <- -chunkDepth + playerChunk.y to chunkDepth + playerChunk.y; cz <- -chunkDepth + playerChunk.z to chunkDepth + playerChunk.z) {
      // Checks if the chunk is viewable
      val playerDir: Vector3F = Data.player.lookDirection
      playerDir.normalize()
      var cAngle = -1.0f
      val corners = List(0, 4, 8, 12, 16)
      for(xMod <- corners; yMod <- corners; zMod <- corners) {
        val chunkDir: Vector3F = Vector3F(cx * 16 + xMod, cy * 16 + yMod, cz * 16 + zMod) - (Data.player.getPosition - playerDir * 5)
        chunkDir.normalize()
        cAngle = Math.max(playerDir :* chunkDir, cAngle)
      }

      if(cAngle > 0.6) {
        val chunk = ChunkLoader.getChunk(Vector3I(cx * 16, cy * 16, cz * 16))

        if(!chunk.isLoaded) {
          chunk.updateVisible()
          chunk.isLoaded = true
        }


        for(block <- chunk.visibleBlocks) {
          val x = block.position.x
          val y = block.position.y
          val z = block.position.z
          ChunkLoader.getBlock(Vector3I(x, y, z)) match {
            case None => Unit
            case Some(block) =>
              val position = block.position

              // Checks if the block is viewable
              val playerDir: Vector3F = Data.player.lookDirection
              playerDir.normalize()
              val objectDir: Vector3F = position.toFloat - (Data.player.getPosition - playerDir * 5)
              objectDir.normalize()
              val angle = playerDir :* objectDir
              if (angle > 0.5 || position.toFloat == Data.player.getPosition) {
                for ((texture, list) <- block.self.textures) {
                  GLWrapper.useTexture(texture, 0)
                  for (side <- list) {
                    if (!block.isSide(side))
                      drawFace(position, side)
                  }
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

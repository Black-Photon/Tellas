import java.nio.file.FileSystems

import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.{Cube, GLWrapper, KeyListener, Shape, SkyBox}
import src.block.{Air, Dirt, Grass, Model}
import src.util.{Vector3F, Vector3I}
import src.{Chunk, ChunkLoader, Data, Image}

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

    if (Data.player.getPosition.y < -100) Data.player.setPosition(Vector3F(3, -2, 0))

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
    val chunkDepth = 8
    val playerChunk = Data.player.getPosition / 16 floor
    val playerDir: Vector3F = Data.player.lookDirection

    def findAngleToPlayer(position: Vector3F): Float = {
      val chunkDir: Vector3F = position - (Data.player.getPosition - playerDir * 5)
      chunkDir.normalize()
      playerDir :* chunkDir
    }

    var chunks = Set[Chunk]()
    // Checks every chunk for viewable.
    for (cx <- -chunkDepth + playerChunk.x until chunkDepth + playerChunk.x; cy <- -chunkDepth + playerChunk.y until chunkDepth + playerChunk.y; cz <- -chunkDepth + playerChunk.z until chunkDepth + playerChunk.z) {
      if(findAngleToPlayer(Vector3F(cx, cy, cz) * 16) > 0.6) {
        val gen = -1 to 0
        for(x <- gen; y <- gen; z <- gen) {
          chunks = chunks + ChunkLoader.getChunk(Vector3I(cx + x, cy + y, cz + z) * 16 floor)
        }
      }
    }
    val playerPos = Data.player.getPosition
    chunks = chunks + ChunkLoader.getChunk(playerPos nearestBlock)
    chunks = chunks + ChunkLoader.getChunk((playerPos nearestBlock) py Data.player.height.ceil.toInt)

    for (chunk <- chunks) {
      if (!chunk.isLoaded) {
        chunk.updateVisible()
        chunk.isLoaded = true
      }


      for (block <- chunk.visibleBlocks) {
        val x = block.position.x
        val y = block.position.y
        val z = block.position.z
        ChunkLoader.getBlock(Vector3I(x, y, z)) match {
          case Some(`block`) =>
            val position = block.position
            block.tick

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
          case _ => Unit
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

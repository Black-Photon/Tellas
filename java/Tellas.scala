import java.nio.file.FileSystems

import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.{Camera, Cube, Framebuffer, GLWrapper, KeyListener, Model, Shader, Shape, SkyBox, Viewport}
import src.block.{Air, Dirt, Grass}
import src.util.Types.Texture
import src.util.{Vector3F, Vector3I}
import src.{Chunk, ChunkLoader, Data, Image}
import jni.Projection.ORTHOGRAPHIC
import jni.Rotation.{PITCH, YAW}

object Tellas extends App {
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

  val debugSun = false
  val sunCamDim = 4096 * 3
  val framebuffer: Framebuffer = new Framebuffer(sunCamDim, sunCamDim, debugSun)

  var testAngle: Float = -90.0f % 360


  val sunCam: Camera = new Camera(sunCamDim, sunCamDim)
  sunCam.setProjectionType(ORTHOGRAPHIC)
  sunCam.setRotation(PITCH, -testAngle.toInt)
  sunCam.setRotation(YAW, -90+testAngle.toInt)
  sunCam.lockPitch(false)

  val sunShader = new Shader("3dImage", if(debugSun) "3dImage" else "empty")

  val shadowShader = new Shader("3dImage", "shadow")
  shadowShader.useShader()
//  shadowShader.setFloat("uangle", 0.0f)
  shadowShader.setVec3("lightDir", Math.sin(-testAngle * Math.PI / 180).toFloat, Math.cos(-testAngle * Math.PI / 180).toFloat, Math.sin(testAngle * Math.PI / 180).toFloat)
  shadowShader.setInt("shadowMap", 1)

  var lastTime = 0.0f
  var iterations = 0

  message("Drawing")
  while(!GLWrapper.shouldClose) {
    val deltaT = GLWrapper.deltaT
    KeyListener.processInput(deltaT, sunCam)
    GLWrapper.prerender(0.2f, 0.2f, 0.7f)

    testAngle += deltaT * 25
    testAngle = testAngle % 360
    if(-testAngle > -180) {
      sunCam.setRotation(PITCH, testAngle.toInt % 90 + 270)
      sunCam.setRotation(YAW, -45)
    } else {
      sunCam.setRotation(PITCH, -testAngle.toInt % 90)
      sunCam.setRotation(YAW, 135)
    }

    shadowShader.useShader()
    shadowShader.setVec3("lightDir", Math.sin(-testAngle * Math.PI / 180).toFloat, Math.cos(-testAngle * Math.PI / 180).toFloat, Math.sin(testAngle * Math.PI / 180).toFloat)

    Data.player.frame(deltaT)
    sunCam.setPosition(Data.player.getPosition + Vector3F(-10.0f, 15.0f, 10.0f))

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
    val angle = testAngle//time % 360Fi

    framebuffer.start()
    Shape.bindBuffer(Model.CUBE)
    GLWrapper.prerender(0.2f, 0.2f, 0.7f)
    drawBlocks(sunCam, sunShader, false)
//    GLWrapper.useTexture(sun, 0)
//    Dirt.drawBlock(Vector3I(0, -1, 0))
    framebuffer.end()

    GLWrapper.useTexture(sun, 0)
    SkyBox.bindBuffer()
    SkyBox.activateShader(Data.player.camera)
    SkyBox.draw(Data.player.getPosition, angle, 15)

    if(debugSun) {
      GLWrapper.useTexture(framebuffer.texture, 0)
      Viewport.drawImage(0, 0, 1000, 1000)
    } else {
      shadowShader.useShader()
      shadowShader.simulateCamera("shadowMatrix", sunCam)
      GLWrapper.useTexture(framebuffer.texture, 1)
      drawBlocks(Data.player.camera, shadowShader, true)
    }

    val size = 64
    crosshair.draw(Data.width/2 - size/2, Data.height/2 - size/2, size, size)
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }


  /**
    * Draws all the blocks of this type
    */
  def drawBlocks(camera: Camera, shader: Shader, optimise: Boolean): Unit = {
    shader.useShader()
    shader.makeModel(camera)

    Shape.bindBuffer(Model.CUBE)
    val chunkDepth = 3
    val playerChunk = camera.getPosition / 16 floor
    val playerDir: Vector3F = camera.getLookingDirection

    def findAngleToPlayer(position: Vector3F): Float = {
      val chunkDir: Vector3F = position - (camera.getPosition - playerDir * 5)
      chunkDir.normalize()
      playerDir :* chunkDir
    }

    var chunks = Set[Chunk]()
    // Checks every chunk for viewable.
    for (cx <- -chunkDepth + playerChunk.x until chunkDepth + playerChunk.x; cy <- -chunkDepth + playerChunk.y until chunkDepth + playerChunk.y; cz <- -chunkDepth + playerChunk.z until chunkDepth + playerChunk.z) {
      if(optimise) {
        if (findAngleToPlayer(Vector3F(cx, cy, cz) * 16) > 0.6) {
          val gen = -1 to 0
          for (x <- gen; y <- gen; z <- gen) {
            chunks = chunks + ChunkLoader.getChunk(Vector3I(cx + x, cy + y, cz + z) * 16 floor)
          }
        }
      } else {
        val gen = -1 to 0
        for (x <- gen; y <- gen; z <- gen) {
          chunks = chunks + ChunkLoader.getChunk(Vector3I(cx + x, cy + y, cz + z) * 16 floor)
        }
      }
    }
    val playerPos = camera.getPosition
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
            val playerDir: Vector3F = camera.getLookingDirection
            playerDir.normalize()
            val objectDir: Vector3F = position.toFloat - (camera.getPosition - playerDir * 5)
            objectDir.normalize()
            val angle = if (optimise) playerDir :* objectDir else 1
            if (angle > 0.5 || position.toFloat == camera.getPosition) {
              for ((texture, list) <- block.self.textures) {
                GLWrapper.useTexture(texture, 0)
                for (side <- list) {
                  if (!block.isSide(side))
                    drawFace(position, side, shader)
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
    def drawFace(position: Vector3I, face: Side, shader: Shader): Unit = {
      Cube.drawFace(position, face, shader)
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

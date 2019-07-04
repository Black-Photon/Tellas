import java.nio.file.FileSystems

import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.{Camera, Cube, Framebuffer, GLWrapper, KeyListener, Model, Shader, Shape, SkyBox, Viewport}
import src.block.{Air, Dirt, Grass, Stone}
import src.util.Types.Texture
import src.util.{Vector3F, Vector3I}
import src.{Chunk, ChunkLoader, Data, Image}
import jni.Projection.ORTHOGRAPHIC
import jni.Rotation.{PITCH, YAW}

import scala.collection.mutable

object Tellas extends App {
  // Loads the C++
  System.load(
    FileSystems.getDefault
      .getPath("cmake-build-debug/libOpenGLProject.so") // Dynamic link ../../../
      .normalize.toAbsolutePath.toString)

  // Tests the JNI works
  GLWrapper.test()

  // Initialises the game
  message("Pre-Initialisation")
  GLWrapper.preInit(Data.width, Data.height, "Tellas")
  message("Initialisation")
  GLWrapper.init(false) // IMPORTANT - MAKE FALSE FOR DEBUGGING

  // Generates the world terrain
  addBlocks()

  Data.player.setPosition(Vector3F(3, -2, 0))

  var time = 0.0f

  // General Texture Loading
  val crosshair: Image = new Image("crosshair.png", true)
  val sun: Texture = GLWrapper.generateTexture("sun.png", false)

  // Shadow-mapping parameters
  val debugSun = false
  val sunCamDim = 4096 * 3
  val framebuffer: Framebuffer = new Framebuffer(sunCamDim, sunCamDim, debugSun)

  // Sun position
  var angle: Float = 250.0f % 360

  // Initialises shaders and cameras for shadow-mapping
  val sunCam: Camera = new Camera(sunCamDim, sunCamDim)
  sunCam.setProjectionType(ORTHOGRAPHIC)
  sunCam.setRotation(PITCH, -angle.toInt)
  sunCam.setRotation(YAW, -90+angle.toInt)
  sunCam.lockPitch(false)

  val sunShader = new Shader("3dImage", if(debugSun) "3dImage" else "empty")

  val shadowShader = new Shader("3dImage", "shadow")
  shadowShader.useShader()
  shadowShader.setVec3("lightDir", Math.sin(-angle * Math.PI / 180).toFloat, Math.cos(-angle * Math.PI / 180).toFloat, Math.sin(angle * Math.PI / 180).toFloat)
  shadowShader.setInt("shadowMap", 1)

  var lastTime = 0.0f
  var iterations = 0

  message("Drawing")
  while(!GLWrapper.shouldClose) {
    val deltaT = GLWrapper.deltaT
    KeyListener.processInput(deltaT, sunCam)
    val colour: Vector3F = skyColour(angle, 15.0f)
    GLWrapper.prerender(colour.x, colour.y, colour.z)
//    GLWrapper.prerender(0.17f, 0.0f, 0.02f) // Blood Moon Expansion

    // Sets shadow-mapping parameters
    angle += deltaT * 4
    angle = angle % 360
    if(-angle > -180) {
      sunCam.setRotation(PITCH, angle.toInt % 90 + 270)
      sunCam.setRotation(YAW, -45)
    } else {
      sunCam.setRotation(PITCH, -angle.toInt % 90)
      sunCam.setRotation(YAW, 135)
    }

    shadowShader.useShader()
    shadowShader.setVec3("lightDir", Math.sin(-angle * Math.PI / 180).toFloat, Math.cos(-angle * Math.PI / 180).toFloat, Math.sin(angle * Math.PI / 180).toFloat)

    // Update player and move sunCam to keep player in view
    Data.player.frame(deltaT)
    sunCam.setPosition(Data.player.getPosition + Vector3F(-10.0f, 15.0f, 10.0f))

    // FPS thing
    time += deltaT
    iterations += 1
    if((time - deltaT).floor < time.floor) {
      println("FPS currently at " + (iterations / (time - lastTime)).toInt)
      lastTime = time
      iterations = 0
    }

    draw()

    if (Data.player.getPosition.y < -100) Data.player.setPosition(Vector3F(3, -2, 0))

    GLWrapper.postrender()
    GLWrapper.swapBuffers()
    GLWrapper.pollEvents()
    GLWrapper.checkError()
  }
  message("Closing")
  GLWrapper.close()

  /**
    * Adds all the blocks to the world
    */
  def addBlocks(): Unit = {
    val cubeDims = 64
    for(x <- -cubeDims to cubeDims; z <- -cubeDims to cubeDims) {
      new Grass(Vector3I(x, -2, z), false)

    }
    for(x <- -cubeDims to cubeDims; y <- -cubeDims to -3; z <- -cubeDims to cubeDims) {
      new Dirt(Vector3I(x, y, z), false)
    }
  }

  /**
    * Draws everything to the screen
    */
  def draw(): Unit = {
    // Draw blocks to shadow-mapper
    framebuffer.start()
    Shape.bindBuffer(Model.CUBE)
    drawBlocks(sunCam, sunShader, optimise = false)
    framebuffer.end()

    // Draw Skybox
    GLWrapper.useTexture(sun, 0)
    SkyBox.bindBuffer()
    SkyBox.activateShader(Data.player.camera)
    SkyBox.draw(Data.player.getPosition, angle, 15)

    // Draw actual blocks
    if(debugSun) {
      GLWrapper.useTexture(framebuffer.texture, 0)
      Viewport.drawImage(0, 0, 1000, 1000)
    } else {
      shadowShader.useShader()
      shadowShader.simulateCamera("shadowMatrix", sunCam)
      GLWrapper.useTexture(framebuffer.texture, 1)
      drawBlocks(Data.player.camera, shadowShader, optimise = true)
    }

    val size = 64
    crosshair.draw(Data.width/2 - size/2, Data.height/2 - size/2, size, size)
  }

  /**
    * Prints a formal message (Information)
    * Uses format "INFO::SCALA::MESSAGE"
    * @param message Message to send
    */
  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }


  /**
    * Draws all the blocks
    * @param camera Camera to draw from perspective of
    * @param shader Shader to use to draw
    * @param optimise Whether to optimise drawing - cull unseen blocks
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

    val facesToDraw = mutable.Map[(Texture, Side), List[Vector3I]]()

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
                  if (!block.isSide(side)) {
                    if(facesToDraw.isDefinedAt((texture, side))) {
                      facesToDraw((texture, side)) = facesToDraw((texture, side)).::(position)
                    } else {
                      facesToDraw((texture, side)) = List(position)
                    }
                    drawFace(position, side, shader)
                  }
                }
              }
            }
          case _ => Unit
        }
      }
    }

    for((texture, side) <- facesToDraw.keys) {
      GLWrapper.useTexture(texture, 0)
      val pos = facesToDraw((texture, side))
      Cube.drawFaceMany(pos, side)
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

  /**
    * Calculates the colour the sky should be
    * @param angle Angle the sun is at (Up is 0 degrees)
    * @param duration Duration of each transition in degrees
    * @return Sky colour at angle
    */
  def skyColour(angle: Float, duration: Float): Vector3F =
    if(angle < 90 - 2*duration || angle > 270 + 2*duration) {
      Vector3F(0.2f, 0.2f, 0.7f)
    } else if(angle > 90 && angle < 270) {
      Vector3F(0.0f, 0.0f, 0.0f)
    } else if(angle < 90 - duration) {
      val newAngle = (angle - (90 - 2*duration)) / duration
      Vector3F(0.2f + newAngle / 2, 0.2f, 0.7f - newAngle / 2)
    } else if(angle < 90) {
      val newAngle = (angle - (90 - duration)) / duration
      Vector3F(0.7f - newAngle * 0.7f, 0.2f - newAngle * 0.2f, 0.2f - newAngle * 0.2f)
    } else if(angle < 270 + duration) {
      val newAngle = (angle - 270) / duration
      Vector3F(newAngle * 0.7f, newAngle * 0.2f, newAngle * 0.2f)
    } else if(angle < 270 + 2*duration) {
      val newAngle = (angle - (270 + duration)) / duration
      Vector3F(0.7f - newAngle / 2, 0.2f, 0.2f + newAngle / 2)
    } else {
      throw new RuntimeException("Sky Colour did not match any options - should be impossible. Contact Developer.")
    }

}

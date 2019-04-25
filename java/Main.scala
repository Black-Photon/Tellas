import java.nio.file.FileSystems

import jni.{GLWrapper, KeyListener, Shape}
import src.block.{Air, Dirt, Grass}
import src.util.{Vector3F, Vector3I}
import src.Data

object Main extends App {
  // Loads the C++
  System.load(
    FileSystems.getDefault
      .getPath("cmake-build-debug/libOpenGLProject.so") // Dynamic link ../../../
      .normalize.toAbsolutePath.toString)

  GLWrapper.test()

  message("Pre-Initialisation")
  GLWrapper.preInit(1920, 1080, "Tellas")
  message("Initialisation")
  GLWrapper.init(true)

  addBlocks()

  Data.player.setPosition(Vector3F(3, -2, 0))

  Data.blocks += Air
  Data.blocks += Dirt
  Data.blocks += Grass

  var time = 0.0f

  message("Drawing")
  while(!GLWrapper.shouldClose) {
    val deltaT = GLWrapper.deltaT
    KeyListener.processInput(deltaT)
    GLWrapper.prerender(0.2f, 0.2f, 0.7f)

    Data.player.frame(deltaT)

    time = time + deltaT
    if((time - deltaT).floor < time.floor) {
      println("FPS currently at " + (1 / deltaT).toInt)
    }

    draw()

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
  }

  def draw(): Unit = {
    Shape.activateShader()
    Grass.draw()
    Dirt.draw()
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }
}

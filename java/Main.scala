import java.nio.file.FileSystems

import jni.{GLWrapper, KeyListener, Shape}
import src.{Chunk, Data, Dirt, Vector3}

object Main extends App {
  // Loads the C++
  System.load(
    FileSystems.getDefault
      .getPath("../../../cmake-build-debug/libOpenGLProject.so") // Dynamic link
      .normalize.toAbsolutePath.toString)

  GLWrapper.test()

  message("Pre-Initialisation")
  GLWrapper.preInit(1920, 1080, "Tellas")
  message("Initialisation")
  GLWrapper.init(true)

  addBlocks()

  Data.player.setPosition(Vector3(3, -2, 0))

  message("Drawing")
  while(!GLWrapper.shouldClose) {
    val deltaT = GLWrapper.deltaT
    KeyListener.processInput(deltaT)
    GLWrapper.prerender(0.2f, 0.2f, 0.7f)

    Data.player.frame(deltaT)

    draw()

    if (Data.player.getPosition.y < -50) Data.player.setPosition(Vector3(3, -2, 0))

    GLWrapper.postrender()
    GLWrapper.swapBuffers()
    GLWrapper.pollEvents()
    GLWrapper.checkError()
  }
  message("Closing")
  GLWrapper.close()

  def addBlocks(): Unit = {
    for(x <- -20 to 20; z <- -20 to 20) {
      new Dirt(Vector3(x, -2, z))
    }
    new Dirt(Vector3(12, -1, 3))
    new Dirt(Vector3(12, 0, 3))
    new Dirt(Vector3(12, 1, 3))
    new Dirt(Vector3(10, -1, 3))
    new Dirt(Vector3(10, 0, 3))
    new Dirt(Vector3(10, 1, 3))
    new Dirt(Vector3(11, 1, 3))

    for(z <- -20 to 20; y <- -1 to 10) {
      if(z != 0)
      new Dirt(Vector3(5, y, z))
    }
  }

  def draw(): Unit = {
    Shape.activateShader()
    Dirt.draw()
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }
}

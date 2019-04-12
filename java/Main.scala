import java.nio.file.FileSystems

import jni.{GLWrapper, Shape, Vector3}

object Main extends App{
  // Loads the C++
  System.load(
    FileSystems.getDefault
      .getPath("../../../cmake-build-debug/libOpenGLProject.so") // Dynamic link
      .normalize.toAbsolutePath.toString)

  GLWrapper.test()

  message("Pre-Initialisation")
  GLWrapper.preInit(1920, 1080, "Tellas")
  message("Initialisation")
  GLWrapper.init()

  addBlocks()

  message("Drawing")
  while(!GLWrapper.shouldClose) {
    val deltaT = GLWrapper.deltaT
    GLWrapper.processInput(deltaT)
    GLWrapper.prerender(0.2f, 0.2f, 0.7f)

    draw()

    GLWrapper.postrender()
    GLWrapper.swapBuffers()
    GLWrapper.pollEvents()
    GLWrapper.checkError()
  }
  message("Closing")
  GLWrapper.close()

  def addBlocks(): Unit = {
    for(x <- -100 to 100; z <- -100 to 100) {
      new Dirt(Vector3(x, -2, z))
    }
    new Dirt(Vector3(12, -1, 3))
    new Dirt(Vector3(12, 0, 3))
    new Dirt(Vector3(12, 1, 3))
    new Dirt(Vector3(10, -1, 3))
    new Dirt(Vector3(10, 0, 3))
    new Dirt(Vector3(10, 1, 3))
    new Dirt(Vector3(11, 1, 3))
  }

  def draw(): Unit = {
    Shape.activateShader()
    Dirt.draw()
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }
}

import jni.{GLWrapper, Model, Shape, Vector3}
import Model.CUBE

object Main extends App{
  val model: Model = CUBE
  val cube: Shape = new Shape(new Vector3(0, -0.5f, 0))
  val cube2: Shape = new Shape(new Vector3(0.5f, -0.5f, 0))

  message("Pre-Initialisation")
  GLWrapper.preInit(1920, 1080, "Tellas")
  message("Initialisation")
  GLWrapper.init()

  val texture: Int = GLWrapper.generateTexture("dirt.jpg", false)

  message("Drawing")
  while(!GLWrapper.shouldClose()) {
    val deltaT = GLWrapper.deltaT()
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

  def draw(): Unit = {
    GLWrapper.useTexture(texture, 0)

    cube.draw(model)
    cube2.draw(model)
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }
}

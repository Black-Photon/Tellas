import jni.GLWrapper

object Main extends App{
  override def main(args: Array[String]): Unit = {
    message("Pre-Initialisation")
    GLWrapper.preInit(1920, 1080, "Tellas")
    message("Initialisation")
    GLWrapper.init()
    message("Drawing")
    while(!GLWrapper.shouldClose()) {
      GLWrapper.frame()
    }
    message("Closing")
    GLWrapper.close()
  }

  def message(message: String): Unit = {
    println("INFO::SCALA::"+message.toUpperCase())
  }
}

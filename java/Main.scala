import jni.GLWrapper

object Main extends App{
  override def main(args: Array[String]): Unit = {
    println("INFO::SCALA::PRE-INITIALISATION")
    GLWrapper.preInit(1920, 1080, "Tellas")
    println("INFO::SCALA::INITIALISATION")
    GLWrapper.init()
    println("INFO::SCALA::DRAWING")
    while(!GLWrapper.shouldClose()) {
      GLWrapper.frame()
    }
    println("INFO::SCALA::CLOSING")
    GLWrapper.close()
  }
}

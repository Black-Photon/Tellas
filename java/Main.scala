import jni.GLWrapper

object Main extends App{
  override def main(args: Array[String]): Unit = {
    println("INFO::SCALA::PRE-INITIALISATION")
    GLWrapper.preInit()
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

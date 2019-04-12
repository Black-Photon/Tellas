import jni.{Model, Vector3}

abstract class Block(position: Vector3) {
  def drawBlock(): Unit
}

package src

abstract class BlockInstance {
  val ID: Int

  def break(position: Vector3I): Unit
  def drawBlock(position: Vector3I): Unit
}

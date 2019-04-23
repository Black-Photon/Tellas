package src

abstract class Block(p: Vector3I) {
  val position: Vector3I = p
  val self: BlockInstance
}
package src

case class Air(override val position: Vector3I) extends Block(position) {
  override val self: BlockInstance = Air
}

object Air extends BlockInstance {
  override val ID: Int = 0
  override def break(position: Vector3I): Unit = {}
  override def drawBlock(position: Vector3I): Unit = {}
}
package src

case class Air(override val position: Vector3) extends Block(position) {
  override def drawBlock(): Unit = {}
}

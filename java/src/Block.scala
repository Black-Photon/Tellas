package src

abstract class Block(p: Vector3) {
  val position = p
  def drawBlock(): Unit

  if(!this.isInstanceOf[Air]) {
    ChunkLoader.addBlock(this, position floor)
  }
}
package src

case class Chunk(postion: Vector3I) {
  val blocks: FixedLandscape[Int] = new FixedLandscape(16, 16, 16)

  def setBlock(block: BlockInstance, position: Vector3I): Unit = {
     blocks.set(block.ID, position)
  }

  def getBlock(position: Vector3I): BlockInstance = {
    val id = blocks.get(position)
    for(block <- Data.blocks) {
      if(block.ID == id) {
        return block
      }
    }
    null
  }
}

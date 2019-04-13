package src

case class Chunk(postion: Vector3) {
  val blocks: Landscape[Block] = new Landscape

  for (x <- 0 to 15; y <- 0 to 15; z <- 0 to 15) {
    val here = Vector3(x, y, z)
    val block = Air(here)

    blocks.set(block, here)
  }

  def setBlock(block: Block, position: Vector3): Unit = {
     blocks.set(block, position)
  }

  def getBlock(position: Vector3): Block = {
    blocks.get(position) match {
      case None     => throw new RuntimeException("Chunk not full")
      case Some(b)  => b
    }
  }
}

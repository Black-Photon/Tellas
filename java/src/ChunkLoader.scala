package src

object ChunkLoader {
  val chunks: Landscape[Chunk] = new Landscape[Chunk]

  def getChunk(position: Vector3): Chunk = {
    chunks.get(position / 16) match {
      case None           =>
        chunks.set(Chunk(position / 16 floor), position / 16)
        chunks.get(position / 16) match {
          case Some(x)    => x
          case None       => throw new RuntimeException("Cannot access position set")
        }

      case Some(value)    => value
    }
  }

  def isBlock(position: Vector3): Boolean = {
    getChunk(position).getBlock(position % 16) match {
      case _: Air         => false
      case _              => true
    }
  }

  def addBlock(block: Block, position: Vector3): Unit = {
    getChunk(position).setBlock(block, position % 16)
  }
}

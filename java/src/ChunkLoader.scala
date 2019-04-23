package src

object ChunkLoader {
  val chunks: HashLandscape[Chunk] = new HashLandscape[Chunk]

  def getChunk(position: Vector3I): Chunk = {
    chunks.get(position / 16 floor) match {
      case null           =>
        chunks.set(Chunk(position / 16 floor), position / 16 floor)
        chunks.get(position / 16 floor) match {
          case null       => throw new RuntimeException("Cannot access position set")
          case x          => x
        }

      case value          => value
    }
  }

  def getBlock(position: Vector3I): BlockInstance = {
    getChunk(position).getBlock(position % 16)
  }

  def isBlock(position: Vector3I): Boolean = {
    getChunk(position).getBlock(position % 16) match {
      case Air            => false
      case _              => true
    }
  }

  def addBlock(block: BlockInstance, position: Vector3I): Unit = {
    getChunk(position).setBlock(block, position % 16)
  }
}

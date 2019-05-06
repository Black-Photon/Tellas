package src

import src.block.{Air, Block, BlockInstance}
import src.util.{HashLandscape, Vector3I}

/**
  * Manages access of chunks
  */
object ChunkLoader {
  /**
    * Landscape holding the chunks
    */
  val chunks: HashLandscape[Chunk] = new HashLandscape[Chunk]

  /**
    * Returns a chunk at the given position
    * @see [[getBlock]] for accessing individual blocks
    * @param position Position in real space of chunk (ie. (0, 0, 0) and (1, 1, 1) correspond with the same chunk)
    * @return Chunk at position
    */
  def getChunk(position: Vector3I): Chunk = {
    // Attempts to retrieve chunk at position
    chunks.get(position / 16 floor) match {
        // If fail
      case None =>
        // Create chunk and retrieve this new chunk
        chunks.set(new Chunk, position / 16 floor)
        chunks.get(position / 16 floor) match {
          case None             => throw new RuntimeException("Cannot access position set")
          case Some(x)          => x
        }

      case Some(value)          => value
    }
  }

  /**
    * Returns the block at the given position
    * @param position Location of block
    * @return Block at location
    */
  def getBlock(position: Vector3I): Option[Block] = {
    getChunk(position).getBlock(position % 16)
  }

  /**
    * Checks if a block is at the location (Or Air)
    * @param position Position to check
    * @return True if block, False if only Air
    */
  def isBlock(position: Vector3I): Boolean = {
    getChunk(position).getBlock(position % 16) match {
      case None           => false
      case _              => true
    }
  }

  /**
    * Adds a block to the chunk
    * @param block Block type to add
    * @param position Position of the block
    */
  def addBlock(block: Block, position: Vector3I): Unit = {
    getChunk(position).setBlock(block, position % 16)
  }

  def addBlockNoUpdate(block: Block, position: Vector3I): Unit = {
    getChunk(position).setBlockNoUpdate(block, position % 16)
  }

  def updateBlockVisibility(maybeBlock: Option[Block]): Unit = {
    maybeBlock match {
      case None => Unit
      case Some(block) =>   val chunk = getChunk(block.position)
                            chunk.updateBlockVisibility(block)
    }
  }

  def updateSurroundingVisibility(position: Vector3I): Unit = {
      ChunkLoader.updateBlockVisibility(ChunkLoader.getBlock(position px 1))
      ChunkLoader.updateBlockVisibility(ChunkLoader.getBlock(position px -1))
      ChunkLoader.updateBlockVisibility(ChunkLoader.getBlock(position py 1))
      ChunkLoader.updateBlockVisibility(ChunkLoader.getBlock(position py -1))
      ChunkLoader.updateBlockVisibility(ChunkLoader.getBlock(position pz 1))
      ChunkLoader.updateBlockVisibility(ChunkLoader.getBlock(position pz -1))
  }
}

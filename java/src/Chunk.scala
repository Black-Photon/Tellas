package src

import src.block.BlockInstance
import src.util.{FastFixedLandscape, Vector3I}

/**
  * Holds data for a single Chunk
  */
class Chunk {
  // Landscape of blocks
  val blocks: FastFixedLandscape[Int] = new FastFixedLandscape(16, 16, 16)

  /**
    * Sets a block in the chunk
    * @param block Block to set
    * @param position Position to set block
    */
  def setBlock(block: BlockInstance, position: Vector3I): Unit = {
     blocks.set(block.ID, position)
  }

  /**
    * Gets a block from a position
    * @param position Position to look in
    * @return Block at position
    */
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

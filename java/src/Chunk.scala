package src

import src.block.{Air, Block, BlockInstance, Dirt, Grass}
import src.util.{FastFixedLandscape, Vector3I}

/**
  * Holds data for a single Chunk
  */
class Chunk {
  // Landscape of blocks
  val blocks: FastFixedLandscape[Block] = new FastFixedLandscape(16, 16, 16)
  val top: FastFixedLandscape[Block] = new FastFixedLandscape(16, 1, 16)
  var visibleBlocks: List[Block] = List()
  var isLoaded: Boolean = false

  /**
    * Sets a block in the chunk
    * @param block Block to set
    * @param position Position to set block
    */
  def setBlock(block: Block, position: Vector3I): Unit = {
    visibleBlocks = visibleBlocks.filterNot(testBlock => testBlock.position % 16 == position)
    val original = blocks.get(position)
    setBlockNoUpdate(block, position)
    if(block != null && block.isVisible) {
      visibleBlocks = visibleBlocks.::(block)
    }
  }

  def setBlockNoUpdate(block: Block, position: Vector3I): Unit = {
    blocks.set(block, position)
  }

  def updateBlockVisibility(block: Block): Unit = {
    if(block != null && block.isVisible) {
      visibleBlocks = visibleBlocks.::(block)
    }
  }

  /**
    * Gets a block from a position
    * @param position Position to look in
    * @return Block at position
    */
  def getBlock(position: Vector3I): Option[Block] = {
    blocks.get(position)
  }

  def updateVisible(): Unit = {
    visibleBlocks = List()
    for(x <- 0 to 15; y <- 0 to 15; z <- 0 to 15) {
      getBlock(Vector3I(x, y, z)) match {
        case None => Unit
        case Some(block) => if(block.isVisible) visibleBlocks = visibleBlocks.::(block)
      }
    }
  }
}

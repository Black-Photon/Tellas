package src.block

import src.ChunkLoader
import src.util.Vector3I

/**
  * Defines an individual block
  * @param self The Instance the block is a type of
  * @param p Position of the block
  */
abstract class Block(self: BlockInstance, p: Vector3I) {
  val position: Vector3I = p

  ChunkLoader.addBlock(self, position)
}
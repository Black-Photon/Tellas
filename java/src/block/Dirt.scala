package src.block

import src.util.Vector3I

/**
  * Dirt Block
  * @param position Position of block in world space
  */
class Dirt(position: Vector3I) extends Block(Dirt, position)

/**
  * Dirt Block Type
  */
object Dirt extends BlockInstance("dirt.jpg", false) {
  // Dirt ID
  override val ID: Int = 1
}
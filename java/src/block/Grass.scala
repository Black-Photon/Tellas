package src.block

import src.util.Vector3I

/**
  * Grass Block
  * @param position Position of block in world space
  */
class Grass(position: Vector3I) extends Block(Grass, position)

/**
  * Grass Block Type
  */
object Grass extends BlockInstance("grass.png", true) {
  // Grass ID
  override val ID: Int = 2
}
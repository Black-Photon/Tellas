package src.block

import jni.Cube.Side.ALL
import src.util.Vector3I

/**
  * Dirt Block
  * @param position Position of block in world space
  */
class Dirt(position: Vector3I) extends Block(Dirt, position)

/**
  * Dirt Block Type
  */
object Dirt extends BlockInstance() {
  // Dirt ID
  override val ID: Int = 1

  addTexture("dirt.jpg", false, ALL)
}
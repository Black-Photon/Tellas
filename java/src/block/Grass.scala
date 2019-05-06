package src.block

import jni.Cube.Side.{ALL, TOP, LEFT, RIGHT, BOTTOM, BACK, FRONT}
import src.util.Vector3I

/**
  * Grass Block
  * @param position Position of block in world space
  */
class Grass(position: Vector3I, update: Boolean) extends Block(Grass, position, update) {
  def this(position: Vector3I) = this(position, true)
}

/**
  * Grass Block Type
  */
object Grass extends BlockInstance() {
  // Grass ID
  override val ID: Int = 2

  addTexture("grass.png", true, List(TOP))
  addTexture("grass_side.png", false, List(LEFT, RIGHT, BACK, FRONT)) // For isPNG, see Dirt
  addTexture(Dirt.textures.head._1, List(BOTTOM))
}
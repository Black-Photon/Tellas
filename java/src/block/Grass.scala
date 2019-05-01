package src.block

import jni.Cube.Side.{ALL, TOP, LEFT, RIGHT, BOTTOM, BACK, FRONT}
import src.util.Vector3I

/**
  * Grass Block
  * @param position Position of block in world space
  */
class Grass(position: Vector3I) extends Block(Grass, position)

/**
  * Grass Block Type
  */
object Grass extends BlockInstance() {
  // Grass ID
  override val ID: Int = 2

  addTexture("grass.png", true, List(TOP))
  addTexture("grass_side.jpg", false, List(LEFT, RIGHT, BACK, FRONT))
  addTexture(Dirt.textures.head._1, List(BOTTOM))
}
package src.block

import jni.Cube.Side.{ALL, LEFT, RIGHT, TOP, BOTTOM, FRONT, BACK}
import src.util.Vector3I

/**
  * Stone Block
  * @param position Position of block in world space
  */
class Stone(position: Vector3I, update: Boolean) extends Block(Stone, position, update) {
  def this(position: Vector3I) = this(position, true)

}

/**
  * Stone Block Type
  */
object Stone extends BlockInstance() {
  // Stone ID
  override val ID: Int = 3

  addTexture("stone.png", false, ALL)
}
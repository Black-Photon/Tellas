package src.block

import jni.Cube.Side.{ALL, BACK, BOTTOM, FRONT, LEFT, RIGHT, TOP}
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
  override def ID: Int = 3
  override type BlockClass = Stone

  override def createNew(pos: Vector3I): Block = new BlockClass(pos)

  addTexture("stone.png", false, ALL)
}
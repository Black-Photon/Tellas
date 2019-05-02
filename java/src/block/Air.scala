package src.block

import src.util.Vector3I

/**
  * Empty Block with no texture
  * @param position Position of block
  */
case class Air(override val position: Vector3I) extends Block(Air, position)

/**
  * Air Block Type
  */
object Air extends BlockInstance() {
  // Air ID
  override val ID: Int = 0

  // Don't draw anything
  override def drawBlock(position: Vector3I): Unit = {}
}
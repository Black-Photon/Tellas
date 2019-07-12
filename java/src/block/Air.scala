package src.block

import jni.Shader
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
  override def ID: Int = 0
  override type BlockClass = Air

  override def createNew(pos: Vector3I): Block = new BlockClass(pos)

  // Don't draw anything
  override def drawBlock(position: Vector3I, shader: Shader): Unit = {}

  // Don't draw anything
  override def drawItem(x: Float, y: Float, size: Int, shader: Shader): Unit = {}
}
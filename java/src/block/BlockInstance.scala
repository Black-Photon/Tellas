package src.block

import jni.{Cube, GLWrapper, Model, Shader, Shape}
import jni.Cube.Side.{ALL, BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.Model.{CUBE, Model}
import src.Data
import src.util.Vector3I

/**
  * Acts as a discrete block type
  */
abstract class BlockInstance() {
  type Texture = Int

  // Uses a cube model as default
  val model: Model = CUBE
  // ID of block type
  def ID: Int
  // Textures to draw with
  var textures: List[(Texture, List[Side])] = List()
  // Type of Block
  type BlockClass <: Block

  // Add to data list
  try {
    Data.blocks(ID) = this
  } catch {
    case _: ArrayIndexOutOfBoundsException =>
      throw new RuntimeException("Update number of blocks in Data.blocks array")
  }

  def createNew(pos: Vector3I): Block

  def addTexture(texture: Int, sides: List[Side]): Unit = {
    textures = textures :+ (texture, sides)
  }

  def addTexture(name: String, isPNG: Boolean, sides: List[Side]): Unit = {
    textures = textures :+ (GLWrapper.generateTexture(name, isPNG), sides)
  }

  /**
    * Draws an individual block
    * @param position Location to draw at
    */
  def drawBlock(position: Vector3I, shader: Shader): Unit = {
    shader.useShader()
    for((texture, sides) <- textures; side <- sides) {
      GLWrapper.useTexture(texture, 0)
      Cube.drawFace(position, side, shader)
    }
  }

  def drawItem(x: Float, y: Float, size: Int, shader: Shader): Unit = {
    shader.useShader()
    for((texture, sides) <- textures; side <- sides) {
      GLWrapper.useTexture(texture, 0)
      Cube.drawItemFace(x, y, size, shader, side)
    }
  }
}
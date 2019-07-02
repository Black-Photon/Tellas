package src.block

import jni.{Cube, GLWrapper, Model, Shader, Shape}
import jni.Cube.Side.{ALL, BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.Model.{CUBE, Model}
import src.util.Vector3I

/**
  * Acts as a discrete block type
  */
abstract class BlockInstance() {
  type Texture = Int

  // Uses a cube model as default
  val model: Model = CUBE
  // ID of block type
  val ID: Int
  // Textures to draw with
  var textures: List[(Texture, List[Side])] = List()

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
    Cube.drawFace(position, FRONT, shader)
    Cube.drawFace(position, BACK, shader)
    Cube.drawFace(position, LEFT, shader)
    Cube.drawFace(position, RIGHT, shader)
    Cube.drawFace(position, BOTTOM, shader)
    Cube.drawFace(position, TOP, shader)
  }
}
package src.block

import jni.{Cube, GLWrapper, Shape}
import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP, ALL}
import Model.CUBE
import jni.Cube.Side
import src.util.Vector3I
import src.ChunkLoader

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
    * Draws all the blocks of this type
    */
  def draw(): Unit = {
    Shape.bindBuffer(model)
    for(cx <- -2 to 2; cy <- -2 to 2; cz <- -2 to 2) {
      val chunk = ChunkLoader.getChunk(Vector3I(cx*16, cy*16, cz*16))
      for(x <- 0 to 15; y <- 0 to 15; z <- 0 to 15) {
        val block = chunk.getBlock(Vector3I(x, y, z))
        if(block.ID == ID) {
          val position = Vector3I(16 * cx + x, 16 * cy + y, 16 * cz + z)
          for((texture, list) <- textures) {
            GLWrapper.useTexture(texture, 0)
            for(side <- list) {
              drawFace(position, side)
            }
          }
        }
      }
    }
  }

  /**
    * Draws an individual block
    * @param position Location to draw at
    */
  def drawBlock(position: Vector3I): Unit = {
    Cube.drawFace(position, FRONT)
    Cube.drawFace(position, BACK)
    Cube.drawFace(position, LEFT)
    Cube.drawFace(position, RIGHT)
    Cube.drawFace(position, BOTTOM)
    Cube.drawFace(position, TOP)
  }

  /**
    * Draws an individual block
    * @param position Location to draw at
    */
  def drawFace(position: Vector3I, face: Side): Unit = {
    Cube.drawFace(position, face)
  }
}

package src.block

import jni.{GLWrapper, Shape}
import Model.CUBE
import src.util.Vector3I
import src.ChunkLoader

/**
  * Acts as a discrete block type
  * @param textureName Name of texture for block (eg. dirt.jpg)
  * @param isPNG True if the texture is a PNG
  */
abstract class BlockInstance(textureName: String, isPNG: Boolean) {
  type Texture = Int

  // Uses a cube model as default
  val model: Model = CUBE
  // ID of block type
  val ID: Int
  // Texture to draw with
  val texture: Texture = if (textureName != null) GLWrapper.generateTexture(textureName, isPNG) else 0

  /**
    * Draws all the blocks of this type
    * @tparam E Type of block to draw
    */
  def draw[E](): Unit = {
    GLWrapper.useTexture(texture, 0)
    Shape.bindBuffer(model)
    for(cx <- -2 to 2; cy <- -2 to 2; cz <- -2 to 2) {
      val chunk = ChunkLoader.getChunk(Vector3I(cx*16, cy*16, cz*16))
      for(x <- 0 to 15; y <- 0 to 15; z <- 0 to 15) {
        val block = chunk.getBlock(Vector3I(x, y, z))
        if(block.isInstanceOf[E]) {
          block.drawBlock(Vector3I(16*cx + x, 16*cy + y, 16*cz + z))
        }
      }
    }
  }

  /**
    * Draws an individual block
    * @param position Location to draw at
    */
  def drawBlock(position: Vector3I): Unit = Shape.draw(model, position)
}

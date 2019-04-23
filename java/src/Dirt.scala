package src

import jni.{GLWrapper, Shape}
import src.Model.CUBE

import scala.collection.mutable

class Dirt(position: Vector3I) extends Block(position) {
  override val self: BlockInstance = Dirt

  Dirt.blocks += this

  ChunkLoader.addBlock(Dirt, position)
}

object Dirt extends BlockInstance {
  val model: Model = CUBE
  val blocks: mutable.ListBuffer[Dirt] = mutable.ListBuffer()
  val texture: Int = GLWrapper.generateTexture("dirt.jpg", isPNG = false)
  override val ID: Int = 1

  def break(position: Vector3I): Unit = {
    ChunkLoader.addBlock(Air, position)
    for(i <- 0 to Dirt.blocks.length) {
      if(Dirt.blocks(i).position == position) {
        for(block <- blocks) {
          if(position == block.position) {
            Dirt.blocks -= block
          }
        }
        return
      }
    }
  }

  def draw(): Unit = {
    GLWrapper.useTexture(texture, 0)
    Shape.bindBuffer(model)
//    for(dirt <- blocks) {
//      drawBlock(dirt.position)
//    }
    for(cx <- -2 to 2; cy <- -2 to 2; cz <- -2 to 2) {
      val chunk = ChunkLoader.getChunk(Vector3I(cx*16, cy*16, cz*16))
      for(x <- 0 to 15; y <- 0 to 15; z <- 0 to 15) {
        chunk.getBlock(Vector3I(x, y, z)).drawBlock(Vector3I(16*cx + x, 16*cy + y, 16*cz + z))
      }
    }
  }

  override def drawBlock(position: Vector3I): Unit = {
    Shape.draw(Dirt.model, position)
  }
}
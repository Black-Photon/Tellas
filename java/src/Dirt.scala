package src

import jni.{GLWrapper, Shape}
import src.Model.CUBE

import scala.collection.mutable

class Dirt(position: Vector3) extends Block(position) {
  val shape: Shape = new Shape(position)

  Dirt.blocks += this

  def break(): Unit = {
    ChunkLoader.addBlock(Air(position), position)
    for(i <- 0 to Dirt.blocks.length) {
      if(Dirt.blocks(i).position == position) {
        Dirt.blocks -= this
        return
      }
    }
  }

  override def drawBlock(): Unit = {
    shape.draw(Dirt.model)
  }
}

object Dirt {
  val model: Model = CUBE
  val blocks: mutable.ListBuffer[Dirt] = mutable.ListBuffer()
  val texture: Int = GLWrapper.generateTexture("dirt.jpg", isPNG = false)

  def draw(): Unit = {
    GLWrapper.useTexture(texture, 0)
    Shape.bindBuffer(model)
    for(dirt <- blocks) {
      dirt.drawBlock()
    }
  }
}
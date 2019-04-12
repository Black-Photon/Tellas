import jni.{GLWrapper, Model, Shape, Vector3}
import jni.Model.CUBE

import scala.collection.mutable

class Dirt(position: Vector3) extends Block(position) {
  val shape: Shape = new Shape(position)

  Dirt.blocks += this

  override def drawBlock(): Unit = {
    shape.draw(Dirt.model)
  }
}

object Dirt {
  val model: Model = CUBE
  val blocks: mutable.MutableList[Dirt] = mutable.MutableList()
  val texture: Int = GLWrapper.generateTexture("dirt.jpg", false)

  def draw(): Unit = {
    GLWrapper.useTexture(texture, 0)
    Shape.bindBuffer(model)
    for(dirt <- blocks) {
      dirt.drawBlock()
    }
  }
}
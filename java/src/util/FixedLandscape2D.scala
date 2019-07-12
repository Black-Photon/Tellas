package src.util

import scala.reflect.ClassTag

class FixedLandscape2D[A : ClassTag](x: Int, y: Int) extends FixedLandscape[A](x, y, 1) {
  def set(content: A, vector2: Vector2I): Unit = {
    landscape(vector2.x)(vector2.y)(0) = content
  }

  def get(vector2: Vector2I): Option[A] = {
    Option(landscape(vector2.x)(vector2.y)(0))
  }

  def apply(x: Int, y: Int): Option[A] = get(Vector2I(x, y))
}

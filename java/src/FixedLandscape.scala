package src

import scala.reflect.ClassTag

class FixedLandscape[A : ClassTag](x: Int, y: Int, z: Int) extends Landscape[A] {
  val landscape: Array[Array[Array[A]]] = Array.ofDim[A](x, y, z)

  override def set(content: A, vector3: Vector3I): Unit = {
    landscape(vector3.x)(vector3.y)(vector3.z) = content
  }

  override def get(vector3: Vector3I): A = {
    landscape(vector3.x)(vector3.y)(vector3.z)
  }
}

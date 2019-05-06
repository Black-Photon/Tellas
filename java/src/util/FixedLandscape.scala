package src.util

import scala.reflect.ClassTag

/**
  * A landscape of fixed size
  * @param x Width of Landscape
  * @param y Height of Landscape
  * @param z Depth of Landscape
  * @tparam A Type of data to hold
  */
class FixedLandscape[A : ClassTag](x: Int, y: Int, z: Int) extends Landscape[A] {
  /**
    * Actual data structure
    */
  val landscape: Array[Array[Array[A]]] = Array.ofDim[A](x, y, z)

  override def set(content: A, vector3: Vector3I): Unit = {
    landscape(vector3.x)(vector3.y)(vector3.z) = content
  }

  override def get(vector3: Vector3I): Option[A] = {
    Option(landscape(vector3.x)(vector3.y)(vector3.z))
  }
}

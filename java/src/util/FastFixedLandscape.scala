package src.util

import scala.reflect.ClassTag

/**
  * A landscape of fixed size
  * @param x Width of Landscape
  * @param y Height of Landscape
  * @param z Depth of Landscape
  * @tparam A Type of data to hold
  */
class FastFixedLandscape[A : ClassTag](x: Int, y: Int, z: Int) extends Landscape[A] {
  /**
    * Actual data structure
    */
  val landscape: Array[A] = new Array[A](x * y * z)

  override def set(content: A, vector3: Vector3I): Unit = landscape(findValue(vector3)) = content

  override def get(vector3: Vector3I): A = landscape(findValue(vector3))

  val findValue: Vector3I => Int = v => v.x  +  x * v.y  +  x * y * v.z
}

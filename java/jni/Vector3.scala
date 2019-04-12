package jni

/**
  * Represents a 3-Dimensional Vector
  *
  * @param x X-Value
  * @param y Y-Value
  * @param z Z-Value
  */
case class Vector3 (x: Float, y: Float, z: Float) {
  /**
  * Creates a vector with all components equal to 0
  */
  def this() = this(0, 0, 0)

  /**
    * Creates a vector with all components the same value
    *
    * @param xyz Value to set X, Y and Z to
    */
  def this(xyz: Float) = this(xyz, xyz, xyz)
}

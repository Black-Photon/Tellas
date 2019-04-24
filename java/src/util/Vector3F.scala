package src.util

/**
  * Represents a 3-Dimensional Vector
  *
  * @param x X-Value
  * @param y Y-Value
  * @param z Z-Value
  */
case class Vector3F (var x: Float, var y: Float, var z: Float) extends Vector3[Float, Vector3F] {
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

  def +(vector: Vector3F): Vector3F = {
    Vector3F(x + vector.x, y + vector.y, z + vector.z)
  }

  def -(vector: Vector3F): Vector3F = {
    Vector3F(x - vector.x, y - vector.y, z - vector.z)
  }

  def *(v: Float): Vector3F = {
    Vector3F(x * v, y * v, z * v)
  }

  def /(v: Float): Vector3F = {
    Vector3F(x / v, y / v, z / v)
  }

  def px(v: Float): Vector3F = {
    Vector3F(x + v, y, z)
  }

  def py(v: Float): Vector3F = {
    Vector3F(x, y + v, z)
  }

  def pz(v: Float): Vector3F = {
    Vector3F(x, y, z + v)
  }

  def mag: Float = Math.sqrt (x*x + y*y + z*z).toFloat

  /**
    * Floors all components of the vector
    * @return An integer vector of all components floored
    */
  def floor: Vector3I = {
    Vector3I(x.floor toInt, y.floor toInt, z.floor toInt)
  }

  /**
    * Normalises the vector (makes the magnitude 1)
    */
  def normalize(): Unit = {
    val mag = Math.sqrt (x*x + y*y + z*z).toFloat

    x = x / mag
    y = y / mag
    z = z / mag
  }

  /**
    * Finds the block that the vector position is inside
    * @return A vector3 rounded to exactly block coordinates
    */
  def nearestBlock: Vector3I = {
    Vector3I(x.round, y.round, z.round)
  }

  /**
    * Dot product
    */
  def :*(v: Vector3F): Float = x * v.x + y * v.y + z * v.z

  /**
    * Cross product
    */
  def :+(v: Vector3F): Vector3F = Vector3F(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

  /**
    * Finds the angle between two vectors
    * @param v Vector to find angle between
    * @return Angle between vectors in radians
    */
  def angleBetween(v: Vector3F): Float = {
    Math.acos((this :* v) / (this.mag * v.mag)).asInstanceOf[Float] * signOf(this :* v)
  }

  /**
    * Finds the sign of a value
    * @param v Value input
    * @return 1 if positive or -1 if negative
    */
  protected def signOf(v: Float): Int = {
    if(v < 0) -1
    else 1
  }
}

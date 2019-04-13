package src

/**
  * Represents a 3-Dimensional Vector
  *
  * @param x X-Value
  * @param y Y-Value
  * @param z Z-Value
  */
case class Vector3 (var x: Float, var y: Float, var z: Float) {
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

  def +(vector: Vector3): Vector3 = {
    Vector3(x + vector.x, y + vector.y, z + vector.z)
  }

  def -(vector: Vector3): Vector3 = {
    Vector3(x - vector.x, y - vector.y, z - vector.z)
  }

  def *(v: Float): Vector3 = {
    Vector3(x * v, y * v, z * v)
  }

  def /(v: Float): Vector3 = {
    Vector3(x / v, y / v, z / v)
  }

  def %(v: Float): Vector3 = {
    Vector3(positive (x % v), positive (y % v), positive (z % v))
  }

  def px(v: Float): Vector3 = {
    Vector3(x + v, y, z)
  }

  def py(v: Float): Vector3 = {
    Vector3(x, y + v, z)
  }

  def pz(v: Float): Vector3 = {
    Vector3(x, y, z + v)
  }

  def floor: Vector3 = {
    Vector3(x.floor, y.floor, z.floor)
  }

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
  def nearestBlock: Vector3 = {
    Vector3(x.round, y.round, z.round)
  }

  /**
    * Dot product
    */
  def :*(v: Vector3): Float = x * v.x + y * v.y + z * v.z

  /**
    * Cross product
    */
  def :+(v: Vector3): Vector3 = Vector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

  def angleBetween(v: Vector3): Float = {
    Math.acos((this :* v) / (this.mag * v.mag)).asInstanceOf[Float] * signOf(this :* v)
  }

  def mag: Float = Math.sqrt (x*x + y*y + z*z).toFloat

  private def signOf(v: Float): Int = {
    if(v < 0) -1
    else 1
  }

  private val positive: Float => Float = (float: Float) => if (float < 0) positive (float + 16) else float
}

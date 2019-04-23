package src


/**
  * Represents a 3-Dimensional Vector
  *
  * @param x X-Value
  * @param y Y-Value
  * @param z Z-Value
  */
case class Vector3I (var x: Int, var y: Int, var z: Int) extends Vector3[Int, Vector3I] {
  /**
    * Creates a vector with all components equal to 0
    */
  def this() = this(0, 0, 0)

  /**
    * Creates a vector with all components the same value
    *
    * @param xyz Value to set X, Y and Z to
    */
  def this(xyz: Int) = this(xyz, xyz, xyz)

  def +(vector: Vector3I): Vector3I = {
    Vector3I(x + vector.x, y + vector.y, z + vector.z)
  }

  def -(vector: Vector3I): Vector3I = {
    Vector3I(x - vector.x, y - vector.y, z - vector.z)
  }

  def *(v: Float): Vector3F = {
    Vector3F(x * v, y * v, z * v)
  }

  def /(v: Float): Vector3F = {
    Vector3F(x / v, y / v, z / v)
  }

  def %(v: Int): Vector3I = {
    Vector3I(positive (x % v), positive (y % v), positive (z % v))
  }

  def px(v: Int): Vector3I = {
    Vector3I(x + v, y, z)
  }

  def py(v: Int): Vector3I = {
    Vector3I(x, y + v, z)
  }

  def pz(v: Int): Vector3I = {
    Vector3I(x, y, z + v)
  }

  /**
    * Dot product
    */
  def :*(v: Vector3I): Float = x * v.x + y * v.y + z * v.z

  /**
    * Cross product
    */
  def :+(v: Vector3I): Vector3I = Vector3I(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

  def angleBetween(v: Vector3I): Float = {
    Math.acos((this :* v) / (this.mag * v.mag)).asInstanceOf[Float] * signOf(this :* v)
  }

  def mag: Float = Math.sqrt (x*x + y*y + z*z).toFloat

  def toFloat: Vector3F = {
    Vector3F(x.toFloat, y.toFloat, z.toFloat)
  }

  protected def signOf(v: Float): Int = {
    if(v < 0) -1
    else 1
  }

  protected val positive: Int => Int = (int: Int) => if (int < 0) positive (int + 16) else int
}

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

  private val positive: Float => Float = (float: Float) => if (float < 0) positive (float + 16) else float
}

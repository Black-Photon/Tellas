package src.util

/**
  * Represents a 2-Dimensional Vector
  *
  * @param x X-Value
  * @param y Y-Value
  */
case class Vector2I (var x: Int, var y: Int) extends Vector2[Int, Vector2I] {
  /**
    * Creates a vector with all components equal to 0
    */
  def this() = this(0, 0)

  /**
    * Creates a vector with all components the same value
    *
    * @param xy Value to set X and Y to
    */
  def this(xy: Int) = this(xy, xy)

  def +(vector: Vector2I): Vector2I = {
    Vector2I(x + vector.x, y + vector.y)
  }

  def -(vector: Vector2I): Vector2I = {
    Vector2I(x - vector.x, y - vector.y)
  }

  def *(v: Float): Vector2F = {
    Vector2F(x * v, y * v)
  }

  def /(v: Float): Vector2F = {
    Vector2F(x / v, y / v)
  }

  def px(v: Int): Vector2I = {
    Vector2I(x + v, y)
  }

  def py(v: Int): Vector2I = {
    Vector2I(x, y + v)
  }

  def mag: Float = Math.sqrt (x*x + y*y).toFloat

  /**
    * Finds the modulus vector
    * @param v Value to modulo by
    * @return Vector with each component modulo-d
    */
  def %(v: Int): Vector2I = {
    Vector2I(positive (x % v), positive (y % v))
  }

  /**
    * Dot product
    */
  def :*(v: Vector2I): Float = x * v.x + y * v.y

  /**
    * Finds the angle between two vectors
    * @param v Vector to find angle between
    * @return Angle between vectors in radians
    */
  def angleBetween(v: Vector2I): Float = {
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

  /**
    * Converts to an Vector2F
    * @return The same vector as a Vector2F
    */
  def toFloat: Vector2F = {
    Vector2F(x.toFloat, y.toFloat)
  }

  /**
    * Makes the input the first positive number found by adding 16
    */
  protected val positive: Int => Int = (int: Int) => if (int < 0) positive (int + 16) else int
}

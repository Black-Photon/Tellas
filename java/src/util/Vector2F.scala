package src.util

/**
  * Represents a 2-Dimensional Vector
  *
  * @param x X-Value
  * @param y Y-Value
  */
case class Vector2F (var x: Float, var y: Float) extends Vector2[Float, Vector2F] {
  /**
    * Creates a vector with all components equal to 0
    */
  def this() = this(0, 0)

  /**
    * Creates a vector with all components the same value
    *
    * @param xy Value to set X and Y to
    */
  def this(xy: Float) = this(xy, xy)

  def +(vector: Vector2F): Vector2F = {
    Vector2F(x + vector.x, y + vector.y)
  }

  def -(vector: Vector2F): Vector2F = {
    Vector2F(x - vector.x, y - vector.y)
  }

  def *(v: Float): Vector2F = {
    Vector2F(x * v, y * v)
  }

  def /(v: Float): Vector2F = {
    Vector2F(x / v, y / v)
  }

  def px(v: Float): Vector2F = {
    Vector2F(x + v, y)
  }

  def py(v: Float): Vector2F = {
    Vector2F(x, y + v)
  }

  def mag: Float = Math.sqrt (x*x + y*y).toFloat

  /**
    * Floors all components of the vector
    * @return An integer vector of all components floored
    */
  def floor: Vector2I = {
    Vector2I(x.floor toInt, y.floor toInt)
  }

  /**
    * Ceilings all components of the vector
    * @return An integer vector of all components ceiling-d
    */
  def ceil: Vector2I = {
    Vector2I(x.ceil toInt, y.ceil toInt)
  }

  /**
    * Normalises the vector (makes the magnitude 1)
    * Saves result in vector
    */
  def normalize(): Unit = {
    if(x == 0 && y == 0) return

    val mag = Math.sqrt (x*x + y*y).toFloat

    x = x / mag
    y = y / mag
  }

  /**
    * Normalises the vector (makes the magnitude 1)
    * Returns value
    */
  def normalized(): Vector2F = {
    if(x == 0 && y == 0) return this

    val mag = Math.sqrt (x*x + y*y).toFloat

    Vector2F(x / mag, y / mag)
  }

  /**
    * Finds the block that the vector position is inside
    * @return A vector2 rounded to exactly block coordinates
    */
  def nearestBlock: Vector2I = {
    Vector2I(x.round, y.round)
  }

  /**
    * Dot product
    */
  def :*(v: Vector2F): Float = x * v.x + y * v.y

  /**
    * Finds the angle between two vectors
    * @param v Vector to find angle between
    * @return Angle between vectors in radians
    */
  def angleBetween(v: Vector2F): Float = {
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

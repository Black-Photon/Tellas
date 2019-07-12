package src.util

/**
  * Defines a vector of size 2
 *
  * @tparam A Type of Vector
  * @tparam R New Vector Type
  */
abstract class Vector2[A <: AnyVal, R]() {
  /**
    * Adds two vectors together
    * @param v Vector to add to this one
    * @return Result of adding two vectors
    */
  def +(v: R): R

  /**
    * Subtracts two vectors
    * @param v Vector to subtract from this one
    * @return Result of subtracting two vectors
    */
  def -(v: R): R

  /**
    * Multiplies the vector by a value
    * @param v Value to multiply this one by
    * @return Result of multiplying by v
    */
  def *(v: Float): Vector2F

  /**
    * Divides the vector by a value
    * @param v Value to divide this one by
    * @return Result of dividing by v
    */
  def /(v: Float): Vector2F

  /**
    * Adds a value to the x-direction of the vector
    * @param v Value to add
    * @return The resulting vector
    */
  def px(v: A): R

  /**
    * Adds a value to the y-direction of the vector
    * @param v Value to add
    * @return The resulting vector
    */
  def py(v: A): R

  /**
    * Finds the magnitude of the vector
    * @return Vector Magnitude
    */
  def mag: Float
}

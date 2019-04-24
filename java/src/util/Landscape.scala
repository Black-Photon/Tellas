package src.util

/**
  * A 3-dimensional landscape of data
 *
  * @tparam A Type of data to hold
  */
trait Landscape [A] {
  /**
    * Sets the position in the landscape to that value
    * @param content  Value to set to
    * @param vector3 Position to set
    */
  def set(content: A, vector3: Vector3I): Unit

  /**
    * Gets a value from the landscape
    * @param vector3 Location of value
    * @return Value at location
    */
  def get(vector3: Vector3I): A
}

package src

import src.util.Vector3F

/**
  * Indicates this block is able to collide with other blocks
  */
trait Collidable {
  /**
    * Position of the entity
    */
  protected var position: Vector3F
  /**
    * Height of the entity
    */
  val height: Float

  /**
    * Checks for collision on the positive x side
    * @return True if collision found
    */
  def xCollisionP: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position px 1 py (currentInt + 1) pz 0.5f floor)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  /**
    * Checks for collision on the positive y side
    * @return True if collision found
    */
  def yCollisionP: Boolean = {
    ChunkLoader.isBlock(position py (height + 1) px 0.5f pz 0.5f floor)
  }

  /**
    * Checks for collision on the positive z side
    * @return True if collision found
    */
  def zCollisionP: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position px 0.5f py (currentInt + 1) pz 1 floor)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  /**
    * Checks for collision on the negative x side
    * @return True if collision found
    */
  def xCollisionN: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position py (currentInt + 1) pz 0.5f floor)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  /**
    * Checks for collision on the negative y side
    * @return True if collision found
    */
  def yCollisionN: Boolean = {
    ChunkLoader.isBlock(ceilY (position) px 0.5f pz 0.5f floor)
  }

  /**
    * Checks for collision on the negative z side
    * @return True if collision found
    */
  def zCollisionN: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position px 0.5f py (currentInt + 1) floor)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  /**
    * Applies ceil to the y value of the input vector
    * @param v Vector to apply to
    * @return Output vector
    */
  def ceilY(v: Vector3F): Vector3F = {
    Vector3F(v.x, v.y.ceil, v.z)
  }
}

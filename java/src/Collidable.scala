package src

trait Collidable {
  protected var position: Vector3
  val height: Float

  def xCollisionP: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position px 1 py (currentInt + 1) pz 0.5f)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  def yCollisionP: Boolean = {
    ChunkLoader.isBlock(position py (height + 1) px 0.5f pz 0.5f)
  }

  def zCollisionP: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position px 0.5f py (currentInt + 1) pz 1)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  def xCollisionN: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position py (currentInt + 1) pz 0.5f)

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  def yCollisionN: Boolean = {
    ChunkLoader.isBlock(ceilY (position) px 0.5f pz 0.5f)
  }

  def zCollisionN: Boolean = {
    var currentInt: Int = 0

    while (currentInt < height) {
      val isThere = ChunkLoader.isBlock(position px 0.5f py (currentInt + 1))

      if(isThere) {
        return true
      }
      currentInt += 1
    }
    false
  }

  def ceilY(v: Vector3): Vector3 = {
    Vector3(v.x, v.y.ceil, v.z)
  }
}

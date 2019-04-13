package jni

import jni.Player.Direction.Direction
import src.{ChunkLoader, Collidable, Vector3}
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT, UP}

class Player extends Collidable {
  protected var position: Vector3 = new Vector3()
  val height = 1.8f
  var velocity = Vector3(0, 0, 0)
  val gravityStrength: Float = 0.01f
  val speed: Float = 7
  var verticalSpeed = 0.0f

  def frame(deltaT: Float): Unit = {
    if (yCollisionN) {
      if (verticalSpeed < 0) verticalSpeed = 0
    } else verticalSpeed -= gravityStrength
    if(verticalSpeed <= -2) verticalSpeed = -2

    if (yCollisionP && verticalSpeed > 0) verticalSpeed = 0

    setPosition(position py verticalSpeed)

    if (yCollisionN) {
      position.y = position.nearestBlock.y
    }

  }

  def getPosition: Vector3 = {
    position
  }

  def setPosition(vector: Vector3): Unit = {
    position = vector
    setPositionN(vector.x, vector.y + height, vector.z)
  }

  def moveDirection(direction: Direction, speedMod: Float): Unit = {
    val lookDirection = Vector3(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)

    if(direction == FORWARD || direction == BACKWARDS) {
      var moveDirection = lookDirection.copy()

      moveDirection.y = 0

      moveDirection.normalize()

      if(direction == BACKWARDS) moveDirection *= -1

      if(moveDirection.x > 0 && xCollisionP) moveDirection.x = 0
      if(moveDirection.x < 0 && xCollisionN) moveDirection.x = 0

      if(moveDirection.z > 0 && zCollisionP) moveDirection.z = 0
      if(moveDirection.z < 0 && zCollisionN) moveDirection.z = 0

      setPosition(position + moveDirection * speed * speedMod)
    }

    if(direction == LEFT || direction == RIGHT) {
      var moveDirection = lookDirection.copy()

      moveDirection.y = 0

      moveDirection.normalize()

      moveDirection = moveDirection :+ Vector3(0, 1, 0)

      if(direction == LEFT) moveDirection *= -1

      if(moveDirection.x > 0 && xCollisionP) moveDirection.x = 0
      if(moveDirection.x < 0 && xCollisionN) moveDirection.x = 0

      if(moveDirection.z > 0 && zCollisionP) moveDirection.z = 0
      if(moveDirection.z < 0 && zCollisionN) moveDirection.z = 0

      setPosition(position + moveDirection * speed * speedMod)
    }

    if(direction == UP) {
      if(yCollisionN) verticalSpeed = 0.15f
    }
  }

  def getLookBlockPosition: Option[Vector3] = {
    val lookDirection = Vector3(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)
    val camera = position py height
    lookDirection.normalize()

    for (i <- 2 to 160) {
      val checkPos = (camera + lookDirection * (i.toFloat / 16.0f)).nearestBlock
      if(ChunkLoader.isBlock(checkPos)) {
        return Some(checkPos)
      }
    }
    None
  }

  def getNewBlockPosition: Option[Vector3] = {
    val lookDirection = Vector3(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)
    val camera = position py height
    lookDirection.normalize()

    for (i <- 20 to 160) {
      val checkPos = (camera + lookDirection * (i.toFloat / 16.0f)).nearestBlock
      if(checkPos != position.nearestBlock)

      if(ChunkLoader.isBlock(checkPos)) {
        val vector = checkPos - (camera + lookDirection * (i.toFloat / 16.0f))

        val x: Float = Math.abs(vector.x)
        val y: Float = Math.abs(vector.y)
        val z: Float = Math.abs(vector.z)

        if(x > y && x > z) {
          if (vector.x < 0) {
            return Some (checkPos px 1)
          } else {
            return Some (checkPos px -1)
          }
        } else

        if(y > x && y > z) {
          if (vector.y < 0) {
            return Some (checkPos py 1)
          } else {
            return Some (checkPos py -1)
          }
        } else

        if(z > x && z > y) {
          if (vector.z < 0) {
            return Some (checkPos pz 1)
          } else {
            return Some (checkPos pz -1)
          }
        } else

          throw new RuntimeException("No Block Face Found")
      }
    }
    None
  }

  @native private def setPositionN(x: Float, y: Float, z: Float): Unit
  @native private def getLookingDirectionX: Float
  @native private def getLookingDirectionY: Float
  @native private def getLookingDirectionZ: Float
}

object Player {
  object Direction extends Enumeration {
    type Direction = Value

    val FORWARD   = Value(0)
    val LEFT      = Value(1)
    val BACKWARDS = Value(2)
    val RIGHT     = Value(3)
    val UP        = Value(4)
  }
}
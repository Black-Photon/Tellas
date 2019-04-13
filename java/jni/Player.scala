package jni

import jni.Player.Direction.Direction
import src.{Collidable, Vector3}
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT}

class Player extends Collidable {
  protected var position: Vector3 = new Vector3()
  val height = 1.8f
  var velocity = Vector3(0, 0, 0)
  val gravityStrength: Float = 0.3f
  val speed: Float = 7

  def frame(deltaT: Float): Unit = {
    if (!yCollisionN) setPosition(position py -gravityStrength)
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

    if(direction == FORWARD) {
      val moveDirection = lookDirection.copy()

      moveDirection.y = 0

      moveDirection.normalize()

      if(moveDirection.x > 0 && xCollisionP) moveDirection.x = 0
      if(moveDirection.x < 0 && xCollisionN) moveDirection.x = 0

      if(moveDirection.z > 0 && zCollisionP) moveDirection.z = 0
      if(moveDirection.z < 0 && zCollisionN) moveDirection.z = 0

      setPosition(position + moveDirection * speed * speedMod)
    }
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
  }
}
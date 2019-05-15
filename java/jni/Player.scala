package jni

import jni.Player.Direction.Direction
import src.{ChunkLoader, Collidable}
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT, UP}
import jni.Player.Axis.{Axis, X, Y, Z}
import src.util.{Vector3F, Vector3I}

/**
  * Represents the player
  */
class Player extends Collidable {
  // Feet Position of player
  protected var position: Vector3F = new Vector3F()
  // Height
  val height = 1.8f
  // Gravity strength
  val gravityStrength: Float = 0.75f
  // Speed of all movement
  val speed: Float = 6
  // Current vertical speed
  var verticalSpeed = 0.0f
  // Max fall speed
  val maxFall = 1.867219f

  /**
    * Moves the player forward one frame
    * @param deltaT Time since last frame
    */
  def frame(deltaT: Float): Unit = {
    // If block below, vertical speed = 0
    if (yCollisionN) {
      if (verticalSpeed < 0) verticalSpeed = 0
    } else verticalSpeed -= gravityStrength * deltaT
    // Max fall speed
    if(verticalSpeed <= -maxFall) verticalSpeed = -maxFall

    // If Moving up and block above
    if (yCollisionP && verticalSpeed > 0) verticalSpeed = 0

    // Applies velocity
    setPosition(position py verticalSpeed)

    // In case slightly in block, moves out
    if (yCollisionN) {
      position.y = position.nearestBlock.y
    }

  }

  /**
    * Gets the position of the players feet
    * @return Player position
    */
  def getPosition: Vector3F = {
    position
  }

  /**
    * Sets the position of the players feet
    * @param vector New player position
    */
  def setPosition(vector: Vector3F): Unit = {
    position = vector
    setPositionN(vector.x, vector.y + height, vector.z)
  }

  /**
    * Moves in the specified direction by the given relative speed
    * @param direction Direction to move
    * @param speedMod How fast to move (multiplied by player speed)
    */
  def moveDirection(direction: Direction, speedMod: Float): Unit = {
    val lookDirection = Vector3F(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)

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

      moveDirection = moveDirection :+ Vector3F(0, 1, 0)

      if(direction == LEFT) moveDirection *= -1

      if(moveDirection.x > 0 && xCollisionP) moveDirection.x = 0
      if(moveDirection.x < 0 && xCollisionN) moveDirection.x = 0

      if(moveDirection.z > 0 && zCollisionP) moveDirection.z = 0
      if(moveDirection.z < 0 && zCollisionN) moveDirection.z = 0

      setPosition(position + moveDirection * speed * speedMod)
    }

    if(direction == UP) {
      if(yCollisionN) verticalSpeed = 0.25f
    }
  }

  /**
    * Gets the block the player is looking at
    * @return Block player looking at (Or None if none found or raycast fails)
    */
  def getLookBlockPosition: Option[Vector3I] = {
    val lookDirection = Vector3F(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)
    val camera = position py height

    // If inside block, destroy it
    if(ChunkLoader.isBlock(camera nearestBlock)) {
      return Some(camera nearestBlock)
    }

    val block = raycastBlock()
    val location: Option[Vector3I] = block match {
      case Some((v, _)) => Some(v)
      case None => None
    }

    location
  }

  /**
    * Gets the block before the block the player is looking at
    * @return New block vector (Or None if none found or raycast fails)
    */
  def getNewBlockPosition: Option[Vector3I] = {
    val lookDirection = Vector3F(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)

    // Performs a raycast to find block looking at
    val block = raycastBlock()
    val location: Option[Vector3I] = block match {
      case Some((v, _)) => Some(v)
      case None => None
    }
    val axis: Option[Axis] = block match {
      case Some((_, a)) => Some(a)
      case None => None
    }

    // Finds the block before that
    for{
      v <- getFromAxis(axis)(lookDirection)
      here <- location
      last <- addAxis(here toFloat)(axis)(-1 * sign(v))
    } yield last.nearestBlock
  }

  /**
    * Performs a Raycast of blocks to find the first block the player is looking at and which axis of the block they are looking at
    * @return Tuple of block looking at and axis looking at (Or None if none found or raycast fails)
    */
  def raycastBlock(): Option[(Vector3I, Axis)] = {
    // Direction looking in
    val lookDirection = Vector3F(getLookingDirectionX, getLookingDirectionY, getLookingDirectionZ)
    val camera = position py height
    lookDirection.normalize()

    var current = camera.nearestBlock

    // Checks up to 10 blocks from the player
    for (i <- 0 to 10) {
      // Finds the planes which we are checking for intersects with (Block location +- 0.5)
      val x = current.x + (if(lookDirection.x < 0) -0.5f else 0.5f)
      val y = current.y + (if(lookDirection.y < 0) -0.5f else 0.5f)
      val z = current.z + (if(lookDirection.z < 0) -0.5f else 0.5f)

      // Finds the length of the raycast needed to meet that plane
      val cx = assertPositive(if(lookDirection.x != 0) Some((x-camera.x) / lookDirection.x) else None)
      val cy = assertPositive(if(lookDirection.y != 0) Some((y-camera.y) / lookDirection.y) else None)
      val cz = assertPositive(if(lookDirection.z != 0) Some((z-camera.z) / lookDirection.z) else None)

      // Finds the plane which is first collided with
      val axis = reduce(for {
        // Finds magnitudes
        mx <- cx.map(mag)
        my <- cy.map(mag)
        mz <- cz.map(mag)

        // Gets the lowest
        val lowest = mx.min(my.min(mz))

        // Turns this into an axis
        val axis: Option[Axis] = lowest match {
          case `mx` => Some(X)
          case `my` => Some(Y)
          case `mz` => Some(Z)
          case _ => None
        }
      } yield axis)

      // Sets the current block to the next one along the ray
      current = (for {
        iaxis <- axis

        // Finds the actual vector of the new blocks using current +- 1
        val next = iaxis match {
          case X => if (lookDirection.x < 0) {
            current px -1
          } else {
            current px 1
          }
          case Y => if (lookDirection.y < 0) {
            current py -1
          } else {
            current py 1
          }
          case Z => if (lookDirection.z < 0) {
            current pz -1
          } else {
            current pz 1
          }
        }
      } yield next) match {
        case Some(v) => v
        case None => return None
      }

      // Returns this block
      if(ChunkLoader.isBlock(current)) {
        return for{
          plane <- axis
        } yield (current, plane)
      }
    }

    // Vacuously fails
    None
  }

  /**
    * Finds the magnitude of the input
    */
  val mag: Float => Float = i => if(i < 0) -i else i

  /**
    * Finds the sign of the input
    */
  val sign: Float => Float = i => if(i < 0) -1 else 1

  /**
    * Asserts that the given float is positive, returning None if not
    */
  val assertPositive: Option[Float] => Option[Float] = {
    case Some(i) => if (i > 0) Some(i) else None
    case None => None
  }

  /**
    * Adds a value to the given axis of the given vector, returning none if no axis
    */
  val addAxis: Vector3F => Option[Axis] => Float => Option[Vector3F] = vector => axis => v => axis match {
    case Some(X)    => Some(vector px v)
    case Some(Y)    => Some(vector py v)
    case Some(Z)    => Some(vector pz v)
    case None       => None
  }

  /**
    * Gets a value from the axis component of the given vector
    */
  val getFromAxis: Option[Axis] => Vector3F => Option[Float] = {
    case Some(X) => vector => Some(vector.x)
    case Some(Y) => vector => Some(vector.y)
    case Some(Z) => vector => Some(vector.z)
    case None    => _      => None
  }

  /**
    * Reduces a double option to an option
    * @tparam A Type of option
    * @return a singular option
    */
  def reduce[A]: Option[Option[A]] => Option[A] = {
    case Some(a) => a
    case None => None
  }

  def lookDirection = {
    val array = getLookingDirectionN
    Vector3F(array(0), array(1), array(2))
  }

  /**
    * Sets the position of the camera on the native side
    * @param x x-location
    * @param y y-location
    * @param z z-location
    */
  @native private def setPositionN(x: Float, y: Float, z: Float): Unit

  /**
    * Finds the x-component of the vector looking in
    * @return X-Component
    */
  @native def getLookingDirectionX: Float

  /**
    * Finds the y-component of the vector looking in
    * @return Y-Component
    */
  @native def getLookingDirectionY: Float

  /**
    * Finds the z-component of the vector looking in
    * @return Z-Component
    */
  @native def getLookingDirectionZ: Float

  /**
    * Finds the direction of the vector looking in
    * @return Array of x y z
    */
  @native def getLookingDirectionN: Array[Float]
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

  object Axis extends Enumeration {
    type Axis = Value

    val X       = Value(0)
    val Y       = Value(1)
    val Z       = Value(2)
  }
}
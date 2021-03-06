package jni

import jni.Projection.Projection
import jni.Rotation.Rotation
import src.util.Vector3F

class Camera(width: Int, height: Int) {
  val id: Int = createCamera(width, height)

  /**
    * Finds the direction of the vector looking in
    * @return Array of x y z
    */
  def getLookingDirection: Vector3F = {
    val lookDirectionArray: Array[Float] = getLookingDirectionN(id)

    Vector3F(lookDirectionArray(0), lookDirectionArray(1), lookDirectionArray(2))
  }

  /**
    * Sets the position of the camera on the native side
    * @param x x-location
    * @param y y-location
    * @param z z-location
    */
  def setPosition(x: Float, y: Float, z: Float): Unit = setPositionN(x, y, z, id)

  /**
    * Sets the position of the camera on the native side
    * @param pos New Position
    */
  def setPosition(pos: Vector3F): Unit = setPositionN(pos.x, pos.y, pos.z, id)

  /**
    * Finds the current location of the camera
    * @return Camera location
    */
  def getPosition: Vector3F = {
    val positionArray: Array[Float] = getPositionN(id)

    Vector3F(positionArray(0), positionArray(1), positionArray(2))
  }

  /**
    * Finds the x-component of the vector looking in
    * @return X-Component
    */
  def getLookingDirectionX: Float = getLookingDirectionX(id)

  /**
    * Finds the y-component of the vector looking in
    * @return Y-Component
    */
  def getLookingDirectionY: Float = getLookingDirectionY(id)

  /**
    * Finds the z-component of the vector looking in
    * @return Z-Component
    */
  def getLookingDirectionZ: Float = getLookingDirectionZ(id)

  /**
    * Whether to use orthographic or perspective Projection Matrix
    * @param projectonType Type of Projection Matrix
    */
  def setProjectionType(projectonType: Projection): Unit = setProjectionTypeN(projectonType.id, id)

  /**
    * Rotates the camera by an angle
    * @param rotation Which way to rotate
    * @param angle Angle in degrees
    */
  def rotate(rotation: Rotation, angle: Int): Unit = rotateN(rotation.id, angle, id)

  /**
    * Sets the camera rotation to an angle
    * @param rotation Rotation being set
    * @param angle Angle in degrees
    */
  def setRotation(rotation: Rotation, angle: Int): Unit = setRotationN(rotation.id, angle, id)

  /**
    * Whether Pitch should be restricted to -90 to 90 degrees
    * @param lock Whether to lock pitch
    */
  def lockPitch(lock: Boolean): Unit = lockPitchN(lock, id)

  /**
    * Inverts the camera (upside-down)
    * @param inverted Should invert
    */
  def invert(inverted: Boolean): Unit = invertN(inverted, id)

  /**
    * Creates a new camera, returning the ID of it
    * @return Id of camera created
    */
  @native private def createCamera(width: Int, height: Int): Int

  // Here follow the Native methods for the above
  // The id is the id of the camera in all cases
  @native private def setPositionN(x: Float, y: Float, z: Float, id: Int): Unit

  @native private def getLookingDirectionX(id: Int): Float

  @native private def getLookingDirectionY(id: Int): Float

  @native private def getLookingDirectionZ(id: Int): Float

  @native private def getLookingDirectionN(id: Int): Array[Float]

  @native private def getPositionN(id: Int): Array[Float]

  @native private def setProjectionTypeN(pType: Int, id: Int)

  @native private def rotateN(rotation: Int, angle: Int, id: Int)

  @native private def setRotationN(rotation: Int, angle: Int, id: Int)

  @native private def lockPitchN(lock: Boolean, id: Int)

  @native private def invertN(inverted: Boolean, id: Int)
}

/**
  * Represents different types of projection
  */
object Projection extends Enumeration {
  type Projection = Value
  val ORTHOGRAPHIC: Projection = Value(0)
  val PERSPECTIVE: Projection = Value(1)
}

/**
  * Represents different types of rotation
  */
object Rotation extends Enumeration {
  type Rotation = Value
  val PITCH: Rotation = Value(0)
  val YAW: Rotation = Value(1)
}
package jni

import src.util.{Vector3F, Vector3I}

class SkyBox extends Shape{
  /**
    * Draws the shape to the skybox relative to the player
    *
    * @param position Player Position
    */
  def draw(position: Vector3F, angle: Float, distance: Float): Unit = {
    drawN(position.x, position.y, position.z, angle, distance)
  }

  /**
    * Binds the model buffer for use. Call once when the model is first used
    */
  def bindBuffer(): Unit = {
    bindBufferN()
  }

  /**
    * Activates the skybox shader for use - quite slow
    * @param camera Camera to view from perspective of
    */
  def activateShader(camera: Camera): Unit = activateShader(camera.id)

  /**
    * Native call to draw to the screen
    *
    * @param x     X-Coordinate of player
    * @param y     Y-Coordinate of player
    * @param z     Z-Coordinate of player
    * @param angle Angle from player
    * @param distance Distance from player
    */
  @native private def drawN(x: Float, y: Float, z: Float, angle: Float, distance: Float): Unit
  @native private def bindBufferN(): Unit
  @native private def activateShader(camera: Int): Unit
}

object SkyBox extends SkyBox

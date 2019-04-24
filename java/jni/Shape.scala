package jni

import src.block.Model
import src.util.Vector3I

/**
  * Defines a shape that can be drawn to the screen
  */
class Shape {

  /**
    * Draws the shape to the screen using the given model
    *
    * @param model Model to use to draw to the screen
    */
  def draw(model: Model, position: Vector3I): Unit = {
    drawN(position.x, position.y, position.z, model.ordinal)
  }

  /**
    * Native call to draw to the screen
    *
    * @param x     X-Coordinate to draw to
    * @param y     Y-Coordinate to draw to
    * @param z     Z-Coordinate to draw to
    * @param model Model reference to draw
    */
  @native private def drawN(x: Float, y: Float, z: Float, model: Int): Unit

  /**
    * Binds the model buffer for use. Call once when the model is first used
    *
    * @param model Model to bind
    */
  def bindBuffer(model: Model): Unit = {
    bindBufferN(model.ordinal)
  }

  /**
    * Native call to bind
    *
    * @param model Model reference to bind
    */
  @native private def bindBufferN(model: Int): Unit

  /**
    * Activates the shader for use - quite slow
    */
  @native def activateShader(): Unit
}

object Shape extends Shape
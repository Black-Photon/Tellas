package jni

/**
  * Defines a shape that can be drawn to the screen
  */
class Shape {
  /**
    * Location of the shape
    */
  var position: Vector3 = new Vector3()

  /**
    * Creates a shape at the given position
    *
    * @param position Position to create shape at
    */
  def this(position: Vector3) {
    this()
    this.position = position
  }

  /**
    * Draws the shape to the screen using the given model
    *
    * @param model Model to use to draw to the screen
    */
  def draw(model: Model): Unit = {
    drawN(position.x / 2, position.y / 2, position.z / 2, model.ordinal)
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
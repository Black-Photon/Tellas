package jni

import jni.Cube.Side.Side
import src.util.Vector3I

class Cube extends Shape {
  /**
    * Draws the shape to the screen using the given model
    *
    * @param position 3D location to draw the cube
    */
  def draw(position: Vector3I): Unit = {
    draw(Model.CUBE, position)
  }

  /**
    * Draws the shape to the screen using the given model
    *
    * @param position 3D location to draw the cube
    * @param side Side of face to draw
    */
  def drawFace(position: Vector3I, side: Side): Unit = {
    drawFaceN(position.x, position.y, position.z, side.id)
  }

  /**
    * Native call to draw to the screen
    *
    * @param x     X-Coordinate to draw to
    * @param y     Y-Coordinate to draw to
    * @param z     Z-Coordinate to draw to
    * @param face  Model reference to draw
    */
  @native private def drawFaceN(x: Float, y: Float, z: Float, face: Int): Unit

  @native private def activateShaderN(angle: Float, camera: Int): Unit

  /**
    * Activates the cube shader for use - quite slow
    */
  def activateShader(angle: Float, camera: Camera): Unit = activateShaderN(angle, camera.id)
}

object Cube extends Cube {
  object Side extends ComplexEnum {
    type Side = ComplexVal

    val FRONT: Side       = new ComplexVal(0) {
      override def opposite: Side = BACK
    }
    val BACK: Side        = new ComplexVal(1) {
      override def opposite: Side = FRONT
    }
    val LEFT: Side        = new ComplexVal(2) {
      override def opposite: Side = RIGHT
    }
    val RIGHT: Side       = new ComplexVal(3) {
      override def opposite: Side = LEFT
    }
    val BOTTOM: Side      = new ComplexVal(4) {
      override def opposite: Side = TOP
    }
    val TOP: Side         = new ComplexVal(5) {
      override def opposite: Side = BOTTOM
    }

    val ALL: List[Side] = List(FRONT, BACK, LEFT, RIGHT, BOTTOM, TOP)
  }
}
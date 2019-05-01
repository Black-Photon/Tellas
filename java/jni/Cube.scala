package jni

import jni.Cube.Side.Side
import src.block.Model
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
}

object Cube extends Cube {
  object Side extends Enumeration {
    type Side = Value

    val FRONT       = Value(0)
    val BACK        = Value(1)
    val LEFT        = Value(2)
    val RIGHT       = Value(3)
    val BOTTOM      = Value(4)
    val TOP         = Value(5)

    val ALL: List[Side] = List(FRONT, BACK, LEFT, RIGHT, BOTTOM, TOP)
  }
}
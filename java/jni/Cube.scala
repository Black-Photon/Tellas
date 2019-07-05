package jni

import java.util

import jni.Cube.Side.Side
import src.util.Vector3I

class Cube extends Shape {
  /**
    * Draws the shape to the screen using the given model
    *
    * @param position 3D location to draw the cube
    */
  def draw(position: Vector3I, shader: Shader): Unit = {
    draw(Model.CUBE, position, shader)
  }

  /**
    * Draws the shape to the screen using the given model
    *
    * @param position 3D location to draw the cube
    * @param side Side of face to draw
    */
  def drawFace(position: Vector3I, side: Side, shader: Shader): Unit = {
    drawFaceN(position.x, position.y, position.z, side.id, shader.id)
  }

  def drawFaceMany(position: List[Vector3I], side: Side): Unit = {
    val array: Array[Int] = position.toArray.flatMap(v => Array(v.x, v.y, v.z))
    drawFaceManyN(array, array.length, side.id)
  }

  /**
    * Native call to draw to the screen
    *
    * @param x     X-Coordinate to draw to
    * @param y     Y-Coordinate to draw to
    * @param z     Z-Coordinate to draw to
    * @param face  Model reference to draw
    */
  @native private def drawFaceN(x: Float, y: Float, z: Float, face: Int, shader: Int): Unit

  @native private def drawFaceManyN(positions: Array[Int], elements: Int, face: Int): Unit
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
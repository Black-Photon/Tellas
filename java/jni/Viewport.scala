package jni

class Viewport {
  /**
    * Draws the bound image to the screen
    * @param x X-Coord
    * @param y Y-Coord
    * @param width Width of image
    * @param height Height of image
    */
  @native def drawImage(x: Int, y: Int, width: Int, height: Int)
}

object Viewport extends Viewport

package jni

class Viewport {
  @native def drawImage(x: Int, y: Int, width: Int, height: Int)
}

object Viewport extends Viewport

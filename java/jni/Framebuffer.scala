package jni

import src.util.Types.Texture

/**
  * Creates a framebuffer of given parameters
  *
  * @param width Width of framebuffer
  * @param height Height of framebuffer
  * @param rw Whether to Read/Write colour to framebuffer
  */
class Framebuffer (width: Int, height: Int, rw: Boolean){
  val fbo: Int = setupN(width, height, rw)
  val texture: Texture = getTextureIDN
  /**
    * Sets up a framebuffer - Call before anything else
    * Call getTextureID immediately after for texture
    * @param width Width of framebuffer
    * @param height Height of framebuffer
    * @param rw Whether to Read/Write colour to framebuffer
    * @return The framebuffer ID
    */
  @native private def setupN(width: Int, height: Int, rw: Boolean): Int
  /**
    * Gets texture ID from framebuffer created using setup
    * Call straight after setup
    * @return ID of texture
    */
  @native private def getTextureIDN: Int
  @native private def startN(width: Int, height: Int, FBO: Int): Unit
  @native private def endN: Unit

  /**
    * Start Drawing to Framebuffer
    */
  def start(): Unit = {
    startN(width, height, fbo)
  }

  /**
    * Finish drawing to framebuffer
    */
  def end(): Unit = {
    endN
  }
}
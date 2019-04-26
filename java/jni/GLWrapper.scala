package jni

class GLWrapper {
  /**
    * Tests JNI link
    */
  @native def test(): Unit

  /**
    * Sets up the classes necessary for OpenGL to function
    *
    * @param width  Window Width
    * @param height Window height
    * @param title  Window title
    */
  @native def preInit(width: Int, height: Int, title: String): Unit

  /**
    * Initialises any data required for the application to function
    * @param capture Capture mouse or not
    */
  @native def init(capture: Boolean): Unit

  /**
    * Checks if the application should close
    *
    * @return True if application should close
    */
  @native def shouldClose: Boolean

  /**
    * Closes the application
    */
  @native def close(): Unit

  // Called Each Frame

  /**
    * Calculates Delta T
    *
    * @return Time since last frame
    */
  @native def deltaT: Float

  /**
    * Processes input
    *
    * @param deltaT Time since last frame
    */
  @native def processInput(deltaT: Float): Unit

  /**
    * Pre-renders the screen
    *
    * @param r Red percent (0 to 1)
    * @param g Green percent (0 to 1)
    * @param b Blue percent (0 to 1)
    */
  @native def prerender(r: Float, g: Float, b: Float): Unit


  /**
    * Post-renders the screen
    */
  @native def postrender(): Unit

  /**
    * Swaps the buffers
    */
  @native def swapBuffers(): Unit

  /**
    * Polls for events
    */
  @native def pollEvents(): Unit

  /**
    * Generates a texture returning its reference number
    *
    * @param texture Texture location relative to assets
    * @param isPNG   True if the texture has an alpha channel (eg. is a PNG)
    *                (If the texture isn't rendering correctly, but still shows an image, try toggling this)
    * @return Reference number of texture created
    */
  @native def generateTexture(texture: String, isPNG: Boolean): Int

  /**
    * Sets the texture as the primary texture in a certain texture slot to be used by the shader3d
    *
    * @param texture  Texture reference number
    * @param location Location to store texture for rendering (0 to 31)
    */
  @native def useTexture(texture: Int, location: Int): Unit

  /**
    * Checks if an error has been recorded, printing it to the screen
    */
  @native def checkError(): Unit
}

object GLWrapper extends GLWrapper
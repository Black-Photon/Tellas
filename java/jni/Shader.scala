package jni

import src.util.Vector3F

/**
  * Creates a shader using fragment and vertex shaders specified
  * Do not add extensions or path
  * All shaders in shader folder specified in data.cpp
 *
  * @param vertex Vertex name - eg. shader3d
  * @param fragment Fragment name - eg. shadow
  */
class Shader(vertex: String, fragment: String) {
  val id: Int = createShaderN(vertex + ".vert", fragment + ".frag")

  /**
    * Sets the current shader to be active
    */
  def useShader(): Unit = useShaderN(id)
  /**
    * Set an integer uniform OR texture ref
    * @param name Uniform name
    * @param value Value to set to
    */
  def setInt(name: String, value: Int): Unit = setIntN(name, value, id)
  /**
    * Set an floating point uniform
    * @param name Uniform name
    * @param value Value to set to
    */
  def setFloat(name: String, value: Float): Unit = setFloatN(name, value, id)
  /**
    * Set an boolean uniform
    * @param name Uniform name
    * @param value Value to set to
    */
  def setBool(name: String, value: Boolean): Unit = setBoolN(name, value, id)
  /**
    * Set a vector uniform
    * @param name Uniform name
    * @param x x-coord
    * @param y y-coord
    * @param z z-coord
    */
  def setVec3(name: String, x: Float, y: Float, z: Float): Unit = setVec3N(name, x, y, z, id)
  /**
    * Set a vector uniform
    * @param name Uniform name
    * @param value Value to set to
    */
  def setVec3(name: String, value: Vector3F): Unit = setVec3N(name, value.x, value.y, value.z, id)
  /**
    * Sets the ''view'' and ''projection'' matrices for the shader to that of the camera specified
    * @param camera View to build matrices from
    */
  def makeModel(camera: Camera): Unit = makeModelN(id, camera.id)
  /**
    * Simulates a camera, condensing the ''view'' and ''projection'' matrices into a single matrix uniform specified
    * Use for example to check where something would be rendered
    * @param name Uniform name
    * @param camera View to build matrix from
    */
  def simulateCamera(name: String, camera: Camera) = simulateCameraN(name, camera.id, id)

  // Accompanying Native methods
  @native private def createShaderN(vertex: String, fragment: String): Int
  @native private def useShaderN(id: Int)
  @native private def setIntN(name: String, value: Int, id: Int)
  @native private def setFloatN(name: String, value: Float, id: Int)
  @native private def setBoolN(name: String, value: Boolean, id: Int)
  @native private def setVec3N(name: String, v1: Float, v2: Float, v3: Float, id: Int)
  @native private def makeModelN(id: Int, camera: Int)
  @native private def simulateCameraN(name: String, camera: Int, id: Int)
}

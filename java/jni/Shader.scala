package jni

class Shader(vertex: String, fragment: String) {
  val id: Int = createShaderN(vertex + ".vert", fragment + ".frag")

  def useShader(): Unit = useShaderN(id)
  def setInt(name: String, value: Int): Unit = setIntN(name, value, id)
  def setFloat(name: String, value: Float): Unit = setFloatN(name, value, id)
  def setBool(name: String, value: Boolean): Unit = setBoolN(name, value, id)
  def setVec3(name: String, v1: Float, v2: Float, v3: Float): Unit = setVec3N(name, v1, v2, v3, id)
  def makeModel(camera: Camera): Unit = makeModelN(id, camera.id)
  def simulateCamera(name: String, camera: Camera) = simulateCameraN(name, camera.id, id)

  @native private def createShaderN(vertex: String, fragment: String): Int
  @native private def useShaderN(id: Int)
  @native private def setIntN(name: String, value: Int, id: Int)
  @native private def setFloatN(name: String, value: Float, id: Int)
  @native private def setBoolN(name: String, value: Boolean, id: Int)
  @native private def setVec3N(name: String, v1: Float, v2: Float, v3: Float, id: Int)
  @native private def makeModelN(id: Int, camera: Int)
  @native private def simulateCameraN(name: String, camera: Int, id: Int)
}

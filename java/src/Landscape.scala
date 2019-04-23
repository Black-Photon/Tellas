package src

trait Landscape [A] {
  def set(content: A, vector3: Vector3I): Unit

  def get(vector3: Vector3I): A
}

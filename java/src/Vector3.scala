package src

abstract class Vector3[A <: AnyVal, R] () {
  def +(vector: R): R

  def -(vector: R): R

  def *(v: Float): Vector3F

  def /(v: Float): Vector3F

  def px(v: A): R

  def py(v: A): R

  def pz(v: A): R

  def mag: Float
}

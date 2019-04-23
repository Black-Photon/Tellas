package src

import scala.collection.mutable

class HashLandscape[A >: Null] extends Landscape[A] {
  val landscape: mutable.HashMap[Int, mutable.HashMap[Int, mutable.HashMap[Int, A]]] = mutable.HashMap()

  override def set(content: A, vector3: Vector3I): Unit = {
    val x = vector3.x.floor.asInstanceOf[Int]
    val y = vector3.y.floor.asInstanceOf[Int]
    val z = vector3.z.floor.asInstanceOf[Int]

    // If x-layer already has this value
    if(landscape.contains(x)) {
      val yLayer = landscape.apply(x)
      if(yLayer.contains(y)) {
        val zLayer = yLayer.apply(y)
        zLayer += (z -> content)
      } else {
        yLayer += (y -> mutable.HashMap((z, content)))
      }
    } else {
      landscape += (x -> mutable.HashMap((y, mutable.HashMap((z, content)))))
    }
  }

  def is(vector3: Vector3I): Boolean = {
    val x = vector3.x.floor.asInstanceOf[Int]
    val y = vector3.y.floor.asInstanceOf[Int]
    val z = vector3.z.floor.asInstanceOf[Int]

    // If x-layer already has this value
    if(landscape.contains(x)) {
      val yLayer = landscape.apply(x)
      if(yLayer.contains(y)) {
        val zLayer = yLayer.apply(y)
        if(zLayer.contains(z)) {
          true
        } else {
          false
        }
      } else {
        false
      }
    } else {
      false
    }
  }

  override def get(vector3: Vector3I): A = {
    val x = vector3.x.floor.asInstanceOf[Int]
    val y = vector3.y.floor.asInstanceOf[Int]
    val z = vector3.z.floor.asInstanceOf[Int]

    if(is(vector3)) {
      landscape(x)(y)(z)
    } else {
      null
    }
  }
}

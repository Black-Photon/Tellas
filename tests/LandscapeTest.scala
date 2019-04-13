import src.{Landscape, Vector3}

object LandscapeTest extends App {
  setGetTest()
  isTest()

  def setGetTest(): Unit = {
    val landscape = new Landscape[Int]
    landscape.set(10, Vector3(1, 2, 3))

    val get = landscape.get(Vector3(1, 2, 3))
    get match {
      case None     => assert(assertion = false, "Expected: 10, Found: None")
      case Some(10) => Unit
      case Some(a)  => assert(assertion = false, "Expected: 10, Found: " + a)
    }

    val get2 = landscape.get(Vector3(1, 2, 2))
    get2 match {
      case None     => Unit
      case Some(a)  => assert(assertion = false, "Expected: None, Found: " + a)
    }
  }

  def isTest(): Unit = {
    val landscape = new Landscape[Int]
    landscape.set(10, Vector3(1, 2, 3))

    val is = landscape.is(Vector3(1, 2, 3))
    assert(is, "Expected: true, Found: false")


    val is2 = landscape.is(Vector3(1, 2, 2))
    assert(!is2, "Expected: false, Found: true")
  }
}

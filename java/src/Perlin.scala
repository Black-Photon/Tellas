package src

import src.util.{FixedLandscape2D, Vector2F, Vector2I}

import scala.util.Random

class Perlin(resolution: Int, size: Int) {
  val landscape = new FixedLandscape2D[Float](size, size)
  val vectors = fillVectors((Math.random() * 100000).toInt, resolution, resolution)

  applyPerlin()

  def applyPerlin(): Unit = {
    for(x <- 0 until size; y <- 0 until size) {
      val location = Vector2F(x, y)
      landscape.set(perlin(vectors, location * resolution.toFloat / size.toFloat), location.floor)
    }
  }

  /**
    * Finds the perlin value at a given point on the grid
    * @param vectors List of vectors
    * @param vector Position on vectors to look at
    * @return Perlin value at location
    */
  def perlin(vectors: FixedLandscape2D[Vector2F], vector: Vector2F): Float = {
    val topLeft = vector.floor
    val topRight = topLeft + Vector2I(1, 0)
    val bottomLeft = topLeft + Vector2I(0, 1)
    val bottomRight = topLeft + Vector2I(1, 1)

    val localCoords = vector - topLeft.toFloat

    // Using .get as all should be already set
    // Dot products should be normalised. Already done to right side in fillVectors
    val tlDot = (vector - topLeft.toFloat).normalized() :* vectors.get(topLeft).get
    val trDot = (vector - topRight.toFloat).normalized() :* vectors.get(topRight).get
    val blDot = (vector - bottomLeft.toFloat).normalized() :* vectors.get(bottomLeft).get
    val brDot = (vector - bottomRight.toFloat).normalized() :* vectors.get(bottomRight).get

    val topLerp = interpolate(localCoords.x, tlDot, trDot)
    val bottomLerp = interpolate(localCoords.x, blDot, brDot)

    interpolate(localCoords.y, topLerp, bottomLerp)
  }

  /**
    * Interpolates between u and v
    * @param value Between 0 and 1
    * @param u Value 1
    * @param v Value 2
    */
  def interpolate(value: Float, u: Float, v: Float): Float = {
    // Used from Improving Noise by Ken Perlin
    val scale = 6 * Math.pow(value, 5).toFloat - 15 * Math.pow(value, 4).toFloat + 10 * Math.pow(value, 3).toFloat
    u * (1 - scale) + v * scale
  }

  def fillVectors(seed: Int, width: Int, height: Int): FixedLandscape2D[Vector2F] = {
    val random = new Random(seed)
    val vectors = new FixedLandscape2D[Vector2F](width + 1, height + 1)

    for(i <- 0 to width; j <- 0 to height) {
      vectors.set(Vector2F(random.nextFloat(), random.nextFloat()).normalized(), Vector2I(i, j))
    }

    vectors
  }
}

package src.block

import jni.Cube.Side.{ALL, BACK, BOTTOM, FRONT, LEFT, RIGHT, TOP}
import src.ChunkLoader
import src.util.Vector3I

/**
  * Grass Block
  * @param position Position of block in world space
  */
class Grass(position: Vector3I, update: Boolean) extends Block(Grass, position, update) {
  def this(position: Vector3I) = this(position, true)

  override def tick: Unit = {
    if(ChunkLoader.getBlock(position py 1).isDefined) {
      if (Math.random() < 0.00015) {
        ChunkLoader.addBlock(new Dirt(position), position)
        return
      }
    }
  }
}

/**
  * Grass Block Type
  */
object Grass extends BlockInstance() {
  // Grass ID
  override def ID: Int = 2
  override type BlockClass = Grass

  override def createNew(pos: Vector3I): Block = new BlockClass(pos)

  addTexture("grass.png", true, List(TOP))
  addTexture("grass_side.png", false, List(LEFT, RIGHT, BACK, FRONT)) // For isPNG, see Dirt
  addTexture(Dirt.textures.head._1, List(BOTTOM))
}
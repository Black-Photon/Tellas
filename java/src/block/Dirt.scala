package src.block

import jni.Cube.Side.ALL
import src.ChunkLoader
import src.util.Vector3I

/**
  * Dirt Block
  * @param position Position of block in world space
  */
class Dirt(position: Vector3I, update: Boolean) extends Block(Dirt, position, update) {
  def this(position: Vector3I) = this(position, true)

  override def tick: Unit = {
    if(ChunkLoader.getBlock(position py 1).isEmpty) {
      for (x <- -1 to 1; y <- -1 to 1; z <- -1 to 1) {
        if (!(x.abs == z.abs && x.abs == 1)) {
          ChunkLoader.getBlock(position + Vector3I(x, y, z)) match {
            case Some(block: Grass) =>
              if (Math.random() < 0.00015) {
                ChunkLoader.addBlock(new Grass(position), position)
                return
              }

            case _ => Unit
          }
        }
      }
    }
  }
}

/**
  * Dirt Block Type
  */
object Dirt extends BlockInstance() {
  // Dirt ID
  override val ID: Int = 1

  /**
    * You may be wondering later why isPNG is false. It *is* a png after all. The answer to that is that for
    * whatever reason, when I replaced the textures with my own, somehow their format is still the opposite of RGB vs
    * RGBA. So no clue what's going on there. Anyway, until you figure out why, these will be staying this way, because
    * it leads to a not-broken Tellas
    */
  addTexture("dirt.png", false, ALL)
}
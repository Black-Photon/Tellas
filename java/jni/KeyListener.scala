package jni

import src.{ChunkLoader, Data}
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT, UP}
import jni.Cube.Side
import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.Player.Direction
import src.block.{Air, Block, Dirt, Stone}
import src.util.Vector3I

import scala.collection.mutable

class KeyListener {
  @native def wPressed: Boolean
  @native def aPressed: Boolean
  @native def sPressed: Boolean
  @native def dPressed: Boolean
  @native def spacePressed: Boolean
  @native def rcPressed: Boolean
  @native def lcPressed: Boolean
  @native def upPressed: Boolean
  @native def rightPressed: Boolean
  @native def downPressed: Boolean
  @native def leftPressed: Boolean
  @native def lctrlPressed: Boolean
}

object KeyListener extends KeyListener {
  var lctrl = false

  def processInput(deltaT: Float, sunCam: Camera): Unit = {
    GLWrapper.processInput(deltaT)

    if (wPressed) {
      Data.player.moveDirection(FORWARD, deltaT)
    }
    if (aPressed) {
      Data.player.moveDirection(Direction.LEFT, deltaT)
    }
    if (sPressed) {
      Data.player.moveDirection(BACKWARDS, deltaT)
    }
    if (dPressed) {
      Data.player.moveDirection(Direction.RIGHT, deltaT)
    }
    if (rcPressed) {
      Data.player.getNewBlockPosition match {
        case Some(position)    => if(Data.selected != Air) Data.selected.createNew(position)
                                  updateSurroundings(position)
                                  ChunkLoader.updateSurroundingVisibility(position)
        case None              => Unit
      }
    }
    if (lcPressed) {
      Data.player.getLookBlockPosition match {
        case Some(position)    => ChunkLoader.addBlock(null, position)
                                  updateSurroundings(position)
                                  ChunkLoader.updateSurroundingVisibility(position)
        case None              => Unit
      }
    }
    if (spacePressed) {
      Data.player.moveDirection(UP, deltaT)
    }
    if (lctrlPressed) {
      if(!lctrl) {
        try {
          var current = Data.selected.ID
          if (current == Data.blocks.length - 1) {
            current = -1
          }
          Data.selected = Data.blocks(current + 1)
        } catch {
          case _: NullPointerException =>
            throw new RuntimeException("A block has not been added to the array in Data")
        }
        lctrl = true
      }
    } else lctrl = false
  }

  /**
    * Updates blocks surrounding the position specified
    * @param position Position to update around
    */
  def updateSurroundings(position: Vector3I): Unit = {
    for((side, pos) <- surrounding(position)) {
      ChunkLoader.getBlock(pos) match {
        case None => Unit
        case Some(block) => block.setIsSide(side, position)
      }
    }
  }

  /**
    * Finds the surrounding block vectors
    * @param p Initial Location
    * @return Hashmap of Side and block location on that side
    */
  def surrounding(p: Vector3I): mutable.HashMap[Side, Vector3I] = mutable.HashMap(Side.LEFT -> (p px 1), Side.RIGHT -> (p px -1), TOP -> (p py -1), BOTTOM -> (p py 1), FRONT -> (p pz 1), BACK -> (p pz -1))
}
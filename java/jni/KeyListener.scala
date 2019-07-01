package jni

import src.{ChunkLoader, Data}
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT, UP}
import jni.Cube.Side
import jni.Cube.Side.{BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}
import jni.Player.Direction
import src.block.{Air, Block, Dirt}
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
}

object KeyListener extends KeyListener {
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
        case Some(position)    => new Dirt(position)
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
    if(upPressed) {
      sunCam.rotate(Rotation.PITCH, (deltaT * 100).toInt)
    }
    if(rightPressed) {
      sunCam.rotate(Rotation.YAW, (deltaT * 100).toInt)
    }
    if(downPressed) {
      sunCam.rotate(Rotation.PITCH, -(deltaT * 100).toInt)
    }
    if(leftPressed) {
      sunCam.rotate(Rotation.YAW, -(deltaT * 100).toInt)
    }
  }

  def updateSurroundings(position: Vector3I): Unit = {
    for((side, pos) <- surrounding(position)) {
      ChunkLoader.getBlock(pos) match {
        case None => Unit
        case Some(block) => block.setIsSide(side, position)
      }
    }
  }

  def surrounding(p: Vector3I): mutable.HashMap[Side, Vector3I] = mutable.HashMap(Side.LEFT -> (p px 1), Side.RIGHT -> (p px -1), TOP -> (p py -1), BOTTOM -> (p py 1), FRONT -> (p pz 1), BACK -> (p pz -1))
}
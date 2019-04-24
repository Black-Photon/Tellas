package jni

import src.{ChunkLoader, Data}
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT, UP}
import src.block.{Air, Dirt}

class KeyListener {
  @native def wPressed: Boolean
  @native def aPressed: Boolean
  @native def sPressed: Boolean
  @native def dPressed: Boolean
  @native def spacePressed: Boolean
  @native def rcPressed: Boolean
  @native def lcPressed: Boolean
}

object KeyListener extends KeyListener {
  def processInput(deltaT: Float): Unit = {
    GLWrapper.processInput(deltaT)

    if (wPressed) {
      Data.player.moveDirection(FORWARD, deltaT)
    }
    if (aPressed) {
      Data.player.moveDirection(LEFT, deltaT)
    }
    if (sPressed) {
      Data.player.moveDirection(BACKWARDS, deltaT)
    }
    if (dPressed) {
      Data.player.moveDirection(RIGHT, deltaT)
    }
    if (rcPressed) {
      Data.player.getNewBlockPosition match {
        case Some(position)    => new Dirt(position)
        case None              => Unit
      }
    }
    if (lcPressed) {
      Data.player.getLookBlockPosition match {
        case Some(position)    => ChunkLoader.addBlock(Air, position)
        case None              => Unit
      }
    }
    if (spacePressed) {
      Data.player.moveDirection(UP, deltaT)
    }
  }
}
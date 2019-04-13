package jni

import src.Data
import Player.Direction.{BACKWARDS, FORWARD, LEFT, RIGHT}

class KeyListener {
  @native def wPressed: Boolean
  @native def aPressed: Boolean
  @native def sPressed: Boolean
  @native def dPressed: Boolean
}

object KeyListener extends KeyListener {
  def processInput(deltaT: Float): Unit = {
    GLWrapper.processInput(deltaT)

    val SPEED = Data.player.speed

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
  }
}
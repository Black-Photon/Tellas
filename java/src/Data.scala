package src

import jni.Player
import src.block.BlockInstance

import scala.collection.mutable

/**
  * General static data that should be globally accessible
  */
object Data {
  // The current player
  val player: Player = new Player
  // List of all block types
  val blocks: mutable.MutableList[BlockInstance] = new mutable.MutableList[BlockInstance]
}

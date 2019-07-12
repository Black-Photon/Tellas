package src

import jni.{Camera, Player}
import src.block.{Air, BlockInstance}

/**
  * General static data that should be globally accessible
  */
object Data {
  // List of all block types
  val blocks: Array[BlockInstance] = new Array(4)
  // Screen Width
  val width: Int = 1920
  // Screen Height
  val height: Int = 1080
  // The current player
  val player: Player = new Player(new Camera(width, height))
  // Block selected to place
  var selected: BlockInstance = Air
}

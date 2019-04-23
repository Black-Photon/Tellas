package src

import jni.Player

import scala.collection.mutable

object Data {
  val player: Player = new Player
  val blocks: mutable.MutableList[BlockInstance] = new mutable.MutableList[BlockInstance]
}

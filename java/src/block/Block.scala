package src.block

import jni.Cube.Side
import src.ChunkLoader
import src.util.Vector3I
import jni.Cube.Side.{ALL, BACK, BOTTOM, FRONT, LEFT, RIGHT, Side, TOP}

import scala.collection.mutable

/**
  * Defines an individual block
  * @param selfI The Instance the block is a type of
  * @param p Position of the block
  */
abstract class Block(selfI: BlockInstance, p: Vector3I, update: Boolean) {
  val position: Vector3I = p
  val self: BlockInstance = selfI
  val isBlock: mutable.HashMap[Side, Boolean] = new mutable.HashMap[Side, Boolean]()

  def this(selfI: BlockInstance, p: Vector3I) = this(selfI, p, true)

  if(update) ChunkLoader.addBlock(this, position)
  else ChunkLoader.addBlockNoUpdate(this, position)

  def setIsSide(side: Side, position: Vector3I) = {
    ChunkLoader.getBlock(position) match {
      case None           => isBlock += (side -> false)
      case Some(block)    => isBlock += (side -> true)
                             block.isBlock += (side.opposite -> true)
    }
  }

  def isSide(side: Side): Boolean = {
    isBlock.get(side) match {
      case None => false
      case Some(bool) => bool
    }
  }

  def isVisible: Boolean = {
    if(isBlock.size < 6) return true
    for((_, isNotVisible) <- isBlock) {
      if(!isNotVisible) return true
    }
    false
  }

  def tick: Unit = {}

  setIsSide(FRONT, position pz -1)
  setIsSide(BACK, position pz 1)
  setIsSide(LEFT, position px -1)
  setIsSide(RIGHT, position px 1)
  setIsSide(TOP, position py 1)
  setIsSide(BOTTOM, position py -1)
}
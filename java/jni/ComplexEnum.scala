package jni

import jni.Cube.Side.Side

class ComplexEnum extends Enumeration {
  abstract class ComplexVal(index: Int) extends Val(index) {
    def opposite: Side
  }
}

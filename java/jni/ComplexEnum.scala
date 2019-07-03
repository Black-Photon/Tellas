package jni

import jni.Cube.Side.Side

// For some reason, JNI breaks with inner inner classes, so have to put in separate file
class ComplexEnum extends Enumeration {
  abstract class ComplexVal(index: Int) extends Val(index) {
    def opposite: Side
  }
}

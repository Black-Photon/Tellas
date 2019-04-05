#include "jni_Utilities.h"
#include "../main.cpp"

JNIEXPORT jint JNICALL Java_jni_Utilities_getValue
  (JNIEnv *, jobject)
  {
      return 6;
  }

JNIEXPORT void JNICALL Java_jni_Utilities_run
  (JNIEnv *, jobject)
  {
      std::cout << "Worked" << std::endl;
      main();
  }
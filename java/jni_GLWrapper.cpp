#include "jni_GLWrapper.h"
#include "../main.cpp"

// Pre-Init
JNIEXPORT void JNICALL Java_jni_GLWrapper_preInit
        (JNIEnv *, jclass)
{
    preInit();
}

// Init
JNIEXPORT void JNICALL Java_jni_GLWrapper_init
        (JNIEnv *, jclass)
{
    init();
}

// Frame
JNIEXPORT void JNICALL Java_jni_GLWrapper_frame
        (JNIEnv *, jclass)
{
    frame();
}

// Should Close
JNIEXPORT jboolean JNICALL Java_jni_GLWrapper_shouldClose
        (JNIEnv *, jclass)
{
    return shouldClose();
}

// Close
JNIEXPORT void JNICALL Java_jni_GLWrapper_close
        (JNIEnv *, jclass)
{
    close();
}
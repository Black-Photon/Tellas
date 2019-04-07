#include "jni_GLWrapper.h"
#include "../main.cpp"

// Pre-Init
JNIEXPORT void JNICALL Java_jni_GLWrapper_preInit
        (JNIEnv * env, jclass self, jint width, jint height, jstring jname)
{
    const char *chars = env->GetStringUTFChars(jname, NULL);
    std::string title = std::string(chars);
    env->ReleaseStringUTFChars(jname, chars);
    core::preInit(width, height, title);
}

// Init
JNIEXPORT void JNICALL Java_jni_GLWrapper_init
        (JNIEnv *, jclass)
{
    core::init();
}

// Frame
JNIEXPORT void JNICALL Java_jni_GLWrapper_frame
        (JNIEnv *, jclass)
{
    core::frame();
}

// Should Close
JNIEXPORT jboolean JNICALL Java_jni_GLWrapper_shouldClose
        (JNIEnv *, jclass)
{
    return core::shouldClose();
}

// Close
JNIEXPORT void JNICALL Java_jni_GLWrapper_close
        (JNIEnv *, jclass)
{
    core::close();
}
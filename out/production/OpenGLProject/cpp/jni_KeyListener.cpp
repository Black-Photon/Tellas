#include "jni_KeyListener.h"
#include "../main.cpp"

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_wPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_W) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_aPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_A) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_sPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_S) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_dPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_D) == GLFW_PRESS;
}
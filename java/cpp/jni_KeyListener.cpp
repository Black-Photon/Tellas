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

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_spacePressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_SPACE) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_rcPressed
        (JNIEnv *, jobject)
{
    if (core::Mouse.rightClick) {
        core::Mouse.rightClick = false;
        return true;
    }
    return false;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_lcPressed
        (JNIEnv *, jobject)
{
    if (core::Mouse.leftClick) {
        core::Mouse.leftClick = false;
        return true;
    }
    return false;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_upPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_UP) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_rightPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_RIGHT) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_downPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_DOWN) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_leftPressed
        (JNIEnv *, jobject)
{
    return glfwGetKey(core::Data.window, GLFW_KEY_LEFT) == GLFW_PRESS;
}
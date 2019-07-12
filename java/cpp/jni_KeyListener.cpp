#include "jni_KeyListener.h"
#include "../main.cpp"

jboolean keyPressed(GLenum key)
{
    return glfwGetKey(core::Data.window, key) == GLFW_PRESS;
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_wPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_W);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_aPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_A);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_sPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_S);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_dPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_D);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_spacePressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_SPACE);
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
    return keyPressed(GLFW_KEY_UP);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_rightPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_RIGHT);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_downPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_DOWN);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_leftPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_LEFT);
}

JNIEXPORT jboolean JNICALL Java_jni_KeyListener_lctrlPressed
        (JNIEnv *, jobject)
{
    return keyPressed(GLFW_KEY_LEFT_CONTROL);
}

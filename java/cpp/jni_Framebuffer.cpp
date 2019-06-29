#include "../main.cpp"
#include "jni_Framebuffer.h"

unsigned int texture;

JNIEXPORT jint JNICALL Java_jni_Framebuffer_setupN
        (JNIEnv *, jobject, jint width, jint height, jboolean rw)
{
    unsigned int fbo;
    core::glCheckError();
    framebuffer::setup(width, height, rw, &fbo, &texture);
    core::glCheckError();
    return fbo;
}

JNIEXPORT jint JNICALL Java_jni_Framebuffer_getTextureIDN
        (JNIEnv *, jobject)
{
    return texture;
}

JNIEXPORT void JNICALL Java_jni_Framebuffer_startN
        (JNIEnv *, jobject, jint width, jint height, jint fbo)
{
    core::glCheckError();
    framebuffer::startWrite(width, height, fbo);
    core::glCheckError();
}

JNIEXPORT void JNICALL Java_jni_Framebuffer_endN
        (JNIEnv *, jobject)
{
    core::glCheckError();
    framebuffer::endWrite();
    core::glCheckError();
}
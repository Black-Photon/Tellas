#include "jni_Player.h"
#include "../main.cpp"

JNIEXPORT void JNICALL Java_jni_Player_setPositionN
        (JNIEnv *, jobject, jfloat x, jfloat y, jfloat z)
{
    core::Data.camera->setPosition(glm::vec3(x, y, z));
}

JNIEXPORT jfloat JNICALL Java_jni_Player_getLookingDirectionX
        (JNIEnv *, jobject)
{
    return core::Data.camera->getLooking().x;
}

JNIEXPORT jfloat JNICALL Java_jni_Player_getLookingDirectionY
        (JNIEnv *, jobject)
{
    return core::Data.camera->getLooking().y;
}

JNIEXPORT jfloat JNICALL Java_jni_Player_getLookingDirectionZ
        (JNIEnv *, jobject)
{
    return core::Data.camera->getLooking().z;
}
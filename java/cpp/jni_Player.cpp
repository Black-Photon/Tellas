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

JNIEXPORT jfloatArray JNICALL Java_jni_Player_getLookingDirectionN
        (JNIEnv * env, jobject)
{
    glm::vec3 vector = core::Data.camera->getLooking();
    jfloat array[] = {vector.x, vector.y, vector.z};

    jfloatArray result;
    result = env->NewFloatArray(3);
    // Check if enough memory to make
    if (result == NULL) {
        return NULL;
    }
    // Move from array to the java structure
    env->SetFloatArrayRegion(result, 0, 3, array);
    return result;
}
#include "jni_Camera.h"
#include "../main.cpp"

JNIEXPORT jint JNICALL Java_jni_Camera_createCamera
        (JNIEnv *, jobject, jint width, jint height)
{
    float aspectRatio = ((float) width)/((float) height);
    Camera *cam = new Camera(aspectRatio);
    cam->rotate(YAW, -90.0f); // Required for mouse callback and rendering direction to work
    core::Data.cameras.push_back(cam);
    if(core::Data.movementCam == nullptr) core::Data.movementCam = cam;
    return core::Data.cameras.size() - 1;
}

JNIEXPORT void JNICALL Java_jni_Camera_setPositionN
        (JNIEnv *, jobject, jfloat x, jfloat y, jfloat z, jint id)
{
    core::Data.cameras.at(id)->setPosition(glm::vec3(x, y, z));
}

JNIEXPORT jfloat JNICALL Java_jni_Camera_getLookingDirectionX
        (JNIEnv *, jobject, jint id)
{
    return core::Data.cameras.at(id)->getLooking().x;
}

JNIEXPORT jfloat JNICALL Java_jni_Camera_getLookingDirectionY
        (JNIEnv *, jobject, jint id)
{
    return core::Data.cameras.at(id)->getLooking().y;
}

JNIEXPORT jfloat JNICALL Java_jni_Camera_getLookingDirectionZ
        (JNIEnv *, jobject, jint id)
{
    return core::Data.cameras.at(id)->getLooking().z;
}

JNIEXPORT jfloatArray JNICALL Java_jni_Camera_getLookingDirectionN
        (JNIEnv * env, jobject, jint id)
{
    glm::vec3 vector = core::Data.cameras.at(id)->getLooking();
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

JNIEXPORT jfloatArray JNICALL Java_jni_Camera_getPositionN
        (JNIEnv * env, jobject, jint id)
{
    glm::vec3 vector = core::Data.cameras.at(id)->cameraPos;
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

JNIEXPORT void JNICALL Java_jni_Camera_setProjectionTypeN
        (JNIEnv *, jobject, jint type, jint id)
{
    core::Data.cameras.at(id)->setProjectionType(type);
}

JNIEXPORT void JNICALL Java_jni_Camera_rotateN
        (JNIEnv *, jobject, jint rotation, jint angle, jint id)
{
    switch (rotation) {
        case 0: core::Data.cameras.at(id)->rotate(PITCH, angle);
            break;
        case 1: core::Data.cameras.at(id)->rotate(YAW, angle);
            break;
        default: std::cerr << "No such rotation type" << std::endl;
            throw std::exception();
    }
}

JNIEXPORT void JNICALL Java_jni_Camera_lockPitchN
        (JNIEnv *, jobject, jboolean lock, jint id)
{
    core::Data.cameras.at(id)->lockPitch = lock;
}
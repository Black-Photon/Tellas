#include "jni_Cube.h"

JNIEXPORT void JNICALL Java_jni_Cube_drawFaceN
        (JNIEnv * env, jobject, jfloat x, jfloat y, jfloat z, jint side, jint shader)
{
    // Sets the position
    glm::vec3 position(x, y, z);

    // Draws the model
    auto *model = core::Data.cube;
    model->drawSide(position, *core::Data.shaders.at(shader), side);
}

JNIEXPORT void JNICALL Java_jni_Cube_drawFaceManyN
        (JNIEnv * env, jobject, jintArray positions, jint length, jint face)
{
    if(length % 3 != 0){
        std::cerr << "Array length not a multiple of 3" << std::endl;
        throw std::exception();
    }

    jboolean isCopy = false;
    int* positionArray = env->GetIntArrayElements(positions, &isCopy);

    auto* vectorArray = new glm::vec3[length / 3];
    int x, y;
    for(int i = 0; i < length; i++) {
        int part = i % 3;
        int index = i / 3;

        if(part == 0) x = positionArray[i];
        else if (part == 1) y = positionArray[i];
        else vectorArray[index] = glm::vec3(x, y, positionArray[i]);
    }

    // Draws the model
    auto *model = core::Data.cube;
    model->drawMany(vectorArray, length / 3, face);
}
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
#include "jni_Cube.h"

JNIEXPORT void JNICALL Java_jni_Cube_drawFaceN
        (JNIEnv * env, jobject, jfloat x, jfloat y, jfloat z, jint side)
{
    Shader *shader = core::Data.shader3d;

    // Sets the position
    glm::vec3 position(x, y, z);

    // Draws the model
    auto *model = core::Data.cube;
    model->drawSide(position, *shader, side);
}
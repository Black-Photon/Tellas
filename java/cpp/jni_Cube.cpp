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
        (JNIEnv *, jobject, jintArray positions, jint length, jint face)
{
    unsigned int instanceVBO;
    glGenBuffers(1, &instanceVBO);
    glBindBuffer(GL_ARRAY_BUFFER, instanceVBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(glm::vec3) * length, &positions[0], GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER, 0);

    glEnableVertexAttribArray(3);
    glBindBuffer(GL_ARRAY_BUFFER, instanceVBO);
    glVertexAttribPointer(3, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glVertexAttribDivisor(3, 1);

    glDrawArraysInstanced(GL_TRIANGLES, 6 * face, 6, length);
}
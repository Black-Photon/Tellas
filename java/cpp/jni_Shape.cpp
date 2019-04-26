#include "jni_Shape.h"
#include "../main.cpp"

JNIEXPORT void JNICALL Java_jni_Shape_drawN
        (JNIEnv * env, jobject, jfloat x, jfloat y, jfloat z, jint jmodel)
{
    Shader *shader = core::Data.shader3d;

    // Sets the position
    glm::vec3 position(x, y, z);

    // Draws the model
    Model *model = core::Data.models.at(jmodel);
    model->draw(position, *shader);
}

JNIEXPORT void JNICALL Java_jni_Shape_bindBufferN
        (JNIEnv *, jobject, jint jmodel)
{
    Model *model = core::Data.models.at(jmodel);
    model->bind();
}

JNIEXPORT void JNICALL Java_jni_Shape_activateShader
        (JNIEnv *, jobject)
{
    // Sets the shader3d to use (Currently only one)
    Shader *shader = core::Data.shader3d;
    core::makeModel(*shader);
}
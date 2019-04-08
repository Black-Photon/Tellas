#include "jni_Shape.h"
#include "../main.cpp"

JNIEXPORT void JNICALL Java_jni_Shape_drawN
        (JNIEnv * env, jobject, jfloat x, jfloat y, jfloat z, jint jmodel)
{
    // Sets the shader to use (Currently only one)
    Shader *shader = core::Data.shader;
    shader->use();
    core::makeModel(*shader);

    // Sets the position
    glm::vec3 position(x, y, z);

    // Draws the model
    Model *model = core::Data.models.at(jmodel);
    model->draw(position, *shader);
}
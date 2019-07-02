#include "jni_Shape.h"
#include "../main.cpp"

JNIEXPORT void JNICALL Java_jni_Shape_drawN
        (JNIEnv * env, jobject, jfloat x, jfloat y, jfloat z, jint jmodel, jint shader)
{
    // Sets the position
    glm::vec3 position(x, y, z);

    // Draws the model
    Model3D *model = core::Data.models3d.at(jmodel);
    model->draw(position, *core::Data.shaders.at(shader));
}

JNIEXPORT void JNICALL Java_jni_Shape_bindBufferN
        (JNIEnv *, jobject, jint jmodel)
{
    Model3D *model = core::Data.models3d.at(jmodel);
    model->bind();
}
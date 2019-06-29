#include "jni_SkyBox.h"
#include "../../classes/Shader.h"
#include "../../main.cpp"

JNIEXPORT void JNICALL Java_jni_SkyBox_drawN
        (JNIEnv *, jobject, jfloat x, jfloat y, jfloat z, jfloat angle, jfloat distance)
{
    glDisable(GL_DEPTH_TEST);
    Shader *shader = core::Data.shaderSkyBox;

    // Sets the position
    glm::vec3 position(x, y, z);

    // Draws the model
    auto *model = (WorldModel*) core::Data.models3d.at(1);
    core::glCheckError();
    model->draw(position, angle, distance, *shader);
    core::glCheckError();
    glEnable(GL_DEPTH_TEST);
}

JNIEXPORT void JNICALL Java_jni_SkyBox_bindBufferN
        (JNIEnv *, jobject)
{
    auto *model = core::Data.models3d.at(1);
    model->bind();
}

JNIEXPORT void JNICALL Java_jni_SkyBox_activateShader
        (JNIEnv *, jobject)
{
    Shader *shader = core::Data.shaderSkyBox;
    core::makeModel(*shader);
    core::glCheckError();
}
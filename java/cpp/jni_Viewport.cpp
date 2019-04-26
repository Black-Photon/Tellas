#include "jni_Viewport.h"

// drawImage
JNIEXPORT void JNICALL Java_jni_Viewport_drawImage
        (JNIEnv *, jobject, jint x, jint y, jint width, jint height)
{
    Shader *shader = core::Data.shader2d;

    // Sets the position
    glm::vec2 position(x, y);
    glm::vec2 screen(core::Data.SCR_WIDTH, core::Data.SCR_HEIGHT);
    glm::vec2 size(width*50, height*50);

    // Draws the model
    Model *model = core::Data.models.at(1);
    model->draw(position, screen, size, *shader);
}
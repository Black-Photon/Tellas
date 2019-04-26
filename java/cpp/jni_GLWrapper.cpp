#include <stdexcept>
#include "jni_GLWrapper.h"
#include "../main.cpp"
#include "jni_Shape.cpp"
#include "jni_KeyListener.cpp"
#include "jni_Player.cpp"
#include "jni_Viewport.cpp"

// Test
JNIEXPORT void JNICALL Java_jni_GLWrapper_test
  (JNIEnv *, jobject)
{
    std::cout << "INFO::JNI::LINK_ESTABLISHED" << std::endl;
}

// Pre-Init
JNIEXPORT void JNICALL Java_jni_GLWrapper_preInit
        (JNIEnv * env, jobject self, jint width, jint height, jstring jname)
{
    const char *chars = env->GetStringUTFChars(jname, NULL);
    std::string title = std::string(chars);
    env->ReleaseStringUTFChars(jname, chars);
    core::preInit(width, height, title);
}

// Init
JNIEXPORT void JNICALL Java_jni_GLWrapper_init
        (JNIEnv *, jobject, jboolean capture)
{
    core::init(capture);
}

// Should Close
JNIEXPORT jboolean JNICALL Java_jni_GLWrapper_shouldClose
        (JNIEnv *, jobject)
{
    return core::shouldClose();
}

// Close
JNIEXPORT void JNICALL Java_jni_GLWrapper_close
        (JNIEnv *, jobject)
{
    core::close();
}

JNIEXPORT jfloat JNICALL Java_jni_GLWrapper_deltaT
        (JNIEnv *, jobject)
{
    float currentFrame = glfwGetTime();
    float deltaTime = currentFrame - core::Data.lastFrame;
    core::Data.lastFrame = currentFrame;
    return deltaTime;
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_processInput
        (JNIEnv *, jobject, jfloat deltaTime)
{
    core::processInput(deltaTime);
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_prerender
        (JNIEnv *, jobject, jfloat r, jfloat g, jfloat b)
{
    core::prerender(r, g, b);
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_postrender
        (JNIEnv *, jobject)
{
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_swapBuffers
        (JNIEnv *, jobject)
{
    glfwSwapBuffers(core::Data.window);
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_pollEvents
        (JNIEnv *, jobject)
{
    glfwPollEvents();
}

JNIEXPORT jint JNICALL Java_jni_GLWrapper_generateTexture
        (JNIEnv * env, jobject, jstring jpath, jboolean isPNG)
{
    const char *path= env->GetStringUTFChars(jpath, 0);

    unsigned int value;
    core::generateTexture(&value, std::string(path), isPNG);

    env->ReleaseStringUTFChars(jpath, path);
    return value;
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_useTexture
        (JNIEnv *, jobject, jint texture, jint location)
{
    switch (location) {
        case 0: glActiveTexture(GL_TEXTURE0);
            break;
        case 1: glActiveTexture(GL_TEXTURE1);
            break;
        case 2: glActiveTexture(GL_TEXTURE2);
            break;
        case 3: glActiveTexture(GL_TEXTURE3);
            break;
        case 4: glActiveTexture(GL_TEXTURE4);
            break;
        case 5: glActiveTexture(GL_TEXTURE5);
            break;
        case 6: glActiveTexture(GL_TEXTURE6);
            break;
        case 7: glActiveTexture(GL_TEXTURE7);
            break;
        case 8: glActiveTexture(GL_TEXTURE8);
            break;
        case 9: glActiveTexture(GL_TEXTURE9);
            break;
        case 10: glActiveTexture(GL_TEXTURE10);
            break;
        case 11: glActiveTexture(GL_TEXTURE11);
            break;
        case 12: glActiveTexture(GL_TEXTURE12);
            break;
        case 13: glActiveTexture(GL_TEXTURE13);
            break;
        case 14: glActiveTexture(GL_TEXTURE14);
            break;
        case 15: glActiveTexture(GL_TEXTURE15);
            break;
        case 16: glActiveTexture(GL_TEXTURE16);
            break;
        case 17: glActiveTexture(GL_TEXTURE17);
            break;
        case 18: glActiveTexture(GL_TEXTURE18);
            break;
        case 19: glActiveTexture(GL_TEXTURE19);
            break;
        case 20: glActiveTexture(GL_TEXTURE20);
            break;
        case 21: glActiveTexture(GL_TEXTURE21);
            break;
        case 22: glActiveTexture(GL_TEXTURE22);
            break;
        case 23: glActiveTexture(GL_TEXTURE23);
            break;
        case 24: glActiveTexture(GL_TEXTURE24);
            break;
        case 25: glActiveTexture(GL_TEXTURE25);
            break;
        case 26: glActiveTexture(GL_TEXTURE26);
            break;
        case 27: glActiveTexture(GL_TEXTURE27);
            break;
        case 28: glActiveTexture(GL_TEXTURE28);
            break;
        case 29: glActiveTexture(GL_TEXTURE29);
            break;
        case 30: glActiveTexture(GL_TEXTURE30);
            break;
        case 31: glActiveTexture(GL_TEXTURE31);
            break;
        default:
            throw std::invalid_argument("location");
    }
    glBindTexture(GL_TEXTURE_2D, texture);
}

JNIEXPORT void JNICALL Java_jni_GLWrapper_checkError
        (JNIEnv *, jobject)
{
    core::glCheckError();
}
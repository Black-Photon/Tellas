#include "../main.cpp"
#include "jni_Shader.h"

JNIEXPORT jint JNICALL Java_jni_Shader_createShaderN
        (JNIEnv * env, jobject, jstring vertex, jstring fragment)
{
    const char *vertexChar = env->GetStringUTFChars(vertex, NULL);
    const char *fragmentChar = env->GetStringUTFChars(fragment, NULL);
    Shader* shader = new Shader(vertexChar, fragmentChar, core::Path.shaders);
    env->ReleaseStringUTFChars(vertex, vertexChar);
    env->ReleaseStringUTFChars(fragment, fragmentChar);

    core::Data.shaders.push_back(shader);
    return core::Data.shaders.size() - 1;
}

JNIEXPORT void JNICALL Java_jni_Shader_useShaderN
        (JNIEnv *, jobject, jint id)
{
    core::Data.shaders.at(id)->use();
}

JNIEXPORT void JNICALL Java_jni_Shader_setIntN
        (JNIEnv * env, jobject, jstring name, jint value, jint id)
{
    const char *nameChar = env->GetStringUTFChars(name, NULL);
    core::Data.shaders.at(id)->setInt(nameChar, value);
    env->ReleaseStringUTFChars(name, nameChar);
}

JNIEXPORT void JNICALL Java_jni_Shader_setFloatN
        (JNIEnv * env, jobject, jstring name, jfloat value, jint id)
{
    const char *nameChar = env->GetStringUTFChars(name, NULL);
    core::Data.shaders.at(id)->setFloat(nameChar, value);
    env->ReleaseStringUTFChars(name, nameChar);
}

JNIEXPORT void JNICALL Java_jni_Shader_setBoolN
        (JNIEnv * env, jobject, jstring name, jboolean value, jint id)
{
    const char *nameChar = env->GetStringUTFChars(name, NULL);
    core::Data.shaders.at(id)->setBool(nameChar, value);
    env->ReleaseStringUTFChars(name, nameChar);
}

JNIEXPORT void JNICALL Java_jni_Shader_setVec3N
        (JNIEnv * env, jobject, jstring name, jfloat v1, jfloat v2, jfloat v3, jint id)
{
    const char *nameChar = env->GetStringUTFChars(name, NULL);
    core::Data.shaders.at(id)->setVec3(nameChar, v1, v2, v3);
    env->ReleaseStringUTFChars(name, nameChar);
}

JNIEXPORT void JNICALL Java_jni_Shader_makeModelN
        (JNIEnv *, jobject, jint id, jint camera)
{
    core::makeModel(*core::Data.shaders.at(id), *core::Data.cameras.at(camera));
}

JNIEXPORT void JNICALL Java_jni_Shader_simulateCameraN
        (JNIEnv * env, jobject, jstring name, jint camera, jint id)
{
    const char *nameChar = env->GetStringUTFChars(name, NULL);
    core::simulateCamera(*core::Data.shaders.at(id), *core::Data.cameras.at(camera), nameChar);
    env->ReleaseStringUTFChars(name, nameChar);
}
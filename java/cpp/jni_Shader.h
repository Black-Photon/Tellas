/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jni_Shader */

#ifndef _Included_jni_Shader
#define _Included_jni_Shader
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jni_Shader
 * Method:    createShaderN
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_jni_Shader_createShaderN
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     jni_Shader
 * Method:    useShaderN
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_useShaderN
  (JNIEnv *, jobject, jint);

/*
 * Class:     jni_Shader
 * Method:    setIntN
 * Signature: (Ljava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_setIntN
  (JNIEnv *, jobject, jstring, jint, jint);

/*
 * Class:     jni_Shader
 * Method:    setFloatN
 * Signature: (Ljava/lang/String;FI)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_setFloatN
  (JNIEnv *, jobject, jstring, jfloat, jint);

/*
 * Class:     jni_Shader
 * Method:    setBoolN
 * Signature: (Ljava/lang/String;ZI)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_setBoolN
  (JNIEnv *, jobject, jstring, jboolean, jint);

/*
 * Class:     jni_Shader
 * Method:    setVec3N
 * Signature: (Ljava/lang/String;FFFI)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_setVec3N
  (JNIEnv *, jobject, jstring, jfloat, jfloat, jfloat, jint);

/*
 * Class:     jni_Shader
 * Method:    makeModelN
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_makeModelN
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     jni_Shader
 * Method:    simulateCameraN
 * Signature: (Ljava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_jni_Shader_simulateCameraN
  (JNIEnv *, jobject, jstring, jint, jint);

#ifdef __cplusplus
}
#endif
#endif

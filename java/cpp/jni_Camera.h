/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jni_Camera */

#ifndef _Included_jni_Camera
#define _Included_jni_Camera
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jni_Camera
 * Method:    createCamera
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_jni_Camera_createCamera
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     jni_Camera
 * Method:    setPositionN
 * Signature: (FFFI)V
 */
JNIEXPORT void JNICALL Java_jni_Camera_setPositionN
  (JNIEnv *, jobject, jfloat, jfloat, jfloat, jint);

/*
 * Class:     jni_Camera
 * Method:    getLookingDirectionX
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_jni_Camera_getLookingDirectionX
  (JNIEnv *, jobject, jint);

/*
 * Class:     jni_Camera
 * Method:    getLookingDirectionY
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_jni_Camera_getLookingDirectionY
  (JNIEnv *, jobject, jint);

/*
 * Class:     jni_Camera
 * Method:    getLookingDirectionZ
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_jni_Camera_getLookingDirectionZ
  (JNIEnv *, jobject, jint);

/*
 * Class:     jni_Camera
 * Method:    getLookingDirectionN
 * Signature: (I)[F
 */
JNIEXPORT jfloatArray JNICALL Java_jni_Camera_getLookingDirectionN
  (JNIEnv *, jobject, jint);

/*
 * Class:     jni_Camera
 * Method:    getPositionN
 * Signature: (I)[F
 */
JNIEXPORT jfloatArray JNICALL Java_jni_Camera_getPositionN
  (JNIEnv *, jobject, jint);

/*
 * Class:     jni_Camera
 * Method:    setProjectionTypeN
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_jni_Camera_setProjectionTypeN
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     jni_Camera
 * Method:    rotateN
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_jni_Camera_rotateN
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     jni_Camera
 * Method:    setRotationN
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_jni_Camera_setRotationN
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     jni_Camera
 * Method:    lockPitchN
 * Signature: (ZI)V
 */
JNIEXPORT void JNICALL Java_jni_Camera_lockPitchN
  (JNIEnv *, jobject, jboolean, jint);

/*
 * Class:     jni_Camera
 * Method:    invertN
 * Signature: (ZI)V
 */
JNIEXPORT void JNICALL Java_jni_Camera_invertN
  (JNIEnv *, jobject, jboolean, jint);

#ifdef __cplusplus
}
#endif
#endif

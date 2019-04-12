#!/usr/bin/env bash

# javah needs access to scala-library.jar
LIBS_HOME=/usr/share/scala/lib
CP=$LIBS_HOME/scala-library.jar

cd out/production/OpenGLProject
echo -- INFO -- Starting JNI
javah -cp $CP:. jni.GLWrapper
if [[ $? -eq 0 ]]; then
    mv jni_GLWrapper.h ../../../java/

    javah -cp $CP:. jni.Shape
    if [[ $? -eq 0 ]]; then
        mv jni_Shape.h ../../../java/
        echo -- INFO -- Success
    else
        echo -- INFO -- Failure
    fi
else
    echo -- INFO -- Failure
fi
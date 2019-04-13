#!/usr/bin/env bash

# javah needs access to scala-library.jar
LIBS_HOME=/usr/share/scala/lib
CP=$LIBS_HOME/scala-library.jar

cd out/production/OpenGLProject
echo -- INFO -- Starting JNI
FILES="$(ls jni | grep -v '\$' | sed 's/.class//')"

for l in $FILES
do
    javah -cp $CP:. jni.$l

    if [[ $? -eq 0 ]]; then echo Creating header for $l...
    else
        echo -- INFO -- Failure
        exit -1
    fi

    mv jni_$l.h ../../../java/cpp
    if [[ $? -eq 0 ]]; then :
    else
        echo -- INFO -- Failure
        exit -1
    fi
done
echo -- INFO -- Success
exit 0
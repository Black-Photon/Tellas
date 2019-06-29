#!/usr/bin/env bash

# javah needs access to scala-library.jar
#LIBS_HOME=/dcs/18/u1800015/.ivy2/cache/org.scala-lang/scala-library/jars
LIBS_HOME=/home/joseph/.ivy2/cache/org.scala-lang/scala-library/jars

CP=$LIBS_HOME/scala-library-2.12.8.jar

#JAVAH=/usr/java/jdk1.8.0_181-amd64/bin/javah
JAVAH=javah

NAME=OpenGLProject
#NAME=Tellas

cd out/production/$NAME
echo -- INFO -- Starting JNI
FILES="$(ls jni | grep -v '\$' | sed 's/.class//')"

for l in $FILES
do
    $JAVAH -cp $CP:. jni.$l

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
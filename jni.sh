#!/usr/bin/env bash

cd java
echo -- INFO -- Starting JNI
javah jni.GLWrapper
if [[ $? -eq 0 ]]; then
    javah jni.Shape
    if [[ $? -eq 0 ]]; then
        echo -- INFO -- Success
    else
        echo -- INFO -- Failure
    fi
else
    echo -- INFO -- Failure
fi
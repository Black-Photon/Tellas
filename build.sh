#!/usr/bin/env bash

echo -- INFO -- Starting Build
cd cmake-build-debug
echo $'\n'-- INFO -- Running CMake
../../clion-2019.1.2/bin/cmake/linux/bin/cmake ../
if [[ $? != 0 ]]; then
    exit 1
fi
echo $'\n'-- INFO -- Running Make
make OpenGLProject
if [[ $? != 0 ]]; then
    exit 1
fi

#cd ../out/production/OpenGLProject
#echo $'\n'-- INFO -- Running Program
#scala Main
#if [[ $? != 0 ]]; then
#    exit 1
#fi
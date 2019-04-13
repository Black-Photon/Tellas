cmake_minimum_required(VERSION 3.12)
project(OpenGLProject)

set(CMAKE_CXX_STANDARD 17)

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

# GLaDOS

include_directories(include)

set(SOURCE_FILES
        java/cpp/jni_GLWrapper.cpp
        src/glad.c
        include/glad/glad.h
        classes/Shader.cpp classes/Camera.cpp classes/Model.cpp classes/CubeModel.cpp classes/CubeModel.h)
add_library(OpenGLProject SHARED ${SOURCE_FILES})

# GLFW

set(GLFW_BUILD_DOCS OFF CACHE BOOL "" FORCE)
set(GLFW_BUILD_TESTS OFF CACHE BOOL "" FORCE)
set(GLFW_BUILD_EXAMPLES OFF CACHE BOOL "" FORCE)

add_subdirectory("/home/joseph/Documents/Programming/Graphics/OpenGLProject/include/glfw-3.2.1/")
target_link_libraries(OpenGLProject glfw)
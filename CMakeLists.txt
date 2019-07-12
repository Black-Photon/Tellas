cmake_minimum_required(VERSION 3.12)
project(Tellas)

set(DIRECTORY "/home/joseph/Documents/Programming/Graphics/OpenGLProject/include/glfw-3.2.1")
#set(DIRECTORY "/run/media/u1800015/USB-ALPHA/Tellas/include/glfw-3.2.1")

set(CMAKE_CXX_STANDARD 17)

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

# GLaDOS

include_directories(include)

set(SOURCE_FILES
        java/cpp/jni_GLWrapper.cpp
        src/glad.c
        include/glad/glad.h
        classes/Shader.cpp classes/Camera.cpp classes/Model3D.cpp classes/CubeModel.cpp classes/SquareModel.cpp classes/Model2D.cpp classes/WorldModel.cpp)
add_library(Tellas SHARED ${SOURCE_FILES})

# GLFW

set(GLFW_BUILD_DOCS OFF CACHE BOOL "" FORCE)
set(GLFW_BUILD_TESTS OFF CACHE BOOL "" FORCE)
set(GLFW_BUILD_EXAMPLES OFF CACHE BOOL "" FORCE)

add_subdirectory(${DIRECTORY})
target_link_libraries(Tellas glfw)
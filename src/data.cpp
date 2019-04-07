#ifndef __INCLUDE_DATA
#define __INCLUDE_DATA

#include "include.cpp"
#include "../classes/Shader.h"

// Global Variables
glm::vec3 cameraPos   = glm::vec3(0.0f, 0.0f,  3.0f);
glm::vec3 cameraFront = glm::vec3(0.0f, 0.0f,  1.0f);
glm::vec3 cameraUp    = glm::vec3(0.0f, 1.0f,  0.0f);

float pitch = 0;
float yaw = 0;

float fov = 45.0f;

struct Data {
    const unsigned int SCR_WIDTH = 800;
    const unsigned int SCR_HEIGHT = 600;
    float lastFrame = 0.0f;
    GLFWwindow * window = nullptr;
    Shader * shader = nullptr;
    unsigned int texture[2];
} Data;

struct Buffers {
    unsigned int EBO;
    unsigned int *VBO;
    unsigned int *VAO;
} Buffers;

struct Mouse {
    float lastX = 400, lastY = 300;
    bool first = true;
} Mouse;

struct initialisationException: public std::exception {
    std::string s;

    explicit initialisationException(const std::string string) {
        s = string;
    }

    const char * what () const noexcept (true) override {
        return s.c_str();
    }
};

#endif
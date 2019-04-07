#pragma once

/**
 * Holds static data structures holding necessary information for the program
 */

#include "include.cpp"
#include "../classes/Shader.h"

namespace core {

    // TODO Remove loose variables
    glm::vec3 cameraPos = glm::vec3(0.0f, 0.0f, 3.0f);
    glm::vec3 cameraFront = glm::vec3(0.0f, 0.0f, 1.0f);
    glm::vec3 cameraUp = glm::vec3(0.0f, 1.0f, 0.0f);

    float pitch = 0;
    float yaw = 0;

    float fov = 45.0f;

    /**
     * Holds Screen and Program data
     */
    struct Data {
        unsigned int SCR_WIDTH = 800;
        unsigned int SCR_HEIGHT = 600;
        float lastFrame = 0.0f;
        GLFWwindow *window = nullptr;
        Shader *shader = nullptr;
        unsigned int texture[2];
    } Data;

    /**
     * Holds the VAO, VBO and EBO
     */
    struct Buffers {
        unsigned int EBO;
        unsigned int *VBO;
        unsigned int *VAO;
    } Buffers;

    /**
     * Holds variables relating to mouse movement
     */
    struct Mouse {
        float lastX = 400, lastY = 300;
        bool first = true;
    } Mouse;

    /**
     * Holds path pre-pends for different locations
     */
    struct Path {
        std::string root = "../../../";
        std::string assets = root + "assets/";
        std::string shaders = root + "shaders/";
    } Path;

    /**
     * Indicated an error occurred while initialising
     */
    struct initialisationException : public std::exception {
        std::string s;

        explicit initialisationException(const std::string string) {
            s = string;
        }

        const char *what() const noexcept(true) override {
            return s.c_str();
        }
    };
}
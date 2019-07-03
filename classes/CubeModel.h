#ifndef OPENGLPROJECT_CUBEMODEL_H
#define OPENGLPROJECT_CUBEMODEL_H


#include "Model3D.h"

static constexpr float SIZE = 0.25f;

static constexpr float vertices[] = {
        // Position          // Texture  // Normal
        // Back Face
        -SIZE, -SIZE, -SIZE, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
        SIZE, -SIZE, -SIZE, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f, 0.0f, 0.0f, -1.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f,
        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f,

        // Front Face
        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f,
        -SIZE,  SIZE,  SIZE, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f,
        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f,

        // Left Face
        -SIZE,  SIZE,  SIZE, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f,
        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f,
        -SIZE, -SIZE,  SIZE, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f,
        -SIZE,  SIZE,  SIZE, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f,

        // Right Face
        SIZE,  SIZE,  SIZE, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f,
        SIZE, -SIZE, -SIZE, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        SIZE,  SIZE, -SIZE, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f,
        SIZE, -SIZE, -SIZE, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f,

        // Bottom Face
        -SIZE, -SIZE, -SIZE, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f,
        SIZE, -SIZE, -SIZE, 1.0f, 1.0f, 0.0f, -1.0f, 0.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f,
        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f,

        // Top Face
        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,
        -SIZE,  SIZE,  SIZE, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f
};

class CubeModel: public Model3D {
public:

    CubeModel();

    void draw(glm::vec3 position, Shader shader);
    void drawSide(glm::vec3 position, Shader shader, int side);
};


#endif //OPENGLPROJECT_CUBEMODEL_H

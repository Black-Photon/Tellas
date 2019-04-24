#ifndef OPENGLPROJECT_CUBEMODEL_H
#define OPENGLPROJECT_CUBEMODEL_H


#include "Model.h"

static constexpr float SIZE = 0.25f;

static constexpr float vertices[] = {
        // Position          // Texture
        -SIZE, -SIZE, -SIZE, 0.0f, 0.0f,
        SIZE, -SIZE, -SIZE, 1.0f, 0.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f,
        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 0.0f,

        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 1.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 1.0f,
        -SIZE,  SIZE,  SIZE, 0.0f, 1.0f,
        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f,

        -SIZE,  SIZE,  SIZE, 1.0f, 0.0f,
        -SIZE,  SIZE, -SIZE, 1.0f, 1.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 1.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 1.0f,
        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f,
        -SIZE,  SIZE,  SIZE, 1.0f, 0.0f,

        SIZE,  SIZE,  SIZE, 1.0f, 0.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f,
        SIZE, -SIZE, -SIZE, 0.0f, 1.0f,
        SIZE, -SIZE, -SIZE, 0.0f, 1.0f,
        SIZE, -SIZE,  SIZE, 0.0f, 0.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 0.0f,

        -SIZE, -SIZE, -SIZE, 0.0f, 1.0f,
        SIZE, -SIZE, -SIZE, 1.0f, 1.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f,
        SIZE, -SIZE,  SIZE, 1.0f, 0.0f,
        -SIZE, -SIZE,  SIZE, 0.0f, 0.0f,
        -SIZE, -SIZE, -SIZE, 0.0f, 1.0f,

        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f,
        SIZE,  SIZE, -SIZE, 1.0f, 1.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 0.0f,
        SIZE,  SIZE,  SIZE, 1.0f, 0.0f,
        -SIZE,  SIZE,  SIZE, 0.0f, 0.0f,
        -SIZE,  SIZE, -SIZE, 0.0f, 1.0f
};

class CubeModel: public Model {
public:

    CubeModel();

    void draw(glm::vec3 position, Shader shader);
};


#endif //OPENGLPROJECT_CUBEMODEL_H

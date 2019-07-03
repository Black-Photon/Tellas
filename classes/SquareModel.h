#ifndef OPENGLPROJECT_SQUAREMODEL_H
#define OPENGLPROJECT_SQUAREMODEL_H


#include "Model2D.h"

static constexpr float SQ_SIZE = 1.0f;

static constexpr float sq_vertices[] = {
        // Position          // Texture
        -SQ_SIZE, -SQ_SIZE, 0.0f, 0.0f,
        SQ_SIZE, -SQ_SIZE, 1.0f, 0.0f,
        -SQ_SIZE,  SQ_SIZE, 0.0f, 1.0f,

        -SQ_SIZE,  SQ_SIZE, 0.0f, 1.0f,
        SQ_SIZE, -SQ_SIZE, 1.0f, 0.0f,
        SQ_SIZE, SQ_SIZE, 1.0f, 1.0f,
};

class SquareModel: public Model2D {
public:

    SquareModel();

    void draw(glm::vec2 position, glm::vec2 screen, glm::vec2 size, Shader shader);
};


#endif //OPENGLPROJECT_SQUAREMODEL_H

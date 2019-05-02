#ifndef OPENGLPROJECT_WORLDMODEL_H
#define OPENGLPROJECT_WORLDMODEL_H


#include "Model3D.h"

static constexpr float WLD_SIZE = 1.0f;

static constexpr float wld_vertices[] = {
        // Position          // Texture
        -WLD_SIZE, WLD_SIZE, -WLD_SIZE, 0.0f, 1.0f,
        WLD_SIZE, WLD_SIZE, -WLD_SIZE, 1.0f, 1.0f,
        WLD_SIZE, WLD_SIZE,  WLD_SIZE, 1.0f, 0.0f,
        WLD_SIZE, WLD_SIZE,  WLD_SIZE, 1.0f, 0.0f,
        -WLD_SIZE, WLD_SIZE,  WLD_SIZE, 0.0f, 0.0f,
        -WLD_SIZE, WLD_SIZE, -WLD_SIZE, 0.0f, 1.0f
};

class WorldModel: public Model3D {
public:

    WorldModel();

    void draw(glm::vec3 position, float angle, float distance, Shader shader);
    void draw(glm::vec3 position, Shader shader);
};


#endif //OPENGLPROJECT_WORLDMODEL_H

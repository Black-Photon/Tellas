#include <glm/gtc/type_ptr.hpp>
#include "CubeModel.h"

CubeModel::CubeModel() : Model3D((float*) vertices, 288)
{
    // Position
    // Tells OpenGL how to interpret the vertex buffer data
    // Index, Size, Type, Normalized, Stride, Pointer
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void *) 0);
    // Enables a generic vertex attribute at the given index
    glEnableVertexAttribArray(0);

    // Location
    // Tells OpenGL how to interpret the vertex buffer data
    // Index, Size, Type, Normalized, Stride, Pointer
    glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void *) (3 * sizeof(float)));
    // Enables a generic vertex attribute at the given index
    glEnableVertexAttribArray(1);

    // Normal
    // Tells OpenGL how to interpret the vertex buffer data
    // Index, Size, Type, Normalized, Stride, Pointer
    glVertexAttribPointer(2, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void *) (5 * sizeof(float)));
    // Enables a generic vertex attribute at the given index
    glEnableVertexAttribArray(2);

    // Note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
    // Unbinds the buffer
    glBindBuffer(GL_ARRAY_BUFFER, 0);

    // You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
    // VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
    glBindVertexArray(0);
}

void CubeModel::draw(glm::vec3 position, Shader shader)
{
    Model3D::draw(position, shader, 0, 36);
}

void CubeModel::drawMany(glm::vec3* position, int length, int side)
{
    Model3D::drawMany(position, length, 6 * side, 6);
}

void CubeModel::drawSide(glm::vec3 position, Shader shader, int side)
{
    Model3D::draw(position, shader, 6 * side, 6);
}

void CubeModel::drawItemSide(glm::vec2 screen_position, glm::vec2 screen, int size, Shader shader, int side)
{
    // Creates the model matrix by translating by coordinates
    glm::mat4 model = glm::mat4(1.0f);
    model = glm::scale(model, glm::vec3(size));
    model = glm::rotate(model, glm::radians(45.0f), glm::vec3(1.0f, 0.0f, 0.0f));
    model = glm::rotate(model, glm::radians(45.0f), glm::vec3(0.0f, 1.0f, 0.0f));
    int modelLoc = glGetUniformLocation(shader.ID, "model");

    // Sets the relative item uniform
    glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

    shader.setVec2("position", screen_position);
    shader.setVec2("screen", screen);

    // Don't depth test
    glDisable(GL_DEPTH_TEST);
    glDrawArrays(GL_TRIANGLES, 6 * side, 6);
    glEnable(GL_DEPTH_TEST);
}
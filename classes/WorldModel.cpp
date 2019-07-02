#include <glm/gtc/type_ptr.hpp>
#include "WorldModel.h"

WorldModel::WorldModel() : Model3D((float*) wld_vertices, 30)
{
    // Position
    // Tells OpenGL how to interpret the vertex buffer data
    // Index, Size, Type, Normalized, Stride, Pointer
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 5 * sizeof(float), (void *) 0);
    // Enables a generic vertex attribute at the given index
    glEnableVertexAttribArray(0);

    // Location
    // Tells OpenGL how to interpret the vertex buffer data
    // Index, Size, Type, Normalized, Stride, Pointer
    glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 5 * sizeof(float), (void *) (3 * sizeof(float)));
    // Enables a generic vertex attribute at the given index
    glEnableVertexAttribArray(1);

    // Note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
    // Unbinds the buffer
    glBindBuffer(GL_ARRAY_BUFFER, 0);

    // You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
    // VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
    glBindVertexArray(0);
}

void WorldModel::draw(glm::vec3 position, float angle, float distance, Shader shader)
{
    angle = glm::radians(angle);

    // Creates the rotate matrix by rotating
    glm::mat4 rotate = glm::mat4(1.0f);
    rotate = glm::rotate(rotate, angle, glm::vec3(1.0f, 0.0f, 0.0f));
    rotate = glm::rotate(rotate, angle, glm::vec3(0.0f, -sin(angle), sin(angle)));
    int rotateLoc = glGetUniformLocation(shader.ID, "rotate");

    // Sets the relative shader3d uniform
    glUniformMatrix4fv(rotateLoc, 1, GL_FALSE, glm::value_ptr(rotate));

    // Creates the location matrix by translating by coordinates
    glm::mat4 model = glm::mat4(1.0f);
    model = glm::translate(model, position + distance * glm::normalize(glm::vec3(-sin(angle), cos(angle), sin(angle))));
    int modelLoc = glGetUniformLocation(shader.ID, "displace");

    // Sets the relative shader3d uniform
    glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

    // Draws the model
    glDrawArrays(GL_TRIANGLES, 0, 6);
}

void WorldModel::draw(glm::vec3 position, Shader shader)
{
    // Sets the relative shader uniforms
    shader.setVec3("player", position);
    shader.setFloat("angle", 0);
    shader.setFloat("distance", 10);

    // Draws the model
    glDrawArrays(GL_TRIANGLES, 0, 6);
}
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>
#include "Model3D.h"

Model3D::Model3D(float vertices[], int length)
{
    // Generates a Vertex Array Object
    glGenVertexArrays(1, &VAO);
    // Generates the Vertex Buffer Objects
    glGenBuffers(1, &VBO);
    // Generates the Instanced Vertex Buffer Objects
    glGenBuffers(1, &instanceVBO);

    // Bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
    // Binds the VAO so glVertexAttribPointer and glEnableVertexAttribArray work on this VAO
    glBindVertexArray(VAO);

    // Binds the buffer to the buffer type so glBufferData works on this
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    // Allocates memory for and stores the vertices data
    glBufferData(GL_ARRAY_BUFFER, length * sizeof(*vertices), vertices, GL_STATIC_DRAW);
}

Model3D::~Model3D()
{
    // Deletes VAO and VBO data from memory
    glDeleteVertexArrays(1, &VAO);
    glDeleteBuffers(1, &VBO);
    glDeleteBuffers(1, &instanceVBO);
}

void Model3D::draw(glm::vec3 position, Shader shader, int start, int length)
{
    // Creates the model matrix by translating by coordinates
    glm::mat4 model = glm::mat4(1.0f);
    model = glm::translate(model, position);
    int modelLoc = glGetUniformLocation(shader.ID, "model");

    // Sets the relative shader3d uniform
    glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

//    shader.setVec3("aOffset", position);

    // Draws the model
    glDrawArrays(GL_TRIANGLES, start, length);
}

void Model3D::drawMany(glm::vec3* position, int length, int start, int indices)
{
    glBindBuffer(GL_ARRAY_BUFFER, instanceVBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(glm::vec3) * length, &position[0], GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER, 0);

    glEnableVertexAttribArray(3);
    glBindBuffer(GL_ARRAY_BUFFER, instanceVBO);
    glVertexAttribPointer(3, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glVertexAttribDivisor(3, 1);

    // Draws the model
    glDrawArraysInstanced(GL_TRIANGLES, start, indices, length);
}

void Model3D::bind()
{
    glBindVertexArray(VAO);
}
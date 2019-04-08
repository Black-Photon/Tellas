#include "data.cpp"
#include "../classes/Shader.h"
#include "../classes/Camera.h"

/**
 * Methods used from frame to frame
 */

namespace core {

    /**
    * Polls and processes any user input
    * @param deltaT Time since last frame
    */
    void processInput(float deltaT);
    /**
     * Pre-renders the screen creating the background and clearing the colour buffer
     */
    void prerender();
    /**
     * Applies the shader program to the VAO and draws it to the buffer
     * @param shader Shader Object to draw from
     * @param VAO Array Object index to draw
     * @param EBO Element Buffer Object index to draw with
     * @param texture Array of textures to draw
     */
    void draw(Shader shader, unsigned const int VAO, unsigned const int *EBO, unsigned const int texture[]);
    /**
     * Builds the model and transforms to the view space
     * @param shader Shader to transform
     */
    void makeModel(Shader shader);

    void processInput(float deltaT) {
        // Pretty Straightforward
        if (glfwGetKey(Data.window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
            glfwSetWindowShouldClose(Data.window, true);

        if (glfwGetKey(Data.window, GLFW_KEY_W) == GLFW_PRESS)
            Data.camera->moveOnPlane(FORWARD, Y, deltaT);
        if (glfwGetKey(Data.window, GLFW_KEY_S) == GLFW_PRESS)
            Data.camera->moveOnPlane(BACKWARD, Y, deltaT);
        if (glfwGetKey(Data.window, GLFW_KEY_A) == GLFW_PRESS)
            Data.camera->moveOnPlane(LEFT, Y, deltaT);
        if (glfwGetKey(Data.window, GLFW_KEY_D) == GLFW_PRESS)
            Data.camera->moveOnPlane(RIGHT, Y, deltaT);

        glfwSetInputMode(Data.window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    void prerender() {
        // Makes the screen this colour
        glClearColor(1.0f, 0.8f, 0.5f, 1.0f);
        // Clears the buffer with the clear colour
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // NOLINT(hicpp-signed-bitwise)
    }

    void draw(Shader shader, unsigned const int VAO, unsigned const int *EBO, unsigned const int texture[]) {
        // Sets the shader program to use
        shader.use();
        makeModel(shader);

        glm::vec3 cubePositions[] = {
                glm::vec3(0.0f, 0.0f, 0.0f),
                glm::vec3(2.0f, 5.0f, -15.0f),
                glm::vec3(-1.5f, -2.2f, -2.5f),
                glm::vec3(-3.8f, -2.0f, -12.3f),
                glm::vec3(2.4f, -0.4f, -3.5f),
                glm::vec3(-1.7f, 3.0f, -7.5f),
                glm::vec3(1.3f, -2.0f, -2.5f),
                glm::vec3(1.5f, 2.0f, -2.5f),
                glm::vec3(1.5f, 0.2f, -1.5f),
                glm::vec3(-1.3f, 1.0f, -1.5f)
        };

        // Binds the VAO so glVertexAttribPointer and glEnableVertexAttribArray work on this VAO
        glBindVertexArray(VAO);
        for (unsigned int i = 0; i < 10; i++) {
            glm::mat4 model = glm::mat4(1.0f);
            model = glm::translate(model, cubePositions[i]);
            float angle = 20.0f * i;
            model = glm::rotate(model, glm::radians(angle), glm::vec3(1.0f, 0.3f, 0.5f));
            int modelLoc = glGetUniformLocation(shader.ID, "model");
            glUniformMatrix4fv(modelLoc, 1, GL_FALSE, glm::value_ptr(model));

            glm::mat4 rotate = glm::mat4(1.0f);
            if (i % 3 == 0) {
                rotate = glm::rotate(rotate, (float) glfwGetTime() * glm::radians(50.0f), glm::vec3(0.5f, 1.0f, 0.0f));
            } else {
                rotate = glm::mat4(1.0f);
            }
            int rotateLoc = glGetUniformLocation(shader.ID, "rotate");
            glUniformMatrix4fv(rotateLoc, 1, GL_FALSE, glm::value_ptr(rotate));

            glDrawArrays(GL_TRIANGLES, 0, 36);
        }


        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture[0]);
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, texture[1]);
        // Binds the buffer to the buffer type so glBufferData works on this
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, *EBO);
        //glDrawArrays(GL_TRIANGLES, 0, 36);
        // Tells OpenGL how to render the polygons
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        // Renders vertices in VBO using EBO
        //glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

        // Checks any recent errors
        glCheckError();
    }

    void makeModel(Shader shader) {
        glm::mat4 view = Data.camera->getTransformation();
        glm::mat4 projection = Data.camera->getPerspectiveTransformation();

        shader.use();
        int viewLoc = glGetUniformLocation(shader.ID, "view");
        glUniformMatrix4fv(viewLoc, 1, GL_FALSE, glm::value_ptr(view));
        int projectionLoc = glGetUniformLocation(shader.ID, "projection");
        glUniformMatrix4fv(projectionLoc, 1, GL_FALSE, glm::value_ptr(projection));
    }
}
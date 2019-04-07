#include "data.cpp"

/**
 * Methods used in Pre-Initialisation
 */

namespace core {

    /**
     * DO NOT CALL
     * Called every time the viewport is updated
     * @param window Window for viewport to affect
     * @param width New viewport width
     * @param height New viewport height
     */
    void framebuffer_size_callback(GLFWwindow *window, int width, int height);
    /**
     * DO NOT CALL
     * Called every time the mouse moves
     * @param window Window for mouse to move in
     * @param xpos X-Coordinate of mouse
     * @param ypos Y-Coordinate of mouse
     */
    void mouse_callback(GLFWwindow *window, double xpos, double ypos);
    /**
     * DO NOT CALL
     * Called every time the mouse scrolls
     * @param window Window for mouse to scroll in
     * @param xpos X-Offset of mouse
     * @param ypos Y-Offset of mouse
     */
    void scroll_callback(GLFWwindow *window, double xoffset, double yoffset);

    void framebuffer_size_callback(GLFWwindow *window, int width, int height) {
        // Simply makes the viewport in the whole screen - bottom left to top right
        glViewport(0, 0, width, height);
    }

    void mouse_callback(GLFWwindow *window, double xpos, double ypos) {
        // Sets default values if first time
        if (Mouse.first) {
            Mouse.lastX = xpos;
            Mouse.lastY = ypos;
            Mouse.first = false;
        }

        // Offset is distance moved
        float xoffset = xpos - Mouse.lastX;
        float yoffset = Mouse.lastY - ypos; // reversed since y-coordinates range from bottom to top
        Mouse.lastX = xpos;
        Mouse.lastY = ypos;

        // Movement sensitivity
        float sensitivity = 0.05f;
        xoffset *= sensitivity;
        yoffset *= sensitivity;

        // Adjusts the yaw and pitch according to the mouse movement
        yaw += xoffset;
        pitch += yoffset;

        // Stops you looking below or above
        if (pitch > 89.0f)
            pitch = 89.0f;
        if (pitch < -89.0f)
            pitch = -89.0f;

        // Applies as a vector to the camera forward direction
        glm::vec3 direction;
        direction.x = cos(glm::radians(pitch)) * cos(glm::radians(yaw));
        direction.y = sin(glm::radians(pitch));
        direction.z = cos(glm::radians(pitch)) * sin(glm::radians(yaw));
        cameraFront = glm::normalize(direction);
    }

    void scroll_callback(GLFWwindow *window, double xoffset, double yoffset) {
        // Adjusts the fov depending on scroll
        if (fov >= 1.0f && fov <= 45.0f)
            fov -= yoffset;
        if (fov <= 1.0f)
            fov = 1.0f;
        if (fov >= 45.0f)
            fov = 45.0f;
    }
}
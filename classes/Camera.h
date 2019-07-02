#ifndef OPENGLPROJECT_CAMERA_H
#define OPENGLPROJECT_CAMERA_H

enum Direction {
        FORWARD,
        BACKWARD,
        LEFT,
        RIGHT
};

enum Axis {
    X,
    Y,
    Z
};

enum Rotation {
    PITCH,
    YAW
};

// Minimum possible FOV value
const float MIN_FOV = 1.0f;
// Maximum possible FOV value
const float MAX_FOV = 90.0f;

// Distance beyond which objects will be shown
const float MIN_DISTANCE = 0.1f;
// Distance beyond which objects won't be shown
const float MAX_DISTANCE = 50.0f;

/**
 * Controls the camera
 */
class Camera {
public:
    glm::vec3 cameraPos = glm::vec3(0.0f, 0.0f, 3.0f);
    glm::vec3 cameraFront = glm::vec3(0.0f, 0.0f, 1.0f);
    glm::vec3 cameraUp = glm::vec3(0.0f, 1.0f, 0.0f);

    float pitch = 0;
    float yaw = 0;
    float fov = 80.0f;

    // Display Aspect Ratio
    float ASPECT_RATIO;

    // Whether the pitch is restricted between -90 and 90 degrees
    bool lockPitch = true;

    // Projection Algorithm to use
    int projection = 1;

    // Orthographic dimensions
    float orthoDim = 50.0f;

    /**
     * Creates a camera of the given aspect ratio
     * @param aspectRatio Screen Display ratio
     */
    Camera(float aspectRatio);
    /**
     * Moves the camera to the given position
     * @param position Position to move to
     */
    void setPosition(glm::vec3 position);
    /**
     * Rotates the camera in the direction specified by the angle specified
     * @param rotation Direction to rotate
     * @param angle Angle to rotate in degrees
     */
    void rotate(Rotation rotation, float angle);
    /**
     * Rotates the camera in the direction specified by the angle specified
     * @param rotation Direction to rotate
     * @param angle Angle to rotate in radians
     */
    void rotateRad(Rotation rotation, float angle);
    /**
     * Zooms the camera out by the amount specified (FoV)
     * @param amount Amount to zoom out by
     */
    void zoom(float amount);
    /**
     * Gets the transformation to translate world coordinates to camera coordinates
     * @return Matrix describing the above transformation
     */
    glm::mat4 getTransformation();
    /**
     * Gets the transformation to camera coordinates into a perspective viewspace
     * @return Matrix describing the above transformation
     */
    glm::mat4 getProjectionTransformation();
    glm::vec3 getLooking();
    /**
     * Whether to use Orthographic or Perspective (Perspective by default)
     * @param type 0 for Orthographic or 1 Perspective
     */
    void setProjectionType(int type);

private:
    /**
     * Finds the modulus of a value, restricting it to between -180 and 180 degrees
     * @param in Value to Modulus
     * @return Value after Modulus
     */
    float modulus(float in);


};

#endif //OPENGLPROJECT_CAMERA_H

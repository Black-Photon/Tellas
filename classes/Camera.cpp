#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <stdexcept>
#include <iostream>
#include "Camera.h"

Camera::Camera(float aspectRatio)
{
    ASPECT_RATIO = aspectRatio;
}

void Camera::setPosition(glm::vec3 position)
{
    cameraPos = position;
}

void Camera::rotate(Rotation rotation, float angle)
{
    switch (rotation) {
        case PITCH:
            pitch += angle;

            if(lockPitch) {
                // Stops you looking below or above
                if (pitch > 89.0f)
                    pitch = 89.0f;
                if (pitch < -89.0f)
                    pitch = -89.0f;
            }

            pitch = modulus(pitch);
            break;
        case YAW:
            yaw += angle;

            yaw = modulus(yaw);
            break;
        default:
            throw std::invalid_argument("rotation");
    }

    // Applies as a vector to the mainCamera forward direction
    cameraFront = getLooking();
}

void Camera::rotateRad(Rotation rotation, float angle)
{
    rotate(rotation, glm::degrees(angle));
}

void Camera::zoom(float amount)
{
    // Adjusts the fov depending on scroll
    fov += amount;

    if (fov <= MIN_FOV)
        fov = MIN_FOV;
    if (fov >= MAX_FOV)
        fov = MAX_FOV;
}

glm::mat4 Camera::getTransformation()
{
    return glm::lookAt(cameraPos, cameraPos + cameraFront, cameraUp);
}

void Camera::setProjectionType(int type)
{
    projection = type;
}

glm::mat4 Camera::getProjectionTransformation()
{
    switch (projection) {
        case 0: return glm::ortho(-orthoDim*ASPECT_RATIO, orthoDim*ASPECT_RATIO, -orthoDim, orthoDim, -500.0f, 500.0f);
        case 1: return glm::perspective(glm::radians(fov), ASPECT_RATIO, MIN_DISTANCE, MAX_DISTANCE);
        default: std::cerr << "Could not interpret projection transformation type" << std::endl;
        throw std::exception();
    }
}

glm::vec3 Camera::getLooking()
{
    glm::vec3 direction;
    direction.x = cos(glm::radians(pitch)) * cos(glm::radians(yaw));
    direction.y = sin(glm::radians(pitch));
    direction.z = cos(glm::radians(pitch)) * sin(glm::radians(yaw));
    return glm::normalize(direction);
}

float Camera::modulus(float in)
{
    while (in < -180.0f) {
        in += 360;
    }
    while (in > 180.0f) {
        in -= 360;
    }
    return in;
}
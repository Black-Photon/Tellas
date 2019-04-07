#include "data.cpp"

/**
 * Methods used in Initialisation
 */

namespace core {

    /**
     * Generates a texture, registering it to be used
     * @param texture Location of to put texture reference in
     * @param path Path to texture relative to assets folder
     * @param isPNG Whether the image is a png (has an alpha channel)
     */
    void generateTexture(unsigned int *texture, std::string path, bool isPNG);
    /**
     * Builds the image putting results into values
     * @param VAO Location to save the Vertex Array Object index
     * @param VBO Location to save the Vertex Buffer Object index
     * @param EBO Location to save the Element Buffer Object index
     */
    void buildImage(unsigned int *VAO, unsigned int *VBO, unsigned int *EBO);
    /**
     * Binds VAO and VBO data to the vertices passed
     * @param VAO VAO to bind
     * @param VBO VBO to use
     * @param vertices Vertices to bind to VBO
     * @param length Length of array
     */
    void bindData(unsigned int VAO, unsigned int VBO, float vertices[], int length);

    void generateTexture(unsigned int *texture, const std::string path, bool isPNG) {
        glGenTextures(1, texture);
        glBindTexture(GL_TEXTURE_2D, *texture);

        // Sets the parameters for the texture to use if incorrectly sized
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        // Set's the Mapmap filter
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        int width, height, nrChannels;
        unsigned char *data = stbi_load((Path.assets + path).c_str(), &width, &height, &nrChannels, 0);

        // Whether it uses alpha depends on whether it is a .png or not
        GLenum useAlpha;
        if (isPNG) {
            useAlpha = GL_RGBA;
        } else {
            useAlpha = GL_RGB;
        }

        if (data) {
            // Target Dimension, Mipmap level, Texture Format, width, height, 0, Format, Data Type, Image
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, useAlpha, GL_UNSIGNED_BYTE, data);
            glGenerateMipmap(GL_TEXTURE_2D);
        } else {
            std::cerr << "Failed to load texture" << std::endl;
        }

        stbi_image_free(data);
    }

    void buildImage(unsigned int *VAO, unsigned int *VBO, unsigned int *EBO) {
        // Cube
        float vertices[] = {
                -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
                0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,

                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
                -0.5f, 0.5f, 0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,

                -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                -0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, -0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,

                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                -0.5f, 0.5f, 0.5f, 0.0f, 0.0f,
                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f
        };

        // Unused
        unsigned int indices[] = {
                0, 1, 2,
                1, 2, 3
        };

        // Generates the Element Buffer Object
        glGenBuffers(1, EBO);
        // Binds the buffer to the buffer type so glBufferData works on this
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, *EBO);
        // Allocates memory for and stores the indices data
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(indices), indices, GL_STATIC_DRAW);

        // Generates a Vertex Array Object
        glGenVertexArrays(1, VAO);
        // Generates the Vertex Buffer Objects
        glGenBuffers(1, VBO);

        bindData(VAO[0], VBO[0], vertices, 36 * 5);

        // Note that this is allowed, the call to glVertexAttribPointer registered VBO as the vertex attribute's bound vertex buffer object so afterwards we can safely unbind
        // Unbinds the buffer
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // You can unbind the VAO afterwards so other VAO calls won't accidentally modify this VAO, but this rarely happens. Modifying other
        // VAOs requires a call to glBindVertexArray anyways so we generally don't unbind VAOs (nor VBOs) when it's not directly necessary.
        glBindVertexArray(0);
    }

    void bindData(unsigned int VAO, unsigned int VBO, float vertices[], int length) {
        // Bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
        // Binds the VAO so glVertexAttribPointer and glEnableVertexAttribArray work on this VAO
        glBindVertexArray(VAO);

        // Binds the buffer to the buffer type so glBufferData works on this
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        // Allocates memory for and stores the vertices data
        glBufferData(GL_ARRAY_BUFFER, length * sizeof(*vertices), vertices, GL_STATIC_DRAW);

        // Position
        // Tells OpenGL how to interpret the vertex buffer data
        // Index, Size, Type, Normalized, Stride, Pointer
        glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 5 * sizeof(float), (void *) 0);
        // Enables a generic vertex attribute at the given index
        glEnableVertexAttribArray(0);

//    // Colour
//    // Tells OpenGL how to interpret the vertex buffer data
//    // Index, Size, Type, Normalized, Stride, Pointer
//    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 8 * sizeof(float), (void*)(3 * sizeof(float)));
//    // Enables a generic vertex attribute at the given index
//    glEnableVertexAttribArray(1);

        // Location
        // Tells OpenGL how to interpret the vertex buffer data
        // Index, Size, Type, Normalized, Stride, Pointer
        glVertexAttribPointer(1, 2, GL_FLOAT, GL_FALSE, 5 * sizeof(float), (void *) (3 * sizeof(float)));
        // Enables a generic vertex attribute at the given index
        glEnableVertexAttribArray(1);
    }
}
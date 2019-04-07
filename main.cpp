#include "src/include.cpp"

#include "classes/Shader.h"
#include "src/data.cpp"
#include "src/preInit.cpp"
#include "src/init.cpp"
#include "src/frame.cpp"

namespace core {

    // Prototypes
    /**
     * Initializes the program, creating a basic window
     * @throws initialisationException If it fails to successfully initialise the program
     */
    void preInit(const unsigned int SCR_WIDTH, const unsigned int SCR_HEIGHT, std::string title);

    /**
     * Performs all operations that are required for drawing
     */
    void init();

    /**
     * Checks if the window has received a close signal
     * @return True if the window should close
     */
    bool shouldClose();

    /**
     * Prepares and draws the next frame
     */
    void frame();

    /**
     * Closes the program by deleting any references
     */
    void close();

    void preInit(const unsigned int SCR_WIDTH, const unsigned int SCR_HEIGHT, std::string title) {
        Data.SCR_WIDTH = SCR_WIDTH;
        Data.SCR_HEIGHT = SCR_HEIGHT;

        // Sets all the window settings
        glfwInit(); // Required
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Creates a window object and checks it actually works
        GLFWwindow *window = glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, title.c_str(), nullptr, nullptr);
        if (window == nullptr) {
            std::cout << "Failed to create GLFW window" << std::endl;
            glfwTerminate();
            throw initialisationException("Failed to create GLFW window");
        }
        // Tells it what window to apply OpenGL operations to
        glfwMakeContextCurrent(window);
        // Tells OpenGL to use this function when resizing
        glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
        glfwSetCursorPosCallback(window, mouse_callback);
        glfwSetScrollCallback(window, scroll_callback);

        // Sets up GLAD
        if (!gladLoadGLLoader((GLADloadproc) glfwGetProcAddress)) {
            std::cout << "Failed to initialize GLAD" << std::endl;
            throw initialisationException("Failed to initialize GLAD");
        }

        Data.window = window;
    }

    void init() {
        glEnable(GL_DEPTH_TEST);

        // Program
        auto *shader = new Shader("vertexShader.vert", "fragmentShader.frag", Path.shaders);
        Data.shader = shader;

        stbi_set_flip_vertically_on_load(true);
        generateTexture(Data.texture, "container.jpg", false);
        generateTexture(Data.texture + 1, "awesomeface.png", true);

        shader->use(); // Must activate shader to use uniforms
        shader->setInt("ourTexture", 0);
        shader->setInt("otherTexture", 1);

        Buffers.VAO = new unsigned int[1];
        Buffers.VBO = new unsigned int[1];

        // Vertex attribute object and Virtual Buffer Object
        buildImage(Buffers.VAO, Buffers.VBO, &Buffers.EBO);

        // Creates the actual main viewport, and makes it adjust for window size changes
        glViewport(0, 0, Data.SCR_WIDTH, Data.SCR_HEIGHT);

        Data.lastFrame = glfwGetTime();
    }

    bool shouldClose() {
        return glfwWindowShouldClose(Data.window);
    }

    void frame() {
        float currentFrame = glfwGetTime();
        float deltaTime = currentFrame - Data.lastFrame;
        Data.lastFrame = currentFrame;

        // Input
        processInput(deltaTime);

        // Pre-Render
        prerender();

        // Draw
        draw(*Data.shader, Buffers.VAO[0], &Buffers.EBO, Data.texture);

        // Swap buffers and call events
        glfwSwapBuffers(Data.window);
        glfwPollEvents();
    }

    void close() {
        // Deletes VAO and VBO data from memory
        glDeleteVertexArrays(1, Buffers.VAO);
        glDeleteBuffers(1, Buffers.VBO);

        delete (Data.shader);

        glfwTerminate();
    }

}
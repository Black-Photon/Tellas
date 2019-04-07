#include "src/include.cpp"

#include "classes/Shader.h"
#include "src/data.cpp"
#include "src/preInit.cpp"
#include "src/init.cpp"
#include "src/frame.cpp"

// Prototypes
void preInit();
void init();
bool shouldClose();
void frame();
void close();

/**
 * Initializes the program, creating a basic window
 * @throws initialisationException If it fails to successfully initialise the program
 */
void preInit()
{
    // Sets all the window settings
    glfwInit(); // Required
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    // Creates a window object and checks it actually works
    GLFWwindow* window = glfwCreateWindow(Data.SCR_WIDTH, Data.SCR_HEIGHT, "LearnOpenGL", nullptr, nullptr);
    if (window == nullptr)
    {
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
    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
    {
        std::cout << "Failed to initialize GLAD" << std::endl;
        throw initialisationException("Failed to initialize GLAD");
    }

    Data.window = window;
}

void init()
{
    glEnable(GL_DEPTH_TEST);

    // Program
    auto *shader = new Shader("../../../shaders/vertexShader.vert", "../../../shaders/fragmentShader.frag");
    Data.shader = shader;

    stbi_set_flip_vertically_on_load(true);
    generateTexture(Data.texture, "../../../assets/container.jpg", false);
    generateTexture(Data.texture + 1, "../../../assets/awesomeface.png", true);

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

bool shouldClose()
{
    return glfwWindowShouldClose(Data.window);
}

void frame()
{
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

void close()
{
    // Deletes VAO and VBO data from memory
    glDeleteVertexArrays(1, Buffers.VAO);
    glDeleteBuffers(1, Buffers.VBO);

    delete(Data.shader);

    glfwTerminate();
}
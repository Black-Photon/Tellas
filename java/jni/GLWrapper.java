package jni;

import java.nio.file.FileSystems;

/**
 * Acts as an interface between the C++ and Scala code
 */
public class GLWrapper {

    static {
        // Loads the C++
        System.load(
                    FileSystems.getDefault()
                            .getPath("../../../cmake-build-debug/libOpenGLProject.so")  // Dynamic link
                            .normalize().toAbsolutePath().toString());
    }

    /**
     * Sets up the classes necessary for OpenGL to function
     * @param width Window Width
     * @param height Window height
     * @param title Window title
     */
    public static native void preInit(int width, int height, String title);

    /**
     * Initialises any data required for the application to function
     */
    public static native void init();

    /**
     * Checks if the application should close
     * @return True if application should close
     */
    public static native boolean shouldClose();

    /**
     * Closes the application
     */
    public static native void close();

    // Called Each Frame

    /**
     * Calculates Delta T
     * @return Time since last frame
     */
    public static native float deltaT();

    /**
     * Processes input
     * @param deltaT Time since last frame
     */
    public static native void processInput(float deltaT);

    /**
     * Pre-renders the screen
     * @param r Red percent (0 to 1)
     * @param g Green percent (0 to 1)
     * @param b Blue percent (0 to 1)
     */
    public static native void prerender(float r, float g, float b);


    /**
     * Post-renders the screen
     */
    public static native void postrender();

    /**
     * Swaps the buffers
     */
    public static native void swapBuffers();

    /**
     * Polls for events
     */
    public static native void pollEvents();

    /**
     * Generates a texture returning its reference number
     * @param texture Texture location relative to assets
     * @param isPNG True if the texture has an alpha channel (eg. is a PNG)
     *              (If the texture isn't rendering correctly, but still shows an image, try toggling this)
     * @return Reference number of texture created
     */
    public static native int generateTexture(String texture, boolean isPNG);

    /**
     * Sets the texture as the primary texture in a certain texture slot to be used by the shader
     * @param texture Texture reference number
     * @param location Location to store texture for rendering (0 to 31)
     */
    public static native void useTexture(int texture, int location);

    /**
     * Checks if an error has been recorded, printing it to the screen
     */
    public static native void checkError();
}
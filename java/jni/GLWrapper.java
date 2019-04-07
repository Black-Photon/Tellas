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
     */
    public static native void preInit();

    /**
     * Initialises any data required for the application to function
     */
    public static native void init();

    /**
     * Called every frame, this method will display the next frame
     */
    public static native void frame();

    /**
     * Checks if the application should close
     */
    public static native boolean shouldClose();

    /**
     * Closes the application
     */
    public static native void close();
}
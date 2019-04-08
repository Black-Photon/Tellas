package jni;

/**
 * Represents a 3-Dimensional Vector
 */
public class Vector3 {
    // X, Y and Z components
    public float x, y, z;

    /**
     * Creates a vector with all components equal to 0
     */
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * Creates a vector with all components the same value
     * @param xyz Value to set X, Y and Z to
     */
    public Vector3(float xyz) {
        x = xyz;
        y = xyz;
        z = xyz;
    }

    /**
     * Creates a vector of given parameters
     * @param x X-Value
     * @param y Y-Value
     * @param z Z-Value
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

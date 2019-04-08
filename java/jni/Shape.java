package jni;

/**
 * Defines a shape that can be drawn to the screen
 */
public class Shape {
    /**
     * Location of the shape
     */
    public Vector3 position;

    /**
     * Creates a shape at the given position
     * @param position Position to create shape at
     */
    public Shape(Vector3 position) {
        this.position = position;
    }

    /**
     * Draws the shape to the screen using the given model
     * @param model Model to use to draw to the screen
     */
    public void draw(Model model) {
        drawN(position.x, position.y, position.z, model.ordinal());
    }

    /**
     * Native call to draw to the screen
     * @param x X-Coordinate to draw to
     * @param y Y-Coordinate to draw to
     * @param z Z-Coordinate to draw to
     * @param model Model reference to draw
     */
    private native void drawN(float x, float y, float z, int model);
}

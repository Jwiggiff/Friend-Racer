package Main;

/**
 * This class is for the 2D vector
 *
 * @author Josh Friedman, Om Patel
 * @version 1 - Josh Friedman - June 1 - 30 mins - coded the entire class
 *
 * Variable             Type                    Description
 * _____________________________________________________________________________
 * x                    double                  This is the x value
 * y                    double                  This is the y value
 */
public class Vector2D {
    public double x;
    public double y;

    /**
     * This is the constructor for the Vector2D class
     *
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this adds two vectors
     *
     * @param v the second vector
     */
    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    /**
     * this sets the values of this vector
     * @param x the x value
     * @param y the y value
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

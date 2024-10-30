public class Vec {
    private double x, y;

    /**
     * Creates new Vector
     * @param x x-position
     * @param y y-position
     */
    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x-value
     * @return x-component of Vector
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y-value
     * @return y-component of Vector
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y-value
     * @param y new y-component of Vector
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets x-value
     * @param x new x-component of Vector
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Adds Vector to this Vector
     * @param b Vector to be added
     * @return this
     */
    public Vec add(Vec b) {
        x += b.x;
        y += b.y;
        return this;
    }

    /**
     * Multiply each component by some double
     * @param m double to multiply each component
     * @return this
     */
    public Vec mult(double m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Gets magnitude of Vector
     * @return magnitude
     */
    public double getMag() {
        return Math.sqrt(x*x+y*y);
    }

    /**
     * Sets magntitude of Vector
     * @param val new magnitude
     * @return this
     */
    public Vec setMag(double val) {
        return mult((val/getMag()));
    }

    /**
     * Limits magnitude
     * @param max max magnitude 
     * @return this
     */
    public Vec limit(double max) {
        if (getMag() > max) {
            setMag(max);
        }
        return this;
    }

    /**
     * Limits x-component of Vector
     * @param min minimum x-value
     * @param max maximum x-value
     */
    public void limitX(double min, double max) {
        x = Math.min(max, Math.max(x, min));
    }

    /**
     * Limits y-component of Vector
     * @param min minimum y-value
     * @param max maximum y-value
     */
    public void limitY(double min, double max) {
        y = Math.min(max, Math.max(y, min));
    }


    /**
     * Returns String representation of object in form "(x, y)"
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
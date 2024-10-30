public class Vec {
    private double x, y;

    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Vec add(Vec b) {
        x += b.x;
        y += b.y;
        return this;
    }

    public Vec mult(double m) {
        x *= m;
        y *= m;
        return this;
    }

    public double getMag() {
        return Math.sqrt(x*x+y*y);
    }

    public Vec setMag(double val) {
        return mult((val/getMag()));
    }

    public Vec limit(double max) {
        if (getMag() > max) {
            setMag(max);
        }
        return this;
    }

    public void limitX(double min, double max) {
        x = Math.min(max, Math.max(x, min));
    }

    public void limitY(double min, double max) {
        y = Math.min(max, Math.max(y, min));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
public class NormalP extends Platform {
    public NormalP(double x, double y) {
        super(x, y);
        setWidth(60);
        setHeight(10);
    }

    /**
     * Draws NormalP platform.
     */
    public void draw() {
        noStroke();
        fill(18, 179, 29);

        rectMode(CENTER);
        rect((float) getX(), (float) getY(), (float) getWidth(), (float) getHeight(), 30);
    }
}
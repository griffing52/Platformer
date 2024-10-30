import java.util.Random;

public class MovableP extends Platform {
    // original width
    private final double ogWidth = 60;

    // angle used for sin calculation
    private double theta;
    
    public MovableP(double x, double y, Random r) {
        super(x, y);
        setWidth(ogWidth);
        setHeight(10);

        // theta = Math.random()*7;
        theta = r.nextDouble()*7;
    }

    /**
     * Draws MovableP platform.
     */
    public void draw() {
        noStroke();
        fill(50, 164, 168);

        rectMode(CENTER);
        rect((float) getX(), (float) getY(), (float) getWidth(), (float) getHeight(), (float) 30);

        update();
    }

    /**
     * Updates theta value and sets width to sin wave of it.
     */
    public void update() {
        theta += 0.03;
        setWidth((Math.sin(theta) + 1) * 0.5 * ogWidth);
    }
}
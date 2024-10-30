import java.util.Random;

public class BreakableP extends Platform {
    private final double ogAge = 21;
    private double age;

    public BreakableP(double x, double y, Random r) {
        super(x, y);
        setWidth(60);
        setHeight(10);
        age = ogAge;
    }

    /**
     * Draws BreakableP platform.
     */
    public void draw() {
        noStroke();
        fill(148, 74, 18, (float) (age/ogAge * 255));

        rectMode(CENTER);
        rect((float) getX(), (float) getY(), (float) getWidth(), (float) getHeight(), 30);
    }

    @Override
    public boolean isColliding(Vec playerPos) {
        if (age < 0) setWidth(0);
        else if (super.isColliding(playerPos)) {
            age--;
            return true;
        }
        return false;
    }
}
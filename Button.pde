public class Button extends Platform {
    private String text;
    private boolean unlocked;

    public Button(double x, double y, double w, double h, String text) {
        super(x, y);
        setWidth(w);
        setHeight(h);
        this.text = text;
        unlocked = true;
    }

    /**
     * Finds if the button is unlocked.
     * @return true if unlocked
     */
    public boolean isUnlocked() {
        return unlocked;
    }

    /**
     * Locks the button.
     * @return this
     */
    public Button lock() {
        unlocked = false;
        return this;
    }

    /**
     * Unlocked the button.
     */
    public void unlock() {
        unlocked = true;
    }

    /**
     * Draws Button.
     */
    public void draw() {
        stroke(255);
        if (unlocked) fill(96, 66, 245);
        else fill(27, 19, 69);

        rectMode(CENTER);
        rect((float) getX(), (float) getY(), (float) getWidth(), (float) getHeight(), 10);

        fill(255);
        textSize(30);
        textAlign(CENTER);
        text(text, (float) getX(), (float) getY()+textAscent()*0.4);
    }
}
public class Announcement {
    private final double frames;
    private double age;
    private String t;
    public Announcement(String text, double frames) {
        this.frames = frames;
        this.t = text;
    }

    /**
     * Draws announcement
     */
    public void draw() {
        fill(255);
        rectMode(CORNERS);
        text(t, width*0.1, height*0.4, width*0.9, height);
    }

    /**
     * True if should display announcement.
     * @return true if announcement has not reached maximum frame count yet.
     */
    public boolean isAlive() {
        age++;
        return age < frames;
    }
}
public abstract class Platform {
    private double x, y, width, height;
    
    public Platform(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets platform's x-position.
     * @return platform's x-value
     */
    public double getX() {
        return x;
    }

    /**
     * Gets platform's y-position.
     * @return platform's y-value
     */
    public double getY() {
        return y;
    }

    /**
     * Gets platforms width.
     * @return platform's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets platforms height.
     * @return platform's height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets platform's width.
     * @param width new width for platform
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets platform's height.
     * @param height new height for platform
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Adds amount to the y-value.
     * @param amount amount being added to y-value
     */
    public void addY(double amount) {
        y += amount;
    }

    /**
     * Returns true if the player is colling with the platform
     * @param playerPos player position as a vector 
     * @return true if player is colling with the platform
     */
    public boolean isColliding(Vec playerPos) {
        return (playerPos.getX() >= x-getWidth()*0.5 && playerPos.getX() <= x+getWidth()*0.5) && (playerPos.getY() >= y-getHeight()*0.5 && playerPos.getY() <= y+getHeight()*0.5);
    }

    /**
     * Returns true if the player has passed this platform
     * @param playerPos player position as a vector
     * @return true if the player has passed this platform
     */
    public boolean beenPassed(Vec playerPos) {
        return (playerPos.getY() < y);
    }

    /**
     * Allow platforms to change player attributes (position, velocity, etc.)
     */
    public void update(Player p) {}

    abstract public void draw();
}
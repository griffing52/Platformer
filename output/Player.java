import java.awt.Color;

public class Player {
    public Vec SPEED = new Vec(0.2, 0);
    public Vec NEGATIVE_SPEED = new Vec(-0.2, 0);
    public Vec JUMP_SPEED = new Vec(0, -4);

    private Vec pos, vel, acc;
    private double height = 30, width = 10;
    private Color c;
    private boolean jumping = true;

    public Player(double x, double y) {
        pos = new Vec(x, y);
        vel = new Vec(0, 0);
        acc = new Vec(0, 0);
        setColor(255);
    }

    /**
     * Applies force to Player sprite
     * @param force Vector of force
     */
    public void applyForce(Vec force) {
        acc.add(force);
    }

    /**
     * Changes player color rgb
     * @param r red
     * @param g green
     * @param b blue
     */
    public void setColor(int r, int g, int b) {
        c = new Color(r, g, b);
    }

    /**
     * Changes player color
     * @param v grayscale color
     */
    public void setColor(int v) {
        c = new Color(v, v, v);
    }

    /**
     * Gets player's width
     * @return player's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets player's height
     * @return player's height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets player's position as a vector
     * @return player's position vector
     */
    public Vec getPos() {
        return pos;
    }

    /**
     * Gets player's velocity as a vector
     * @return player's velocity vector
     */
    public Vec getVel() {
        return vel;
    }

    /**
     * Limits velocity vector to prevent player from going through platform.
     * Simulated arbitruary friction of -24% each frame.
     */
    public void collide() {
        // prevents player from falling through platform
        acc.limitY(Double.MIN_VALUE, 0);

        // friction!
        vel.setX(vel.getX() * 0.76);
    }

    /**
     * Returns true is the player is jumping or is falling.
     * False if the player is allowed to jump.
     * @return true if player can't jump again; false if it can.
     */
    public boolean isJumping() { 
        return jumping || vel.getY() > 0.09;
    }

    /**
     * Sets jumping variable.
     * @param b true if jumping; false if not.
     */
    public void setJump(boolean b) {
        jumping = b;
    }

    /**
     * Updates player's position, velocity, and acceloration.
     * Accounts for player overflow on the x-axis.
     * @param w width of screen
     */
    public void update(double w) {
        vel.add(acc);

        vel.limitX(-3, 3);
        vel.limitY(-5, 10);
        
        pos.add(vel);
        acc.mult(0);

        // screen overflow
        if (pos.getX() + this.width * 0.5 < 0) pos.setX(w+width*0.5);
        else if (pos.getX() - this.width * 0.5 > w) pos.setX(0-width*0.5);
    }

    /**
     * Draws player
     */
    public void draw() {
        rectMode(CENTER);
        noStroke();
        fill(c.getRGB());
        rect((float) (pos.getX()), (float) (pos.getY()-height*0.5), (float) width, (float) height);
    }
}
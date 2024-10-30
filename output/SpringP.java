public // NOT USED !!!EXPERIMENTAL!!!
class SpringP extends Platform {
    private boolean sprung;
    private Vec springForce;

    public SpringP(double x, double y) {
        super(x, y);
        setWidth(60);
        setHeight(10);
        sprung = false;
        springForce = new Vec(0, -100);
    }

    /**
     * Draws SpringP platform.
     */
    public void draw() {
        noStroke();
        fill(240, 19, 21);

        rectMode(CENTER);
        rect((float) getX(), (float) getY(), (float) getWidth(), (float) getHeight(), 30);
    }

    @Override
    public void update(Player p) {
        if (!sprung && isColliding(p.getPos())) {
            p.applyForce(springForce);
            sprung = true;
        }
    }
}
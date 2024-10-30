import java.util.Random;
import java.util.ArrayList;
import java.util.Hashtable;

public class Level {
    private Vec gravity = new Vec(0, 0.09);
    private final Random r;
    private int score;
    private boolean goal, announced = false;

    private Player player;
    private ArrayList<Platform> platforms;
    
    private Hashtable<Long, Integer> highscores;
    private long seed;

    public Level(Player p, long seed, Hashtable<Long, Integer> highscores) {
        player = p;

        r = new Random(seed);
        score = 0;
        goal = false;

        this.seed = seed;
        this.highscores = highscores;

        platforms = new ArrayList<Platform>();  

        // starting platform
        platforms.add(new NormalP(width/2, height));

        fillPlatforms();
    }

    /**
     * Fills the blank space at the top of screen with more platforms
     */
    public void fillPlatforms() {
        for (double y = platforms.get(platforms.size()-1).getY(); y > 0;) {
            y -= r.nextDouble() * 10 + 40;
            addPlatform(r.nextDouble() * width, y);
            // y -= Math.random() * 10 + 40;
            // addPlatform(Math.random() * width, y);
        }
    }

    /**
     * Adds a random platform at position (x, y).
     * 10% chance for BreakableP platform.
     * 20% chance for MovableP platform.
     * 70% chance for NormalP platform.
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    public void addPlatform(double x, double y) {
        // double seed = Math.random();
        // platforms.add(new SpringP(x, y));
        double seed = r.nextDouble();
        if (seed < 0.1) platforms.add(new BreakableP(x, y, r));
        else if (seed < 0.3) platforms.add(new MovableP(x, y, r));
        else platforms.add(new NormalP(x, y));
    }

    /**
     * Clears the screen. Draws the player. Draws the platforms.
     */
    public void draw() {
        background(0);
        player.draw();

        for (Platform p: platforms) {
            p.draw();
            p.update(player);
        }

        fill(255);
        textSize(60);
        text(score, width/2, height*0.1);

        // update highscore
        if (score > highscores.get(seed)) highscores.put(seed, score);
        textSize(20);
        text("Highscore: " + highscores.get(seed), width/2, height*0.1+30);

        // check to see if goal reached
        if (goal) {
            int g = (int) (seed+1)*5;
            text("Goal to reach: " + g, width/2, height * 0.1+60);
            if (score >= g && !announced) {
                announced = true;
                announce("Goal Reached! You can move on to the next level by pressing ESC on your keyboard", 600);
                unlockNextLevel();
            }
        }
    }

    /**
     * Updates the player and platforms. Accounts for user input and collisions.
     * Locks the player at no more than 25% up the screen.
     * 
     * @param keys boolean array of key presses: w:0, a:1, d:2.
     */
    public void update(boolean[] keys) {
        // a
        if (keys[1]) {
            player.applyForce(player.NEGATIVE_SPEED);
        // d
        } if (keys[2]) {
            player.applyForce(player.SPEED);
        }

        player.applyForce(gravity);
        player.update(width);

        // check for collisions in half the platforms for optimization
        for (int i = 0; i < platforms.size(); i++) {
            Platform p = platforms.get(i);
            if (p.beenPassed(player.getPos()) && p.isColliding(player.getPos())) {
                player.collide();

                if (player.getVel().getY() > gravity.getY()) {
                    player.getVel().limitY(Double.MIN_VALUE, 0);
                    player.getPos().setY(p.getY()-p.getHeight()*0.5);
                }
                
                player.setJump(false);
            }

            // camera sliding
            if (player.getPos().getY() < height * 0.75) {
                if (player.getVel().getY() < 0) {
                    p.addY(player.getVel().getY() * -1);
                }
            }

            // fill top blank space w/ new platforms
            if (p.getY() > height + 20) {
                fillPlatforms();
                platforms.remove(p);
                score++;
            }
        }

        // locks player's position at 75% down the screen
        if (player.getPos().getY() < height * 0.75)
            player.getPos().setY(player.getPos().getY() - player.getVel().getY());

        // w
        if (keys[0] && !player.isJumping()) {
            player.applyForce(player.JUMP_SPEED);
            player.setJump(true);
        }
    }

    /**
     * True if player is below the bottom of the screen
     * @return if player is below bottom of screen
     */
    public boolean playerIsOffScreen() {
        return player.getPos().getY() > height+1.5*player.getHeight();
    }

    /**
     * Sets the goal trait to true, which means the level will be treated with a goal.
     */
    public void setGoal() {
        goal = true;
    }

    /**
     * Returns true if the level has a goal
     * @return true or false if goal or not
     */
    public boolean isGoal() {
        return goal;
    }
}
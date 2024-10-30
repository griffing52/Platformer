boolean[] keys = new boolean[3]; // 0:w, 1:a, 2:s

Level level;
Hashtable<Long, Integer> highscores;
ArrayList<Announcement> announcements;

Button freePlayButton, levelsButton;
Button[] levelsButtons;

boolean started, levelM, levels;
long seed;
int currLevel = 0;

void setup() {
    size(400, 600);

    freePlayButton = new Button(width/2, height*0.6, width*0.6, height*0.1, "Freeplay");
    levelsButton = new Button(width/2, height*0.73, width*0.6, height*0.1, "Levels");
    
    levelsButtons = new Button[10]; 
    double margin = height*0.05;
    double step = (height-margin)/levelsButtons.length;
    for (int i = 0; i < levelsButtons.length;) {
        levelsButtons[i] = new Button(width/2, margin + step*i + step*0.25, width*0.8, step*0.5, "Level " + ++i).lock();
    }
    levelsButtons[0].unlock();

    highscores = new Hashtable<Long, Integer>();
    announcements = new ArrayList<Announcement>();

    seed = new Random().nextLong();

    newLevel();
}

void draw() {
    if (started || levels) {
        level.update(keys);
        level.draw();
        if (level.playerIsOffScreen()) {
            boolean goal = level.isGoal();
            newLevel();
            if (goal) level.setGoal();
        }
    } else if (levelM) levelsMenu();
    else startMenu();

    // update announcements
    for (int i = 0; i < announcements.size(); i++) {
        Announcement a = announcements.get(i);
        a.draw();
        if (!a.isAlive()) announcements.remove(a);
    }
}

void unlockNextLevel() {
    levelsButtons[++currLevel].unlock();
}

void announce(String text, double frames) {
    announcements.add(new Announcement(text, frames));
}

void newLevel() {
    if (highscores.get(seed) == null) highscores.put(seed, 0);
    level = new Level(
        new Player(width/2, height-15),
        seed,
        highscores
    );
}

void startMenu() {
    background(0);
    started = false;
    levelM = false;
    levels = false;
    
    freePlayButton.draw();
    levelsButton.draw();
}

void levelsMenu() {
    background(0);
    started = false;
    levelM = true;
    levels = false;

    for (Button b: levelsButtons) {
        b.draw();
    }
}

void mousePressed() {
    Vec point = new Vec(mouseX, mouseY);
    if (started) {} 
    else if (levelM) {
        for (int i = 0; i < levelsButtons.length; i++) {
            if (levelsButtons[i].isUnlocked() && levelsButtons[i].isColliding(point)) {
                levels = true;
                levelM = false;
                started = false;
                seed = (long) i;
                newLevel();
                level.setGoal();
                return;
            }
        }
    } else {
        if (freePlayButton.isColliding(point)) {
            started = true;
            seed = new Random().nextLong();
            newLevel();
        }
        else if (levelsButton.isColliding(point)) levelM = true;
    }
}

void keyPressed() {
    if (key == 'w') keys[0] = true;
    if (key == 'a') keys[1] = true;
    if (key == 'd') keys[2] = true;
    if (key == 'r') {
        boolean goal = level.isGoal();
        newLevel();
        if (goal) level.setGoal();
    } if (key == 'n' && !levels) {
        seed = new Random().nextLong();
        newLevel();
    }
    if (key == ESC) {
        key = 0;
        announcements.clear();
        if (levels) levelsMenu();
        else startMenu();
    }
    if (key == 'e') System.out.println(seed);
}

void keyReleased() {
    if (key == 'w') keys[0] = false;
    if (key == 'a') keys[1] = false;
    if (key == 'd') keys[2] = false;
}
# Platformer

This Java platformer game code is designed to create an interactive, multi-level environment with both free play and structured levels. The player can navigate using keyboard controls, aiming to reach a goal in each level, and unlocking levels sequentially. The game features a start menu, levels menu, and on-screen announcements to enhance gameplay.

## Key Features

- **Menu System**: Allows the player to choose between Freeplay mode and Level mode.

- **Keyboard Controls**: Players use keys for movement (`w`, `a`, `d`) and resetting (`r`) to control the character.

- **Level System**: Offers sequential levels with locked/unlocked states, allowing progression through completion.

- **Announcements**: On-screen messages notify players of game events and updates.

- **Dynamic Level Generation**: Levels are generated with random seeds for varied experiences in Freeplay mode.

## Requirements

To run this game, you need a basic Java development environment such as:

- **Java Development Kit (JDK)**

- **Processing IDE** (for visual and UI elements if necessary)

## Explanation of Core Functions

### `setup`

Initializes game settings and interface elements:

- **Buttons**: Initializes buttons for Freeplay and Levels modes.

- **Levels**: Creates level selection buttons, initially locking all but the first level.

- **Highscores and Announcements**: Initializes storage for high scores and announcements.

- **Random Seed Generation**: Generates a unique seed to create randomized levels.

### `draw`

Handles the main game loop:

- **Game Modes**: Checks if the player is in a game level, level selection, or start menu.

- **Level Updates**: Updates and draws the level and player progress if a level is active.

- **Announcements**: Updates and draws on-screen messages, removing them when expired.

### `unlockNextLevel`

Unlocks the next level button upon level completion, enabling sequential progression.

### `announce`

Displays a text announcement on the screen for a specified duration.

### `newLevel`

Generates a new level using the current seed, initializing the player's starting position and resetting the level's environment.

### `startMenu` & `levelsMenu`

Displays either the main start menu or levels menu based on the player's mode selection.

### `mousePressed`

Handles mouse interactions for selecting Freeplay or Levels modes, as well as specific level buttons in the levels menu.

### `keyPressed` & `keyReleased`

Processes keyboard inputs for controlling player movement (`w`, `a`, `d`), resetting the level (`r`), generating a new level (`n`), and quitting to the menu (`ESC`). It also outputs the current seed when `e` is pressed, useful for debugging or saving level configurations.

## Core Classes

- **`Button`**: Represents interactive buttons on the screen (e.g., Freeplay, Levels, and individual level selection).

- **`Level`**: Represents a game level, including obstacles and player positioning.

- **`Player`**: Represents the player's character, including movement and interactions.

- **`Announcement`**: Handles timed on-screen messages that provide feedback to the player.

## Usage Example

1\. **Run the Game**: Launch the game, starting in the main menu.

2\. **Freeplay Mode**: Click "Freeplay" to generate a random level with a unique seed.

3\. **Level Mode**: Click "Levels" to open the level selection screen, then select a level.

4\. **Control the Player**:

   - Use **`w`**, **`a`**, and **`d`** keys to navigate the level.

   - Press **`r`** to restart the current level.

   - Press **`n`** in Freeplay mode to generate a new random level.

   - Press **`ESC`** to return to the menu.

5\. **Track Progress**: Upon reaching the level goal, the next level unlocks.

## Potential Improvements

- **Highscore System**: Add scoring for each level based on completion time or fewer resets.

- **Save and Load System**: Allow players to save their progress and return later.

- **Enhanced Graphics**: Add more graphics for visual appeal, such as background images or animations.

- **Power-Ups and Hazards**: Add more variety to gameplay with obstacles or boosts for the player.

This code provides a foundation for a fun, interactive platformer game, offering a range of player challenges and room for creative extensions.

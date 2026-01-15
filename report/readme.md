# WIX1002 FUNDAMENTALS OF PROGRAMMING - TOPIC 6: EinStein  würfelt nicht! Puzzle Solver 
# Project Overview
## Introduction
In 1926, Albert Einstein challenged the scientific world with a single sentence: "I am convinced that He (God) does not play dice."

This Java project brings that philosophy to life. We are reimagining the board game EinStein würfelt nicht by removing the element of chance entirely. By replacing the roll of the dice with fixed, pre-determined sequences, we have converted a game of probability into a rigorous test of logic and planning.

The Challenge Players must navigate a target piece across a 10x10 grid using King-style movements. The board is laden with obstacles, and the clock is ticking: the solver must reach Square 0 in under 30 moves. This requires not just gameplay, but the implementation of efficient pathfinding algorithms to master the deterministic chaos.

## Basic Requirements
1. System Initialization and Data Handling
   * GameLoader class (Constructor) - Required to obtain game data from external files and initialize the necessary data.
   * GameLoader class (printGameDetails() method) - Static method, required to output the initial game configuration into moves.txt.

2. Core Game Logic and State Management
   * GameState class (generatePossibleMoves() method) - Required to calculate all valid movements by the piece based on its current position.
   * GameState class (isWinning() method) - Required to check whether the target piece has reached "Square 0" successfully.

3. Player Implementation and Decision Making
   * abstract Player class (printMove() method) - An abstract method that has shared functionality across every player, which logs every move into moves.txt.
   * HumanPlayer class (chooseMove() method) - Required to receive manual input, allowing users to select moves.
   * RandomPlayer class (chooseMove() method) - Required to select a move randomly.

4. Main Application Control
   * GameMain class (main() method) - Required to prompt users to select game mode (Human, Random or AI).
   * GameMain class (main() method) - Required for instantiation of chosen player type and executes move selection process.
   * GameMain class (main() method) - Required to output the final results, whether the user won or lost the game.

## Added Features (Approach to the Problem)
1. GameLoader class
   * getInitialPosition() method - Non-static method, used to get the initial position without the need to reaccess the file.
   * getTargetPiece() method - Non-static method, used to get the target piece without the need to reaccess the file.
   * getDiceRoll() method - Non-static method, used to get the current dice roll without the need to reaccess the file.

2. GameState class
   * Constructor - Used to initialise the current piece positions and the target piece into the instance.
   * getCurrentPositions() method - Non-static method, used to get the current positions after each update.
   * updatePositions() method - Non-static method, used to update the positions after moving.
   * generatePossiblePieces() method - Non-static method, used to obtain possible movable pieces under certain circumstances.

3. GameMain class
   * gameMode() method - Static method, used to obtain game mode and player name (Human Player).
   * getLevel() method - Static method, used to obtain the chosen level and initialise the whole game system.
   * mainGame() method - Static method, used for the instantiation of the chosen player and handles game logic.
   * humanName() method - Static method, used to obtain the human player name.

## Extra Features
1. Level 1
2. Level 2
3. Level 3
4. Level 4
(will talk about what restrictions, what algorithm used, and the performance)

# Solution
## Module Overview (Class Structure)

| Module | Description |
| :--- | :--- |
| **GameMain** | The main class. Responsible for user input, player selection, and the primary game loop. |
| **GameLoader** | Handles initial external file I/O. Responsible for reading level configurations and ensuring all initial data is loaded into memory. |
| **GameState** | Handles logic and rules. Responsible for validating valid moves and detecting the win condition. |
| **Player (Abstract)** | The blueprint for all classes of players, ensuring the same logic for logging to `moves.txt`. |
| **HumanPlayer** | Responsible for creating the prompt for the user to choose their moves. |
| **RandomPlayer** | Responsible for creating the random logic to solve the puzzle. |
| **AIPlayer** | Responsible for finding the most optimal solution for the puzzle using Beam Search. |

# How to compile and run
1. Change your terminal directory to the folder EWN-Puzzle-main
```
cd .\EWN-Puzzle-main\
```

2. Compile all of the Java files inside the folder
```
javac *.java
```

3. Run the main file (GameMain)
```
java GameMain
```

4. To check the moves visually, run the EWN_GUI.jar file
```
java -jar EWN_GUI.jar
```

# User guide / How to play
# Screenshots
# Contribution
  1. Ooi Yong Zhe:
     - Done major changes to GameState to refine and reduce redundancy within classes
  3. Chew Jee Syuen:
     - Complete GameLoader part
     - Complete GameMain part
     - Write report
  4. Lim Kai Hern:
     - Complete GameState part
     - Refine Read me 
     - Write report
  5. Chaang Wai Chiu:
     - Complete HumanPlayer part
  6. Si Jun Tian:
     -Complete RandomPlayer part
# Challenges faced
  

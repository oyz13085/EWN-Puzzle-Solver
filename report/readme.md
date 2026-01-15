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

## Module Interaction Table
|Interaction|Moudle A|Module B|Purpose|
|:---|:---|:---|:---|
|**Data Flow**|`GameLoader`|`GameMain`|Passes level data and dice sequences to the main loop.|
|**Validation**|`GameState`|`AIPlayer`|AI uses the logic in `GameState` to simulate future moves without breaking rules.|
|**Output**|`Player`|`moves.txt`|Every player type uses the same method to ensure the move log is consistent.|
|**Control**|`GameMain`|`GameState`|Main triggers the winning check after every move.|

## AI Strategy: Beam Search Detail
**Beam Search** is a heuristic search algorithm that explores a search space by keeping only a limited number of the most promising "candidates" or "paths" at each level. 

**How It Works**
Beam Search utilizes a parameter called **Beam Width (*k*)**. This number determines how many partial paths the algorithm "remembers" at any given time.
1) **Initialization**: Start at the beginning (Square 0) and generate all possible next moves.
2) **Expansion**: For every path currently tracking, generate all possible successor moves.
3) **Scoring**: Use a **Heuristic Function** to assign a score to every new path. The function used is shown below:
```math
Score = (D * 100) + E
```
* $D$ (**Chebyshev Distance**):  Calculated as $max(\Delta x, \Delta y)$ from the target piece to $(0,0)$. This accounts for diagonal moves.
* $E$ (**Enemy Count**): A small penalty for each remaining enemy piece, encouraging the AI to capture obstacles if they block the path.
* **Safety Trigger**: If the target piece is captured in a simulated state, that state is assigned a score of $\infty$ and immediately discarded.

4) **Pruning**: Sort all the paths by their scores and keep only the top $k$ paths. Discard the rest.
5) **Repeat**: Continue expanding and pruning until the goal or the move limit is reached.

**Beam Search vs Other Algorithm**
|Feature|Greedy Algorithm|Beam Search|BFS(Breadth-First Search)|
|:---|:---|:---|:---|
|**Search Width**|1 (The Very Best)|k (The Top k Best)|$\infty$ (Every single move)|
|**Memory Usage**|Very Low|Low to Moderate|Extremely High|
|**Optimality**|Rarely optimal|Likely sub-optimal|Guaranteed optimal|
|**Speed**|Fastest|Fast|Slowest|

Thus, **Beam Search** is used as there is a move limit and time limit for the AI Player. Since the dice rolls are fixed, Beam Search can look several moves ahead and get the best move. The only downside is that if the optimal solution starts with a "bad-looking" path, it might delete the path at the start and fail to find the optimal path.

## Flow Chart
<img width="726" height="1072" alt="image" src="https://github.com/user-attachments/assets/d8d4bf60-bf24-4b0a-a809-f83e1e9d7767" />


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
# Screenshots (Test Run)
1. Human Player
   (a) Level 1
   <img width="935" height="518" alt="image" src="https://github.com/user-attachments/assets/91a9f97e-87d8-4171-bc8e-3c6ec23b4710" />
   <img width="940" height="587" alt="image" src="https://github.com/user-attachments/assets/4387ac94-f88b-4c15-a505-1c120899cae2" />
   (b) Level 2
   <img width="940" height="518" alt="image" src="https://github.com/user-attachments/assets/b396830a-e32b-426c-825f-e74547e84817" />
   <img width="940" height="552" alt="image" src="https://github.com/user-attachments/assets/4c0eb645-44f3-4b75-b290-057e82927a3e" />
   (c) Level 3
   <img width="940" height="512" alt="image" src="https://github.com/user-attachments/assets/102037a4-f1e2-4bb0-a750-6b0337ea421e" />
   <img width="940" height="552" alt="image" src="https://github.com/user-attachments/assets/4f1fe6b4-47d5-4231-ba6d-fbb3985e4203" />
   (d) Level 4
   <img width="940" height="522" alt="image" src="https://github.com/user-attachments/assets/166a5376-97d4-4e13-862c-9355a5ac9562" />
   <img width="936" height="523" alt="image" src="https://github.com/user-attachments/assets/22b9062a-3627-4a69-a5ec-426184d7e9aa" />
   <img width="940" height="552" alt="image" src="https://github.com/user-attachments/assets/83d3be28-f606-4255-8879-d5f7b9d4f639" />

2. Random Player
3. AI Player
   (a) Level 1 (Extra Feature: Solved within 6 moves)
   <img width="935" height="560" alt="image" src="https://github.com/user-attachments/assets/579ca9d5-daf5-4d8c-bd6a-009b8a5747c7" />
   <img width="940" height="551" alt="image" src="https://github.com/user-attachments/assets/931a557e-ccd3-4b64-8553-f976197f1a8f" />
   (b) Level 2 (Extra Feature: Solved within 10 moves)
   <img width="932" height="512" alt="image" src="https://github.com/user-attachments/assets/54645640-8bd2-418f-ba68-20ed3de4d58a" />
   <img width="940" height="551" alt="image" src="https://github.com/user-attachments/assets/51e3de34-6ed5-45b8-b034-44094bb46028" />
   (c) Level 3 (Extra Feature: Solved within 10 moves)
   <img width="907" height="496" alt="image" src="https://github.com/user-attachments/assets/0cabf11a-094b-432a-b894-4e0c9948cf4a" />
   <img width="940" height="549" alt="image" src="https://github.com/user-attachments/assets/e63f35af-b442-439f-8733-7939b09bd1cd" />
   (d) Level 4 (Extra Feature: Solved within 15 moves)
   <img width="918" height="504" alt="image" src="https://github.com/user-attachments/assets/b703f02d-fe8c-457e-88c3-eb62056c180d" />
   <img width="940" height="587" alt="image" src="https://github.com/user-attachments/assets/2baccdda-7329-41d1-8d65-8368e0feba7d" />
















  

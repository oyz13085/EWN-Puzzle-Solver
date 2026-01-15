# WIX1002 FUNDAMENTALS OF PROGRAMMING - TOPIC 6: EinStein  würfelt nicht! Puzzle Solver 
# Team Members:
    1. OOI YONG ZHE (25069059)
    2. CHEW JEE SYUEN (25066410)
    3. LIM KAI HERN (23116000)
    4. CHAANG WAI CHIU (23104771)
    5. SI JUN TIAN (25068553)
# Project Overview
Introduction\
In 1926, Albert Einstein challenged the scientific world with a single sentence: "I am convinced that He (God) does not play dice."\

This Java project brings that philosophy to life. We are reimagining the board game EinStein würfelt nicht by removing the element of chance entirely. By replacing the roll of the dice with fixed, pre-determined sequences, we have converted a game of probability into a rigorous test of logic and planning.\

The Challenge Players must navigate a target piece across a 10x10 grid using King-style movements. The board is laden with obstacles, and the clock is ticking: the solver must reach Square 0 in under 30 moves. This requires not just gameplay, but the implementation of efficient pathfinding algorithms to master the deterministic chaos.

# Basic Features
1. GameLoader.java - Constructor\
   (a) Constructor is created to read data from the given filename (level1.txt, level2.txt, level3.txt and level4.txt).\
   (b) Data read from the files are stored in appropriate variables (targetPiece, initialPosition and diceSequence).\
   (c) Validation was carried out using try and catch for the selectedLevel(1-4).
   
2. GameLoader.java - printGameDetails()\
   (a) printGameDetails() method was used to print all the game setup details into "moves.txt" file\
   (b) Inside the file, the first line will print "Human Player", "Random Player" or "AI Player" based on the mode. If it is         "Human Player", it will also print out the name of the player.\
   (b) Second line, print out the dice Sequence (30 sequences).\
   (c) Third line, print out the target piece.\
   (d) Fourth line, print out the initial position of all the 6 pieces.\
   (e) Fifth line onwards until last line, print out the position of the pieces after each move until the game ends.
   
3. GameState.java - generatePossibleMoves()\
   (a) generatePossibleMoves() method is used to determine the possible moves for each piece.\
   (b) It works by taking the piece input and using that input to calculate the steps that are movable while avoiding space 22.\
   (c) First, it detects if the pieces from dice roll are still on the board, it will calculate the closest two pieces
       in terms of bigger and smaller and outputs to user.\
   (d) Calculation is then done by separating the piece position with mathematical equations into rows and columns and 
       then its check with different conditions to ensure that the value is not out of bounds and does not interfere with the space 22.\
   (e) All of the valid positions are then stored in a arraylist call moves.
   
4. GameState.java - isWinning() \
    (a) isWinning() is used to check if the winning conditions are met.\
    (b) If the current position of the pieces is at the "0" space, the system will output "You win!".

5. HumanPlayer.java - chooseMove() \
    (a) Extends the Player class to handle move selection for a human player. \
    (b) Scanner reads input from the terminal then fetches valid moves from GameState.generatePossibleMoves() \
    (c) Then, prompt the human player to make a move by outputs of movable pieces. \
    (d) Returns new position after player makes a move. 

6. RandomPlayer.java - chooseMove() \
   (a) Purpose: Extends the Player class to handle move selection for the random player. \
   (b) Core Logic: \
   Fetches all valid moves via GameState.generatePossibleMoves() (stored in List<Move>).
   Returns null if no valid moves exist (prevents NPE).
   Uses Random to pick a random index from the valid moves list, then returns the corresponding Move.
   (c) Output: A randomly selected Move (containing target piece and destination) to update the game state in GameMain. 

7. Player.java - printMove() \

8. GameMain.java - main()\
   (a) gameMode() method was used to ask player to input the mode (Human Player, Random Player or AI Player).\
       To make it easy for the user to input, we define Human Player as 0, Random Player as 1 and AI Player as 2.\
       User only need to input the mode is either (0,1 or 2).\
   (b) humanName() method was used to ask user enter their username is the mode chosen is Human Player\
   (c) getLevel() method was used to prompt users to enter the level that they want (level 1-4)\
       If the levelSelected in valid, it it instantiates a GameLoader object to load the specific level file and immediately         calls printGameDetails() method to save the configuration to "moves.txt".\
   (d) main() method is where all the methods were called (gameMode(), getLevel(), chooseMove()) and display the result of           the game whether it is winning or losing.
# Extra Features
1. Level 1
2. Level 2
3. Level 3
4. Level 4

# How to compile and run
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
  5. Chaang Wai Chiu:
     - Complete HumanPlayer part
  6. Si Jun Tian:
     -Complete RandomPlayer part
# Challenges faced
  

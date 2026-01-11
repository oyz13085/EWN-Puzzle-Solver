# WIX1002 FUNDAMENTALS OF PROGRAMMING - TOPIC 6: EinStein  würfelt nicht! Puzzle Solver 
# Team Members:
    1. OOI YONG ZHE (25069059)
    2. CHEW JEE SYUEN (25066410)
    3. LIM KAI HERN (23116000)
    4. CHAANG WAI CHIU (23104771)
    5. SI JUN TIAN (25068553)
# Project Overview
Introduction\
This project requires students to develop a simulator and solver for the single-player EinStein würfelt nicht variant puzzle using Java. Originally conceptualized by Professor Ingo Althöfer, the game gets its name from Albert Einstein’s famous 1926 declaration, "I am convinced that He (God) does not play dice," a critique of the randomness inherent in quantum mechanics. While the traditional game uses dice to drive gameplay, this project implements a deterministic single-player variant. In this version, the dice rolls are replaced by fixed sequences which transform the game from a test of probability into a complex logic puzzle. The board size for this puzzle is in 10 x 10 grid where pieces move according to "King-style" mechanics. The primary objective is to maneuver the target piece to square 0 within limited number of moves which is 30 moves, navigating around static obstacles and utilizing strategic captures.

# Features Implemented
# Basic Features
1. GameLoader.java - Constructor\
   (a) Constructor is created to read data from the given filename (level1.txt, level2.txt, level3.txt and level4.txt).\
   (b) Data read from the files are stored in appropriate variables (targetPiece, initialPosition and diceSequence).\
   (c) Validation was carried out using try and catch for the selectedLevel(1-4).\
   
2. GameLoader.java - printGameDetails()\
   (a) printGameDetails() method was used to print all the game setup details into "moves.txt" file\
   (b) Inside the file, the first line will print "Human Player", "Random Player" or "AI Player" based on the mode. If it is         "Human Player", it will also print out the name of the player.\
   (b) Second line, print out the dice Sequence (30 sequences).\
   (c) Third line, print out the target piece.\
   (d) Fourth line, print out the initial position of all the 6 pieces.\
   (e) Fifth line onwards until last line, print out the position of the pieces after each move until the game ends.\
   
3. GameState.java - generatePossibleMoves()\
    (a)generatePossibleMoves()method is used to determine the possible moves for each piece.\
    (b)It works by taking the piece input and using that input to calculate the steps that are movable while avoiding space 22\
    (c)First,it detects if the pieces from dice roll are still on the board, it will calculate the closest two pieces
       in terms of bigger and smaller and outputs to user.\
    (d)Calculation is then done by separating the piece position with mathematical equations into rows and columns and 
       then its check with different conditions to ensure that the value is not out of bounds and does not interfere with the space 22.\
    (e)All of the valid positions are then stored in a arraylist call moves.\
5. GameState.java - isWinning() \
    (a)isWinning() is used to check if the winning conditions are met.\
    (b)If the current position of the pieces is at the "0" space, the system will output "You win!".\
6. HumanPlayer.java - chooseMove()
7. RandomPlayer.java - chooseMove()
8. Player.java - printMove()

9. GameMain.java - main()\
   (a) gameMode() method was used to ask player to input the mode (Human Player, Random Player or AI Player).\
       To make it easy for the user to input, we define Human Player as 0, Random Player as 1 and AI Player as 2.\
       User only need to input the mode is either (0,1 or 2).\
   (b) humanName() method was used to ask user enter their username is the mode chosen is Human Player\
   (c) getLevel() method was used to prompt users to enter the level that they want (level 1-4)\
       If the levelSelected in valid, it it instantiates a GameLoader object to load the specific level file and immediately         calls printGameDetails() method to save the configuration to "moves.txt".\
   (d) main() method is where all the methods were called (gameMode(), getLevel(), chooseMove()) and display the result of           the game whether it is winning or losing\
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
     -Done major changes to GameState to refine and reduce redundancy within classes
  3. Chew Jee Syuen:
     - Complete GameLoader part
     - Complete GameMain part
     - Write report
  4. Lim Kai Hern:
     -Complete GameState part
  5. Chaang Wai Chiu:
  6. Si Jun Tian:
# Challenges faced
  

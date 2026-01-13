import java.util.Scanner;
import java.util.Arrays;

public class GameMain {
    static int mode, selectedLevel;
    static String name;
    static GameState state;
    static GameLoader loader;
    static Scanner input = new Scanner(System.in);

    private static void gameMode() {
        System.out.println("Enter your game mode (Human = 0, Random = 1, AI = 2): ");
        mode = input.nextInt();
        switch (mode) {
            case 0:
                System.out.println("Human Player");
                name = humanName();
                break;
            case 1:
                System.out.println("Random Player");
                break;
            case 2:
                System.out.println("AI Player");
                break;
            default:
                System.out.println("Invalid Input");
                throw new IllegalArgumentException("Choose 0 to 2 only");
        }
    }

    private static String humanName() {
        System.out.println("Enter your name: ");
        return input.nextLine();
    }

    private static int getLevel() {
        System.out.println("Select a level (1-4): ");
        selectedLevel = input.nextInt();
        if (selectedLevel >= 1 && selectedLevel <= 4) {
            loader = new GameLoader(selectedLevel);
            loader.printGameDetails(mode, name);
            state = new GameState(loader.getInitialPosition(), loader.getTargetPiece());
            return selectedLevel;
        } else {
            throw new IllegalArgumentException("Choose 1 to 4 only");
        }

    }

    private static void mainGame(int mode) {
        Player player;
        switch (mode) {
            case 0:
                player = new HumanPlayer();
                break;
            case 1:
                player = new AIPlayer();
                break;
            case 2: // AI
                player = new AIPlayer();
                break;
            default:
                throw new IllegalArgumentException("Invalid input");
        }

        for (int i = 0; i < 30 && !state.isWinning(); i++) {
            int diceRoll = loader.getDiceRoll(i);
            state.updatePositions(player.chooseMove(state.generatePossiblePieces(diceRoll), state.getCurrentPositions())); 
            System.out.println("\nCurrent positions: " + Arrays.toString(state.getCurrentPositions()));
            player.printMove(state.getCurrentPositions());

        }

    }

    public static void main(String[] args) {
        gameMode();
        getLevel(); // set up the states of the level
        mainGame(mode);
        if (state.isWinning()) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("Game Over! You lose!");
        }
    }
}
// GameState position = new GameState(loader.initialPosition,
// loader.diceSequence);
// position.generatePossibleMoves();
// // choseMove();
// //Show result win or lose

// ============================================================
// TODO: Implement the main() function
// ------------------------------------------------------------
// This is the main entry point of the program.
//
// The function should perform the following tasks:
//
// 1. Prompt the user to choose a game mode:
// - Human Player
// - Random Player
// - AI Player
//
// 2. Create a player object based on the selected mode:
// - For Human Player => prompt for player name.
// - For Random Player => use default name "Random Player".
// - For AI Player => use default name "AI Player".
//
// 3. Prompt the user to select a level.
//
// 4. Call the chooseMove() function of the player
// to perform their move based on the current game state.
//
// 5. Display the result of the game
//
// ============================================================

// You may also add any other helper functions, variables,
// and constructors needed for your implementation.

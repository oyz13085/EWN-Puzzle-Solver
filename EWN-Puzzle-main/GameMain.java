import java.util.Scanner;
public class GameMain {
    static int mode;
    static String name;
    private static int gameMode() {
        System.out.println("Enter your game mode (Human = 0, Random = 1, AI = 2): ");
        Scanner input = new Scanner(System.in);
        mode = input.nextInt();
        switch (mode) {
            case 0:
                System.out.println("Human Player");
                name=humanName();
                break;
            case 1:
                System.out.println("Random Player");
                break;
            case 2:
                System.out.println("AI Player");
                break;
            default:
                System.out.println("Invalid Input");
        }
        return mode;
    }

    private static String humanName() {
        System.out.println("Enter your name: ");
        Scanner input = new Scanner(System.in);
        String humanPlayerName = input.nextLine();
        return humanPlayerName;
    }

    private static int getLevel() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select a level (1-4): ");
        int selectedlevel = input.nextInt();
        if (selectedlevel >= 1 && selectedlevel<= 4) {
            GameLoader loader = new GameLoader(selectedlevel);
            loader.printGameDetails(mode, name);
        }
        return selectedlevel;
    }

    public static void main(String[] args) {
        gameMode();
        getLevel();
    }
}
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


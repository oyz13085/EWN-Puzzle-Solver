import java.util.Scanner;


public class GameMain {

    public static void main(String[] args) {
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

        Scanner input = new Scanner(System.in);
        int mode, humanplayer, randomplayer, aiplayer;
        String humanplayername = "";

        System.out.println("Enter your game mode (Human Player = 0, Random Player = 1, AI Player = 2): ");
        mode = input.nextInt();

        if (mode == 0) {
            System.out.println("Human Player");
            input.nextLine();
            System.out.println("Enter your name:");
            humanplayername = input.nextLine();
        } else if (mode == 1) {
            System.out.println("Random Player");
        } else if (mode == 2) {
            System.out.println("AI Player");
        } else {
            System.out.println("Invalid input");
        }

        System.out.println("Select a level (1-4): ");
        int selectedlevel = input.nextInt();
        if (selectedlevel == 1) {
            GameLoader loader = new GameLoader(1);
            loader.printGameDetails(mode, humanplayername);
        } else if (selectedlevel == 2) {
            GameLoader loader = new GameLoader(2);
            loader.printGameDetails(mode, humanplayername);
        } else if (selectedlevel == 3) {
            GameLoader loader = new GameLoader(3);
            loader.printGameDetails(mode, humanplayername);
        } else if (selectedlevel == 4) {
            GameLoader loader = new GameLoader(4);
            loader.printGameDetails(mode, humanplayername);
        }
    }
}

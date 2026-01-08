import java.util.Scanner;

public class HumanPlayer extends Player {

    
    private Scanner scanner = new Scanner(System.in);

   
    
    public int chooseMove(int diceRoll, int[] currentPositions) {
        System.out.println("\nDice Roll: " + diceRoll);

        
        if (currentPositions[diceRoll] != -1) {
            System.out.println("Piece " + diceRoll + " is available.");
            System.out.println("You must move piece " + diceRoll);
            return diceRoll;
        }

       
        System.out.println("Piece " + diceRoll + " is captured/missing.");
        
        int lowerOption = -1;
        int higherOption = -1;

       
        for (int i = diceRoll - 1; i >= 1; i--) {
            if (currentPositions[i] != -1) {
                lowerOption = i;
                break; 
            }
        }

        
        for (int i = diceRoll + 1; i <= 6; i++) {
            if (currentPositions[i] != -1) {
                higherOption = i;
                break; 
            }
        }


        // CASE A: Both options exist 
        if (lowerOption != -1 && higherOption != -1) {
            System.out.println("You can move piece " + lowerOption + " OR piece " + higherOption);
            System.out.print("Enter your choice (" + lowerOption + " or " + higherOption + "): ");
            
            int choice = scanner.nextInt();
            
            // Simple validation loop to ensure they pick a valid option
            while (choice != lowerOption && choice != higherOption) {
                System.out.print("Invalid choice. Please enter " + lowerOption + " or " + higherOption + ": ");
                choice = scanner.nextInt();
            }
            return choice;
        }
        
        // CASE B: Only the smaller piece exists
        else if (lowerOption != -1) {
            System.out.println("Only smaller option available.");
            System.out.println("Forced move: You must move piece " + lowerOption);
            return lowerOption;
        }
        
        // CASE C: Only the larger piece exists
        else if (higherOption != -1) {
            System.out.println("Only larger option available.");
            System.out.println("Forced move: You must move piece " + higherOption);
            return higherOption;
        }

        // CASE D: No moves possible (Should rarely happen in normal gameplay unless game over)
        System.out.println("No valid moves available.");
        return 0;
    }
}
    // ============================================================
    // TODO: Implement chooseMove()
    // ------------------------------------------------------------
    // This method prompts the human player to choose the next move
    //
    // You may decide on the return type, parameters, and logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.
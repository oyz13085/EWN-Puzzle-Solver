import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    // Scanner object to read input from the terminal
    private Scanner scanner;

    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
    }

    
    public int[] chooseMove(List<Integer> movablePieces, int[] currentPositions) {
        
        // ---------------------------------------------------------
        // STEP 1: Choose WHICH Piece to Move
        // ---------------------------------------------------------
        int selectedPiece = -1;

        // Should not happen in normal logic, but good safety check
        if (movablePieces.isEmpty()) {
            System.out.println("No movable pieces available. Skipping turn.");
            return currentPositions;
        }

        // If there is only one valid piece, force selection (or auto-select)
        // However, to keep it interactive, we show them the only option.
        System.out.println("\n--- Your Turn ---");
        System.out.println("Movable Piece(s): " + movablePieces);

        while (true) {
            System.out.print("Select a piece to move: ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                // Validation: The input must be inside the movablePieces list
                if (movablePieces.contains(input)) {
                    selectedPiece = input;
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid choice. You can only move: " + movablePieces);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid string input to prevent infinite loop
            }
        }

    
        // STEP 2: Choose WHERE to Move
       
        List<Integer> validDestinations = GameState.generatePossibleMoves(selectedPiece, currentPositions);

        int selectedDest = -1;

        if (validDestinations.isEmpty()) {
            System.out.println("Piece " + selectedPiece + " is blocked and cannot move.");
            return currentPositions;
        }

        System.out.println("Possible destinations for Piece " + selectedPiece + ": " + validDestinations);

        // If there's only one place to go, we can arguably just move it, 
        // but let's ask the user to confirm to maintain "Human" control.
        while (true) {
            System.out.print("Select destination square: ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (validDestinations.contains(input)) {
                    selectedDest = input;
                    break;
                } else {
                    System.out.println("Invalid move. Choose from: " + validDestinations);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }

        currentPositions[selectedPiece] = selectedDest;
        
        System.out.println("Piece " + selectedPiece + " moved to " + selectedDest);
        
        return currentPositions;
    }
}
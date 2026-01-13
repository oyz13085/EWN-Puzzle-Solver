import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int[] chooseMove(List<Integer> movablePieces, int[] currentPosition) {
        
        // --- STEP 1: SELECT PIECE ---
        int movingPiece = -1;

        if (movablePieces.isEmpty()) {
            System.out.println("No moves available.");
            return currentPosition;
        }

        if (movablePieces.size() == 1) {
            movingPiece = movablePieces.get(0);
            System.out.println("You must move: " + movingPiece);
        } else {
            System.out.println("Available pieces to move: " + movablePieces);
            System.out.print("Enter piece number: ");

            while (true) {
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    if (movablePieces.contains(input)) {
                        movingPiece = input;
                        break;
                    }
                    System.out.print("Invalid piece. Choose from " + movablePieces + ": ");
                } else {
                    scanner.next(); 
                    System.out.print("Please enter a number: ");
                }
            }
        }

        // --- STEP 2: SELECT DESTINATION ---
        List<Integer> possibleMoves = GameState.generatePossibleMoves(movingPiece, currentPosition);
        int moveChosen = -1;

        if (possibleMoves.isEmpty()) {
            System.out.println("Piece " + movingPiece + " has no valid moves.");
            return currentPosition;
        }

        System.out.println("Possible destinations for piece " + movingPiece + ": " + possibleMoves);
        System.out.print("Enter destination square: ");

        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (possibleMoves.contains(input)) {
                    moveChosen = input;
                    break;
                }
                System.out.print("Invalid move. Choose from " + possibleMoves + ": ");
            } else {
                scanner.next(); 
                System.out.print("Please enter a number: ");
            }
        }

        // Step 3: Update Position
        System.out.println("Moving Piece " + movingPiece + " to " + moveChosen);
        currentPosition[movingPiece] = moveChosen;

        return currentPosition;
    }
}
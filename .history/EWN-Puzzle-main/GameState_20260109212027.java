import java.util.ArrayList;
import java.util.List;

public class GameState {

    public int[] piecePositions = new int[7];
    public int targetPiece;

    public GameState(int[] positions, int target) {
        this.piecePositions = positions;
        this.targetPiece = target;
    }

    public boolean isWinning() {
        int currentPos = piecePositions[targetPiece];
        if (currentPos == 0) {
            return true;
        }
        return false;
    }


    public List<Integer> generatePossibleMoves(int diceRoll) {
        Player currentPlayer;

// 2. Decide who is playing (maybe based on a menu choice)
        if (userChoice == HumanPlayer) {
            currentPlayer = new HumanPlayer();    // Load from Human.java
        } else if (userChoice == AIPlayer) {
            currentPlayer = new AIPlayer();       // Load from AI.java
        } else {
            currentPlayer = new RandomPlayer(); // Load from RandomPlayer.java
        }
        List<Integer> moves = new ArrayList<>();
        List<Integer> movablePieces = new ArrayList<>();

        if (piecePositions[diceRoll] != -1) {
            movablePieces.add(diceRoll);
        } else {
            for (int i = diceRoll + 1; i <= 6; i++) {
                if (piecePositions[i] != -1) {
                    movablePieces.add(i);
                    break;
                }
            }

            for (int i = diceRoll - 1; i >= 1; i--) {
                if (piecePositions[i] != -1) {
                    movablePieces.add(i);
                    break;
                }
            }
        }
        int choosedMove = currentPlayer.chooseMove();

        //Calculation of which move
        int currentPos = piecePositions[choosedMove];

        int row = currentPos / 10;
        int col = currentPos % 10;

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];


            if (newRow >= 0 && newRow <= 9 && newCol >= 0 && newCol <= 9) {
                int newPos = (newRow * 10) + newCol;

                // Rule B: Cannot enter Square 22
                if (newPos != 22) {
                    moves.add(newPos);
                }
            }
        }


        return moves;
    }

}

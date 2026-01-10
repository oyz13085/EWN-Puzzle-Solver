import java.util.ArrayList;
import java.util.List;

public class GameState {

    public int[] piecePositions = new int[7];
    public int targetPiece;

    public GameState(int[] positions, int target) {
        this.piecePositions = positions;
        this.targetPiece = target;

    }

    public int[] getCurrentPositions(){
        return piecePositions;
    }

    public void updatePositions(int[] positions){
        this.piecePositions = positions;
    }

    public boolean isWinning() {
        int currentPos = piecePositions[targetPiece];
        if (currentPos == 0) {
            return true;
        }
        return false;
    }

    public void generatePossiblePieces(int diceRoll) {
        if (piecePositions[diceRoll] != -1) {
            System.out.print("Movable pieces are " + diceRoll);
        } else {
            for (int i = diceRoll + 1; i <= 6; i++) {
                if (piecePositions[i] != -1) {
                    System.out.print("Movable pieces are " + i);
                    break;
                }
            }

            for (int i = diceRoll - 1; i >= 1; i--) {
                if (piecePositions[i] != -1) {
                    System.out.print("Movable pieces are " + i);
                    break;
                }
            }
        }
    }

    public List<Integer> generatePossibleMoves(int piece) {// piece will come from player
        List<Integer> moves = new ArrayList<>();
        int currentPos = piecePositions[piece];

        int row = currentPos / 10;
        int col = currentPos % 10;

        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow <= 9 && newCol >= 0 && newCol <= 9) {
                int newPos = (newRow * 10) + newCol;

                if (newPos != 22) {
                    moves.add(newPos);
                }
            }
        }
        return moves;
    }
}

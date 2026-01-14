import java.util.ArrayList;
import java.util.List;

public class GameState {

    public int[] piecePositions;
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
        }else if(currentPos == -1){
            return true;
        }else{
            return false;
        }
        
    }

    public List<Integer> generatePossiblePieces(int diceRoll) {
        List<Integer> answer = new ArrayList<>();
        if (piecePositions[diceRoll] != -1) {
            answer.add(diceRoll);
        } else {
            for (int i = diceRoll + 1; i <= 6; i++) {
                if (piecePositions[i] != -1) {  
                    answer.add(i);
                    break;
                }
            }

            for (int i = diceRoll - 1; i >= 1; i--) {
                if (piecePositions[i] != -1) {
                    answer.add(i);
                    break;
                }
            }
        }
        return answer;
    }

    public static List<Integer> generatePossibleMoves(int piece, int[] piecePositions) {// piece will come from player
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

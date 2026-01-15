import java.util.Random;
import java.util.List;

public class RandomPlayer extends Player{
    private final Random random = new Random();

    /**
     * randomly select a legal piece and move position
     * @param possiblePieces list of movable pieces
     * @param currentPosition current position array of the piece
     * @return new position array of the piece after moving
     */

    @Override
    public int[] chooseMove(List<Integer> possiblePieces,int[] currentPosition){
        
        if(currentPosition == null){
            System.out.println("[RandomPlayer]Current position is null,skip this turn.");
            return new int[7];
        }
        int[] newPositions = currentPosition.clone();

        if(possiblePieces == null || possiblePieces.isEmpty()){
            System.out.println("[RandomPlayer]No legal movable pieces,skip this turn.");
            return newPositions;
        }
        int selectedPieceIndex = random.nextInt(possiblePieces.size());
        int selectedPiece = possiblePieces.get(selectedPieceIndex);

        List<Integer> possibleMoves = GameState.generatePossibleMoves(selectedPiece,newPositions);

        if (possibleMoves == null || possibleMoves.isEmpty()){
            System.out.println("[RandomPlayer]Piece"+selectedPiece+"No legal moves available,skip this turn.");
            return newPositions;
        }
        int selectedDestIndex = random.nextInt(possibleMoves.size());
        int selectedDest = possibleMoves.get(selectedDestIndex);

        for(int i = 1;i < 7;i++){
            if (i != selectedPiece && newPositions[i] == selectedDest){
                newPositions[i] = -1;
                System.out.println("[RandomPlayer]Captured piece"+i+"at position"+selectedDest);
            }
        }

        newPositions[selectedPiece] = selectedDest;

        System.out.println("[RandomPlayer]Execution of move successful:piece"+selectedPiece
            +"from position"+currentPosition[selectedPiece]
            +"to position"+selectedDest);
        
        return newPositions;
    }
}
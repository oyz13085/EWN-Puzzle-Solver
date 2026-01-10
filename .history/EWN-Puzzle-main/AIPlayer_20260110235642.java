import java.util.List;

public class AIPlayer extends Player {
    // ============================================================
    // TODO: Implement chooseMove()
    // ------------------------------------------------------------
    // This method defines how the AI selects its next move.
    //
    // You are encouraged to implement any suitable AI or algorithmic
    // approach to decide the move, including rule-based strategy,
    // dynamic programming, greedy algorithm, and search-based algorithm
    //
    // Hardcoded solution (manually entering the correct moves)
    // is strictly not allowed.
    //
    // You may decide on the return type, parameters, and logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.

    public AIPlayer(){
        
    }

    public int[] chooseMove(List<Integer> diceRoll, int[] currentPosition){
        int movingPiece;
        if(diceRoll.size() == 1){
            movingPiece = diceRoll.get(0);
        }else{
            movingPiece = diceRoll.get((int)(Math.random() * 2));
        }

        System.out.println("Your current moving piece: " + movingPiece);

        List<Integer> possibleMoves = GameState.generatePossibleMoves(movingPiece, currentPosition);

        System.out.println("Your current possible moves: " + possibleMoves);

        int moveChosen = possibleMoves.get((int)Math.random() * possibleMoves.size());

        System.out.println("You choose Move " + moveChosen);
        
        currentPosition[movingPiece] = moveChosen;

        return currentPosition;
    }

}

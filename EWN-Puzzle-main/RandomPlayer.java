import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player{
    private Random random;

    public RandomPlayer(String name){
        super(name);
        random = new Random();
    }

    @Override
    public Move chooseMove(gameState gamestate){

        List<Move> possibleMoves = gameState.generatePossibleMoves();

        if(possibleMoves.isEmpty()){
            return null;
        }

        int index = random.nextInt(possibleMoves.size());
        return possibleMoves.get(index);
    }
}
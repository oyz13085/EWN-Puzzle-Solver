import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class Player {
    // ============================================================
    // TODO: Implement printMove()
    // ------------------------------------------------------------
    // This method print the chosen move of the player into the
    // "moves.txt" file
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================

    public void printMove(int[] position){
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream("moves.txt",true));
            for(int i=1;i<8;i++){
                pw.print(position[i] + " ");
            }
            pw.println();
            pw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // ============================================================
    // TODO: Implement abstract function - chooseMove()
    // ------------------------------------------------------------
    // This is an abstract method that defines how the player selects
    // a move. You should implement the logic in the subclasses.
    //
    // You may decide on the return type, and parameters.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.
    public abstract int[] chooseMove(List<Integer> diceRoll, int[] currentPositions);
}

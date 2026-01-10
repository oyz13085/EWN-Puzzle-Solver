import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class GameLoader {

    // ============================================================
    // TODO: Implement constructor
    // ------------------------------------------------------------
    // This method read data from the given filename and stores
    // them in appropriate variables
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================

    int targetPiece;
    int[] initialPosition = new int[6];
    int[] diceSequence = new int[30];
    int mode;
    String humanplayername;

    public GameLoader(int selectedlevel) {
        if (selectedlevel == 1) {
            try {
                Scanner input = new Scanner(new FileInputStream("level1.txt"));
                while (input.hasNextLine()) {
                    targetPiece = input.nextInt();
                    for (int j = 0; j < initialPosition.length; j++) {
                        initialPosition[j] = input.nextInt();
                    }
                    for (int k = 0; k < diceSequence.length; k++) {
                        diceSequence[k] = input.nextInt();
                    }
                }
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }else if(selectedlevel==2){
            try {
                Scanner input = new Scanner(new FileInputStream("level2.txt"));
                while (input.hasNextLine()) {
                    targetPiece = input.nextInt();
                    for (int j = 0; j < initialPosition.length; j++) {
                        initialPosition[j] = input.nextInt();
                    }
                    for (int k = 0; k < diceSequence.length; k++) {
                        diceSequence[k] = input.nextInt();
                    }
                }
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }else if(selectedlevel==3){
            try {
                Scanner input = new Scanner(new FileInputStream("level3.txt"));
                while (input.hasNextLine()) {
                    targetPiece = input.nextInt();
                    for (int j = 0; j < initialPosition.length; j++) {
                        initialPosition[j] = input.nextInt();
                    }
                    for (int k = 0; k < diceSequence.length; k++) {
                        diceSequence[k] = input.nextInt();
                    }
                }
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }else if(selectedlevel==4){
            try {
                Scanner input = new Scanner(new FileInputStream("level4.txt"));
                while (input.hasNextLine()) {
                    targetPiece = input.nextInt();
                    for (int j = 0; j < initialPosition.length; j++) {
                        initialPosition[j] = input.nextInt();
                    }
                    for (int k = 0; k < diceSequence.length; k++) {
                        diceSequence[k] = input.nextInt();
                    }
                }
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        }else{
            System.out.println("Invalid level");
        }
    }


    // ============================================================
    // TODO: Implement printGameDetails()
    // ------------------------------------------------------------
    // This method print the game setup details into "moves.txt"
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.

    public void printGameDetails(int selectedmode, String humanplayername) {
        this.mode = selectedmode;
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("moves.txt"));
            if (selectedmode == 0) {
                outputStream.println("Human Player");
                outputStream.println("Name: " + humanplayername);
            } else if (selectedmode == 1) {
                outputStream.println("Random Player");
            } else if (selectedmode == 2) {
                outputStream.println("AI Player");
            } else {
                outputStream.println("Invalid mode");
            }

            for (int i = 0; i < diceSequence.length; i++) {
                outputStream.print(diceSequence[i] + " ");
            }
            outputStream.println();
            outputStream.println(targetPiece);

            for (int j = 0; j < initialPosition.length; j++) {
                outputStream.print(initialPosition[j] + " ");
            }
            outputStream.println();
            outputStream.close();

        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}

import java.io.*;
import java.util.Scanner;
public class GameLoader {
    private int targetPiece;
    private int[] initialPosition = new int[6];
    private int[] diceSequence = new int[30];
    private int mode;

    public GameLoader(int selectedLevel) {
        if (selectedLevel < 1 || selectedLevel > 4) {
            System.out.println("Invalid level");
        } else {
            String fileName = "TestCases//level" + selectedLevel + ".txt";

            try (Scanner input = new Scanner(new FileInputStream(fileName))) {
                if (input.hasNextInt()) {
                    targetPiece = input.nextInt();

                    for (int j = 0; j < initialPosition.length; j++) {
                        initialPosition[j] = input.nextInt();
                    }

                    for (int k = 0; k < diceSequence.length; k++) {
                        diceSequence[k] = input.nextInt();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + fileName);
            }
        }
    }

    public void printGameDetails(int selectedMode, String humanPlayerName) {
        this.mode = selectedMode;
        try {
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("moves.txt"));
            if (selectedMode == 0) {
                outputStream.println("Human Player");
                outputStream.println("Name: " + humanPlayerName);
            } else if (selectedMode == 1) {
                outputStream.println("Random Player");
            } else if (selectedMode == 2) {
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


    // ============================================================
    // TODO: Implement constructor
    // ------------------------------------------------------------
    // This method read data from the given filename and stores
    // them in appropriate variables
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================

    // ============================================================
    // TODO: Implement printGameDetails()
    // ------------------------------------------------------------
    // This method print the game setup details into "moves.txt"
    //
    // You may decide on the return type, parameters, and internal logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.

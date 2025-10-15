package lastpencil;

import java.util.Random;

public class LastPencilGame {
    private static final int MIN_PENCILS = 1;
    private static final int MAX_PENCILS = 999;
    public static final String playerOne = "John";
    public static final String playerTwo = "Jack";
    private final InputProvider inputProvider;

    LastPencilGame(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void play() {
        int pencilsCount = promptForPencilCount();
        String firstPlayer = promptForFirstPlayer();
        startGameLoop(pencilsCount, firstPlayer);
    }

    private void startGameLoop(int pencilsCount, String currentPlayer) {
        while (pencilsCount >= MIN_PENCILS ) {
            printPencils(pencilsCount);
            System.out.println(currentPlayer + "'s turn:");
            int numPencilsToUse = readValidPencilsToUseInput(pencilsCount);

            pencilsCount -= numPencilsToUse;
            currentPlayer = switchPlayer(currentPlayer);
        }
    }

    private int readValidPencilsToUseInput(int pencilsCount) {
        while (true) {
            int numPencilsToUse = 0;
            try {
                numPencilsToUse = Integer.parseInt(inputProvider.readLine());
                if (numPencilsToUse < 1 || numPencilsToUse > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                return numPencilsToUse;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format! Please enter digits only.");
            }
        }
    }

    int promptForPencilCount() {
        while (true) {
            System.out.println("How many pencils would you like to use:");
            try {
                int pencils = Integer.parseInt(inputProvider.readLine());
                if (pencils == 0) {
                   System.out.println("The number of pencils should be positive");
                    continue;
                } else if (pencils < 0) {
                    throw new NumberFormatException();
                }
                return pencils;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
    }

    String promptForFirstPlayer() {
        while (true) {
            System.out.printf("Who will be the first (%s, %s):%n", playerOne, playerTwo );
            if (inputProvider.hasNextLine()) {
                String firstPlayer = inputProvider.readLine();
                if (firstPlayer.equalsIgnoreCase(playerOne)
                        || firstPlayer.equalsIgnoreCase(playerTwo)) {
                    return firstPlayer;
                } else  {
                    System.out.println("Choose between 'John' and 'Jack'");
                }
            }
        }
    }

    private String switchPlayer(String currentPlayer) {
        return currentPlayer.equalsIgnoreCase("John") ? "Jack" : "John";
    }

    private int generateRandomPencilCount () {
        return new Random().nextInt(MAX_PENCILS - MIN_PENCILS + 1) + MIN_PENCILS;
    }

    private static void printPencils(int count) {
        System.out.println("|".repeat(count));
    }
}

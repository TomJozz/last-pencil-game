package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new PencilGame(scanner).play();
        scanner.close();
    }
}

class PencilGame {
    private static final int MIN_PENCILS = 1;
    private static final int MAX_PENCILS = 9999;
    public static final String playerOne = "John";
    public static final String playerTwo = "Jack";
    private final Scanner scanner;

    PencilGame(Scanner scanner) {
        this.scanner = scanner;
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
                numPencilsToUse = Integer.parseInt(scanner.nextLine());
                if (numPencilsToUse > pencilsCount || numPencilsToUse < MIN_PENCILS) {
                    if (pencilsCount == 1) {
                        System.out.println("\uD83D\uDE44 There is only one left");
                    } else {
                        System.out.printf("You can't use less than %d or more than %d pencils!\n", MIN_PENCILS, pencilsCount);
                    }
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format! Please enter digits only.");
            }
            return numPencilsToUse;
        }
    }

    private int promptForPencilCount() {
        while (true) {
            System.out.println("How many pencils would you like to use:");
            try {
                int pencils = Integer.parseInt(scanner.nextLine());
                if (pencils < MIN_PENCILS || pencils > MAX_PENCILS) {
                    System.out.printf("Please enter a number between %d and %d.%n", MIN_PENCILS, MAX_PENCILS);
                    continue;
                }
                return pencils;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format! Please enter digits only.");
            }
        }
    }

    private String promptForFirstPlayer() {
        while (true) {
            System.out.printf("Who will be the first (%s, %s):%n", playerOne, playerTwo );
            if (scanner.hasNextLine()) {
                String firstPlayer = scanner.nextLine();
                if (firstPlayer.equalsIgnoreCase(playerOne)
                        || firstPlayer.equalsIgnoreCase(playerTwo)) {
                    return firstPlayer;
                }
            }
        }
    }

    private String switchPlayer(String currentPlayer) {
        return currentPlayer.equalsIgnoreCase("John") ? "Jack" : "John";
    }

    private static int generateRandomPencilCount () {
        return new Random().nextInt(MAX_PENCILS - MIN_PENCILS + 1) + MIN_PENCILS;
    }

    private static void printPencils(int count) {
        StringBuilder pencils = new StringBuilder();
        pencils.append("|".repeat(count));
        System.out.println(pencils);
    }
}

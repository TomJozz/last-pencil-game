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
        int pencils = promptForPencilCount();
        String firstPlayer = promptForFirstPlayer();
        printPencils(pencils);
        System.out.println(firstPlayer + " is going first!");
    }

    private int promptForPencilCount() {
        while (true) {
            System.out.println("How many pencils would you like to use:");
            try {
                int pencils = Integer.parseInt(scanner.nextLine());
                if (pencils < MIN_PENCILS || pencils > MAX_PENCILS) {
                    throw new Exception("Pencils must be between " + MIN_PENCILS +" and " + MAX_PENCILS +"!");
                }
                return pencils;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
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

    private static void nextTurn() {
        System.out.println("Your Turn!");
    }

    private static int generateRandomPencilCount () {
        new Random().nextInt(3,4);
        return new Random().nextInt(MAX_PENCILS - MIN_PENCILS + 1) + MIN_PENCILS;
    }

    private static void printPencils(int count) {
        StringBuilder pencils = new StringBuilder();
        pencils.append("|".repeat(count));
        System.out.println(pencils);
    }
}

package lastpencil;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        PencilGame.play();
    }
}

class PencilGame {
    private static final int MIN_PENCILS = 3;
    private static final int MAX_PENCILS = 8;

    public static void play() {
        int pencils = generateRandomPencilCount();
        printPencils(pencils);
        nextTurn();
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
        pencils.append("|".repeat(Math.max(0, count)));
        System.out.println(pencils);
    }
}

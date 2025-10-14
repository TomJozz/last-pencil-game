package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputProvider inputProvider = new ConsoleInputProvider(new Scanner(System.in));
        new LastPencilGame(inputProvider).play();

        //TODO: sc.close();
    }
}


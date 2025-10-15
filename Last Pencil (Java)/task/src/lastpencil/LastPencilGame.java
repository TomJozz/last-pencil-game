package lastpencil;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class LastPencilGame {
    private static final int MIN_PENCILS = 1;
    private static final int MAX_PENCILS = 999;
    private final InputProvider inputProvider;

    LastPencilGame(InputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    public void play() {
        int pencilsCount = promptForPencilCount();
        Player currentPlayer = Player.from(promptForFirstPlayer());
        startGameLoop(pencilsCount, currentPlayer);
    }

    private void startGameLoop(int pencilsCount, Player currentPlayer) {
        while (pencilsCount >= MIN_PENCILS ) {
            printPencils(pencilsCount);
            System.out.println(currentPlayer.getName() + "'s turn:");
            int numPencilsToTake = promptForPencilsToRemove(pencilsCount);

            pencilsCount -= numPencilsToTake;
            currentPlayer = currentPlayer.switchPlayer();
            if (pencilsCount == 0) {
                System.out.println(currentPlayer.getName() + " won!");
            }
        }
    }

    public <T> T readValidatedInput(
            String prompt,
            Function<String, T> parser,
            Function<T, Optional<String>> validator,
            String formatErrorMessage
    ) {
        while (true) {
            System.out.println(prompt);
            String line = inputProvider.readLine();
            try {
                T value = parser.apply(line);
                Optional<String> error = validator.apply(value);
                if (error.isEmpty()) {
                    return value;
                } else {
                    System.out.println(error.get());
                }
            } catch (Exception e) {
                System.out.println(formatErrorMessage);
            }
        }
    }

    Function<Integer, Optional<String>> pencilsToTakeValidator(int remaining) {
        return value -> {
            if (value < 1 || value > 3) return Optional.of("Possible values: '1', '2' or '3'");
            if (value > remaining) return Optional.of("Too many pencils were taken");
            return Optional.empty();
        };
    }

    int promptForPencilsToRemove(int remaining) {
        return readValidatedInput(
                String.format("%d pencils left. How many will you take?", remaining),
                Integer::parseInt,
                pencilsToTakeValidator(remaining),
                "The input should be a valid number."
        );
    }

    Function<Integer, Optional<String>> pencilValidator = value -> {
        if (value < 0) return Optional.of("The number of pencils should be numeric");
        if (value == 0) return Optional.of("The number of pencils should be positive");
        return Optional.empty();
    };

    int promptForPencilCount() {
        return readValidatedInput(
                "How many pencils would you like to use:",
                Integer::parseInt,
                pencilValidator,
                "The number of pencils should be numeric"
        );
    }

    Function<String, Optional<String>> playerNameValidator = name -> {
        if (!name.equals(Player.JOHN.getName()) && !name.equals(Player.JACK.getName())) {
            return Optional.of(String.format("Choose between '%s' and '%s'",  Player.JOHN.getName(), Player.JACK.getName()));
        }
        return Optional.empty();
    };

    String promptForFirstPlayer() {
        return readValidatedInput(
                String.format("Who will be the first (%s, %s):", Player.JOHN.getName(),  Player.JACK.getName()),
                Function.identity(),
                playerNameValidator,
                "Invalid name format" //TODO RegexNameFormatCheck
        );
    }

    private int generateRandomPencilCount () {
        return new Random().nextInt(MAX_PENCILS - MIN_PENCILS + 1) + MIN_PENCILS;
    }

    private static void printPencils(int count) {
        System.out.println("|".repeat(count));
    }
}
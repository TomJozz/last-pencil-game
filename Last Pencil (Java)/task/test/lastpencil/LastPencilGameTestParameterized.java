package lastpencil;

import org.junit.jupiter.api.Timeout;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LastPencilGameTestParameterized {

    private String runGameWithMockInput(MockInputProvider mockInput) {
        // Limit output to avoid memory overflow
        ByteArrayOutputStream outContent = new ByteArrayOutputStream(8192); // 8 KB cap
        PrintStream originalOut = System.out;
        PrintStream testOut = new PrintStream(outContent);
        System.setOut(testOut);

        // Run game with a timeout thread to prevent infinite loop
        LastPencilGame game = new LastPencilGame(mockInput);
        Thread gameThread = new Thread(game::play);
        gameThread.start();

        try {
            gameThread.join(3000); // wait max 3 seconds
            if (gameThread.isAlive()) {
                gameThread.interrupt(); // kill runaway loop
                throw new RuntimeException("Game loop did not terminate");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Test interrupted", e);
        }

        testOut.close();
        try {
            outContent.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.setOut(originalOut);
        return outContent.toString();
    }

    private static Stream<Arguments> invalidInputScenarios() {
        return Stream.of(
                Arguments.of("Non-numeric pencil count",
                        new String[]{"abc", "5", "John", "1", "1", "3"},
                        "The number of pencils should be numeric"),

                Arguments.of("Zero pencil count",
                        new String[]{"0", "5", "Jack", "2", "1", "2"},
                        "The number of pencils should be positive"),

                Arguments.of("Negative pencil count",
                        new String[]{"-3", "5", "John", "3", "2"},
                        "The number of pencils should be numeric"),

                Arguments.of("Invalid first player",
                        new String[]{"5", "Alice", "Jack", "3", "2"},
                        "Choose between 'John' and 'Jack'"),

                Arguments.of("Invalid pencil removal amount",
                        new String[]{"5", "John", "4", "2", "1", "2"},
                        "Possible values: '1', '2' or '3'"),

                Arguments.of("Too many pencils taken",
                        new String[]{"2", "Jack", "3", "2"},
                        "Too many pencils were taken")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidInputScenarios")
    @Timeout(5)
    void testInvalidInputs(String description, String[] inputs, String expectedMessage) {
        MockInputProvider mock = new MockInputProvider();
        for (String input : inputs) {
            mock.addInput(input);
        }

        String output = runGameWithMockInput(mock);
        assertTrue(output.contains(expectedMessage), "Failed: " + description);
    }

}

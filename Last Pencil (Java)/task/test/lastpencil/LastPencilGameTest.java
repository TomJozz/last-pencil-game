package lastpencil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LastPencilGameTest {

    private String runGameWithMockInput(MockInputProvider mockInput) {
        // Limit output to avoid memory overflow
        ByteArrayOutputStream outContent = new ByteArrayOutputStream(8192); // 8 KB cap
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

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

        System.setOut(originalOut);
        return outContent.toString();
    }

    @Timeout(5)
    @Test
    void testNonNumericPencilCount() {
        MockInputProvider mock = new MockInputProvider();
        mock.addInput("abc");  // invalid
        mock.addInput("5");    // valid
        mock.addInput("John"); // valid
        mock.addInput("1");    // valid move
        mock.addInput("1");    // valid move
        mock.addInput("3");    // valid move

        String output = runGameWithMockInput(mock);
        assertTrue(output.contains("The number of pencils should be numeric"));
    }

    @Timeout(5)
    @Test
    void testZeroPencilCount() {
        MockInputProvider mock = new MockInputProvider();
        mock.addInput("0");     // invalid
        mock.addInput("5");     // valid
        mock.addInput("Jack");  // valid
        mock.addInput("2");     // valid move
        mock.addInput("1");     // valid move
        mock.addInput("2");     // valid move

        String output = runGameWithMockInput(mock);
        assertTrue(output.contains("The number of pencils should be positive"));
    }

    @Timeout(5)
    @Test
    void testNegativePencilCount() {
        MockInputProvider mock = new MockInputProvider();
        mock.addInput("-3");    // invalid
        mock.addInput("5");     // valid
        mock.addInput("John");  // valid
        mock.addInput("3");     // valid move
        mock.addInput("2");     // valid move

        String output = runGameWithMockInput(mock);
        assertTrue(output.contains("The number of pencils should be numeric"));
    }

    @Timeout(5)
    @Test
    void testInvalidFirstPlayer() {
        MockInputProvider mock = new MockInputProvider();
        mock.addInput("5");     // valid
        mock.addInput("Alice"); // invalid name
        mock.addInput("Jack");  // valid name
        mock.addInput("3");     // valid move
        mock.addInput("2");     // valid move

        String output = runGameWithMockInput(mock);
        assertTrue(output.contains("Choose between 'John' and 'Jack'"));
    }

    @Timeout(5)
    @Test
    void testInvalidPencilRemovalAmount() {
        MockInputProvider mock = new MockInputProvider();
        mock.addInput("5"); // valid
        mock.addInput("John"); // valid
        mock.addInput("4"); // invalid
        mock.addInput("2"); // valid
        mock.addInput("1"); // valid
        mock.addInput("2"); // valid

        String output = runGameWithMockInput(mock);
        assertTrue(output.contains("Possible values: '1', '2' or '3'"));
    }

    @Timeout(5)
    @Test
    void testValidInputFlow() {
        MockInputProvider mockInput = new MockInputProvider();
        mockInput.addInput("5");     // pencil count
        mockInput.addInput("John");  // first player

        LastPencilGame game = new LastPencilGame(mockInput);

        int pencils = game.promptForPencilCount();
        String player = game.promptForFirstPlayer();

        assertEquals(5, pencils);
        assertEquals("John", player);
    }

    @Timeout(5)
    @Test
    void testInvalidThenValidInputs() {
        MockInputProvider mockInput = new MockInputProvider();
        mockInput.addInput("0");     // invalid
        mockInput.addInput("-3");    // invalid
        mockInput.addInput("abc");   // invalid
        mockInput.addInput("7");     // valid
        mockInput.addInput("Jack");  // valid

        LastPencilGame game = new LastPencilGame(mockInput);

        int pencils = game.promptForPencilCount();
        String player = game.promptForFirstPlayer();

        assertEquals(7, pencils);
        assertEquals("Jack", player);
    }
}

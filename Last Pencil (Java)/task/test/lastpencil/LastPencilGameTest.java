package lastpencil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LastPencilGameTest {

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

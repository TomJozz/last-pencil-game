package lastpencil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LastPencilGameTestTwo {








//
//    @Test
//    void testInvalidFirstPlayer() {
//        MockInputProvider mock = new MockInputProvider();
//        mock.addInput("5");
//        mock.addInput("Alice");
//        mock.addInput("Jack");
//
//        String output = runGameWithMockInput(mock);
//        assertTrue(output.contains("Choose between 'John' and 'Jack'"));
//    }
//
//    @Timeout(5)
//    @Test
//    void testInvalidPencilRemovalAmount() {
//        MockInputProvider mock = new MockInputProvider();
//        mock.addInput("5");
//        mock.addInput("John");
//        mock.addInput("4"); // invalid
//        mock.addInput("2"); // valid
//        mock.addInput("1"); // valid
//        mock.addInput("2"); // valid
//
//        String output = runGameWithMockInput(mock);
//        assertTrue(output.contains("Possible values: '1', '2' or '3'"));
//    }
//
//    @Timeout(5)
//    @Test
//    void testTooManyPencilsTaken() {
//        MockInputProvider mock = new MockInputProvider();
//        mock.addInput("2");
//        mock.addInput("Jack");
//        mock.addInput("3"); // invalid
//        mock.addInput("2"); // valid
//
//        String output = runGameWithMockInput(mock);
//        assertTrue(output.contains("Too many pencils were taken"));
//    }
//
//    @Timeout(5)
//    @Test
//    void testWinnerAnnouncement() {
//        MockInputProvider mock = new MockInputProvider();
//        mock.addInput("3");
//        mock.addInput("John");
//        mock.addInput("1"); // John
//        mock.addInput("1"); // Jack
//        mock.addInput("1"); // John (last pencil)
//
//        String output = runGameWithMockInput(mock);
//        assertTrue(output.contains("Jack won!"));
//    }
}

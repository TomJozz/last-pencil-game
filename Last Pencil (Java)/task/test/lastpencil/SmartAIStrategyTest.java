package lastpencil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartAIStrategyTest {

    private final SmartAIPlayerStrategy ai = new SmartAIPlayerStrategy();

    @Test
    void testSmartMoveWhenRemainingIsMultipleOf4() {
        assertEquals(3, ai.chooseMove(4));
        assertEquals(3, ai.chooseMove(8));
    }

    @Test
    void testSmartMoveWhenRemainingIsNotMultipleOf4() {
        assertEquals(2, ai.chooseMove(7)); // 7 % 4 = 3
        assertEquals(1, ai.chooseMove(6)); // 6 % 4 = 2

        int actual = ai.chooseMove(5);
        assertTrue(actual >= 1 && actual <= 3, "Move should be between 1 and 3, but was " + actual);
    }

    @Test
    void testSmartMoveWhenRemainingIsLessThan4() {
        assertEquals(2, ai.chooseMove(3));
        assertEquals(1, ai.chooseMove(2));
        assertEquals(1, ai.chooseMove(1));
    }
}

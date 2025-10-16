package lastpencil;

import java.util.Random;

public class SmartAIPlayerStrategy implements PlayerStrategy {
    private final Random random = new Random();

    @Override
    public int chooseMove(int remainingPencils) {
        if (remainingPencils == 1)
            return 1;

        int optimalMove = remainingPencils % 4;
        int[] moves = {3, random.nextInt(3) + 1, 1, 2};
        return moves[optimalMove];
    }
}

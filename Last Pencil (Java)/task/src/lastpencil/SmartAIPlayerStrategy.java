package lastpencil;

import java.util.Random;

public class SmartAIPlayerStrategy implements PlayerStrategy {
    private final Random random = new Random();

    @Override
    public int chooseMove(int remainingPencils) {
        int optimalMove = remainingPencils % 4;
        return (optimalMove == 0) ? 3
                : ((optimalMove == 3) ? 2
                : ((optimalMove == 2) ? 1
                : ((optimalMove == 1) ? random.nextInt(1, 4)
                : 1)));
    }
}

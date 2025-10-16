package lastpencil;

public class HumanPlayerStrategy implements PlayerStrategy {
    private final LastPencilGame game;
    private final InputProvider inputProvider;

    public HumanPlayerStrategy(LastPencilGame game, InputProvider inputProvider) {
        this.game = game;
        this.inputProvider = inputProvider;
    }

    @Override
    public int chooseMove(int remainingPencils) {
        return game.promptForPencilsToRemove(remainingPencils);
    }
}



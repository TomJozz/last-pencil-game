package lastpencil;

import java.util.LinkedList;
import java.util.Queue;

public class MockInputProvider implements InputProvider {
    private final Queue<String> inputs = new LinkedList<>();

    public void addInput(String input) {
        inputs.add(input);
    }

    @Override
    public String readLine() {
        return inputs.poll();
    }

    @Override
    public boolean hasNextLine() {
        return true;
    }
}
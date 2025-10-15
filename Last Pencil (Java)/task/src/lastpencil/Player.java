package lastpencil;

import java.util.Arrays;

public enum Player {
    JOHN("John"),
    JACK("Jack");

    private final String name;
    Player(String name) { this.name = name; }
    public String getName() { return name; }

    public static boolean isValid(String input) {
        return Arrays.stream(values()).anyMatch(p -> p.name.equals(input));
    }

    public static Player from(String input) {
        return Arrays.stream(values())
                .filter(p -> p.name.equals(input))
                .findFirst()
                .orElseThrow();
    }

    public Player switchPlayer() {
        return this == JOHN ? JACK : JOHN;
    }
}

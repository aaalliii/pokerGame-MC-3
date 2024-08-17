package pokerSmth;

public enum Suit {
    SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");
    public final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static Suit fromString(String suit) {
        return switch (suit.toLowerCase()) {
            case "h" -> HEARTS;
            case "d" -> DIAMONDS;
            case "c" -> CLUBS;
            case "s" -> SPADES;
            default -> throw new IllegalArgumentException("Invalid suit: " + suit);
        };
    }
}


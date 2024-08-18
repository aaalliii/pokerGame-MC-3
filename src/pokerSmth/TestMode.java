package pokerSmth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TestMode {
    private static final Set<Card> uniqueCards = new HashSet<>();
    private static final Set<String> VALID_SUITS = Set.of("s", "h", "d", "c");
    private static final Set<String> VALID_VALUES = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

    public static void testMode(Scanner scanner) {
        uniqueCards.clear();
        List<Card> tableCards = new ArrayList<>();
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();

        System.out.println("#  Write like in this example\n" +
                "#\n" +
                "#  table cards?\n" +
                "#  1 d\n" +
                "#  10 h\n" +
                "#  Q h\n" +
                "#  5 d\n" +
                "#  2 s\n" +
                "#\n" +
                "#  p1 cards?\n" +
                "#  8 d\n" +
                "#  8 c\n" +
                "#\n" +
                "#  p2 cards?\n" +
                "#  4 h\n" +
                "#  5 h\n");

        System.out.println("table cards?");
        for (int i = 0; i < 5; i++) {
            tableCards.add(getValidCard(scanner));
        }

        System.out.println("p1 cards?");
        for (int i = 0; i < 2; i++) {
            player1Cards.add(getValidCard(scanner));
        }

        System.out.println("p2 cards?");
        for (int i = 0; i < 2; i++) {
            player2Cards.add(getValidCard(scanner));
        }

        Combination player1Combination = CombinationResolver.resolve(tableCards, player1Cards);
        Combination player2Combination = CombinationResolver.resolve(tableCards, player2Cards);

        System.out.println("p1 - " + player1Combination);
        System.out.println("p2 - " + player2Combination);

        int comparisonResult = CombinationResolver.compareHands(
                player1Cards, player2Cards, player1Combination, player2Combination);

        if (comparisonResult > 0) {
            System.out.println("\np1");
        } else if (comparisonResult < 0) {
            System.out.println("\np2");
        } else {
            System.out.println("\ntie");
        }
    }

    private static Card getValidCard(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                Card card = parseCard(input);

                if (uniqueCards.contains(card)) {
                    System.out.println("duplicate card at \n" + card);
                    continue;
                }

                uniqueCards.add(card);
                return card;
            } catch (IllegalArgumentException e) {
                System.out.println("incorrect input at \n" + e);
            }
        }
    }

    private static Card parseCard(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("invalid format at \n" + input);
        }
        String cardName = parts[0];
        String cardSuit = parts[1];

        if (!VALID_VALUES.contains(cardName)) {
            throw new IllegalArgumentException("invalid name at \n" + cardName);
        }

        if (!VALID_SUITS.contains(cardSuit)) {
            throw new IllegalArgumentException("invalid suit at \n" + cardSuit);
        }

        Suit suit = Suit.fromString(cardSuit);
        return new Card(cardName, suit);
    }
}

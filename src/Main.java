import pokerSmth.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Set<String> YES_RESPONSES = new HashSet<>();
    private static final Set<String> NO_RESPONSES = new HashSet<>();

    static {
        YES_RESPONSES.add("y");
        YES_RESPONSES.add("yes");
        YES_RESPONSES.add("ye");
        YES_RESPONSES.add("yeah");
        YES_RESPONSES.add("yep");
        YES_RESPONSES.add("yup");
        YES_RESPONSES.add("Y");

        NO_RESPONSES.add("N");
        NO_RESPONSES.add("n");
        NO_RESPONSES.add("no");
        NO_RESPONSES.add("na");
        NO_RESPONSES.add("nah");
        NO_RESPONSES.add("not");
        NO_RESPONSES.add("nuh-uh");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Do you want to play Poker? (Y/N)");
            String response = scanner.nextLine().trim().toLowerCase();

            if (YES_RESPONSES.contains(response)) {
                playGame(scanner);
            } else if (NO_RESPONSES.contains(response)) {
                System.out.println("End");
                break;
            } else if (response.equals("test")) {
                testMode(scanner);
            } else {
                System.out.println("Invalid input. Please enter Y/N or test.");
            }
        }

        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> tableCards = new ArrayList<>();
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            tableCards.add(deck.getCard());
        }
        player1Cards.add(deck.getCard());
        player1Cards.add(deck.getCard());
        player2Cards.add(deck.getCard());
        player2Cards.add(deck.getCard());

        System.out.println("Table cards are:");
        printCards(tableCards);

        System.out.println("Player 1 cards are:");
        printCards(player1Cards);

        System.out.println("Player 2 cards are:");
        printCards(player2Cards);

        Combination player1Combination = CombinationResolver.resolve(tableCards, player1Cards);
        Combination player2Combination = CombinationResolver.resolve(tableCards, player2Cards);

        System.out.println("Player 1 best combination is " + player1Combination);
        System.out.println("Player 2 best combination is " + player2Combination);

        int comparisonResult = CombinationResolver.compareHands(
                player1Cards, player2Cards, player1Combination, player2Combination);

        if (comparisonResult > 0) {
            System.out.println("\nPlayer 1 won");
        } else if (comparisonResult < 0) {
            System.out.println("\nPlayer 2 won");
        } else {
            System.out.println("\nIt is a tie");
        }

        System.out.println("\nDo you want to play again? (Y/N)");
        String playAgainResponse = scanner.nextLine().trim().toLowerCase();
        if (NO_RESPONSES.contains(playAgainResponse)) {
            System.out.println("End");
            System.exit(0);
        }
    }

    private static void testMode(Scanner scanner) {
        List<Card> tableCards = new ArrayList<>();
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();

        System.out.println("#  write like in this example \n" +
                "#\n" +
                "#  table cards? \n" +
                "#  1 d \n" +
                "#  10 h \n" +
                "#  Q h \n" +
                "#  5 d \n" +
                "#  2 s \n" +
                "#\n" +
                "#  p1 cards? \n" +
                "#  8 d \n" +
                "#  8 c \n" +
                "#\n" +
                "#  p2 cards? \n" +
                "#  4 h \n" +
                "#  5 h \n");
        System.out.println("table cards?");
        for (int i = 0; i < 5; i++) {
            String input = scanner.nextLine().trim();
            tableCards.add(parseCard(input));
        }

        System.out.println("p1 cards?");
        for (int i = 0; i < 2; i++) {
            String input = scanner.nextLine().trim();
            player1Cards.add(parseCard(input));
        }

        System.out.println("p2 cards?");
        for (int i = 0; i < 2; i++) {
            String input = scanner.nextLine().trim();
            player2Cards.add(parseCard(input));
        }

        Combination player1Combination = CombinationResolver.resolve(tableCards, player1Cards);
        Combination player2Combination = CombinationResolver.resolve(tableCards, player2Cards);


        System.out.println("p1 - " + player1Combination);
        System.out.println("p2 -  " + player2Combination);

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

    private static Card parseCard(String input) {
        // Example parsing, assuming format: value suit (e.g., 10 h)
        String[] parts = input.split(" ");
        String value = parts[0];
        Suit suit = Suit.fromString(parts[1]);
        return new Card(value, suit);
    }

    private static void printCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println();
    }
}
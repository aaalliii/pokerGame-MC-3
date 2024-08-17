import pokerSmth.Card;
import pokerSmth.Deck;
import pokerSmth.Combination;
import pokerSmth.CombinationResolver;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> tableCards = new ArrayList<>();
        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();

        // Draw cards for table and players
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
    }

    private static void printCards(List<Card> cards) {
        for (Card card : cards) {
            System.out.println(card);
        }
        System.out.println();
    }
}
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
        for (int i = 0; i < 5; i++) {
            tableCards.add(deck.getCard());
        }

        List<Card> player1Cards = new ArrayList<>();
        List<Card> player2Cards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            player1Cards.add(deck.getCard());
            player2Cards.add(deck.getCard());
        }

        System.out.println("Table Cards:");
        for (Card card : tableCards) {
            System.out.println(card);
        }

        System.out.println("\nPlayer 1 Cards:");
        for (Card card : player1Cards) {
            System.out.println(card);
        }

        System.out.println("\nPlayer 2 Cards:");
        for (Card card : player2Cards) {
            System.out.println(card);
        }

        Combination player1Combination = CombinationResolver.resolve(tableCards, player1Cards);
        Combination player2Combination = CombinationResolver.resolve(tableCards, player2Cards);


        System.out.println("\nPlayer 1 Best Combination: " + player1Combination);
        System.out.println("Player 2 Best Combination: " + player2Combination);

        if (player1Combination.ordinal() < player2Combination.ordinal()) {
            System.out.println("\nPlayer 1 won");
        } else if (player1Combination.ordinal() > player2Combination.ordinal()) {
            System.out.println("\nPlayer 2 won");
        }else {
            System.out.println("\nIt's a tie");
        }
    }
}
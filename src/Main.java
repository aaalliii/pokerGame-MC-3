import pokerSmth.Card;
import pokerSmth.Deck;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Card\n______\n|    |\n| " + "A" + "♠" + " |\n|    |\n‾‾‾‾‾");
//        System.out.println("Card\n＿＿＿\n│    │\n│ " + "A" + "♠" + " │\n│    │\n ‾‾‾‾‾");
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> cards = deck.getCards();
        //System.out.println(cards);
        Card card1 = deck.getCard();
        Card card2 = deck.getCard();
        Card card3 = deck.getCard();
        Card card4 = deck.getCard();
        Card card5 = deck.getCard();
        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3);
        System.out.println(card4);
        System.out.println(card5);

    }
}
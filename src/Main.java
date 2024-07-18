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
        System.out.println(card1);


    }
}
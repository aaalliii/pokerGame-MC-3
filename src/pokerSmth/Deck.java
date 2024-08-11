package pokerSmth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck {
    public final String[] cardNames = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public List<Card> cards = new ArrayList<>();

    public Deck() {
        for (String cardName : cardNames){
            for (Suit suit : Suit.values()){
                cards.add(new Card(cardName, suit));
            }
        }
    }
    public void shuffle(){
        Collections.shuffle(cards);
    }
    public List<Card> getCards() {
        return cards;
    }
    public Card getCard(){
        return cards.removeFirst();
    }
}

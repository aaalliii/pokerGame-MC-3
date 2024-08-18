package pokerSmth;
import java.util.Objects;

public class Card {
    public String cardName;
    public Suit cardSuit;

    public Card(String cardName, Suit cardSuit) {
        this.cardName = cardName;
        this.cardSuit = cardSuit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardName.equals(card.cardName) && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardName, cardSuit);
    }

    @Override
    public String toString(){
//        if(cardName == "10"){
//            return "\n＿＿＿\n|    |\n|" + cardName + " " + cardSuit.symbol + "|\n|    |\n‾‾‾‾‾";
//        }
//        return "\n＿＿＿\n|    |\n| " + cardName + " " + cardSuit.symbol + "|\n|    |\n‾‾‾‾‾";
        return cardName + cardSuit;
    }
}

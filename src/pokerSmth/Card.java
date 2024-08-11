package pokerSmth;

public class Card {
    public String cardName;
    public Suit cardSuit;

    public Card(String cardName, Suit cardSuit) {
        this.cardName = cardName;
        this.cardSuit = cardSuit;
    }

    @Override
    public String toString(){
        if(cardName == "10"){
            return "\n＿＿＿\n|    |\n|" + cardName + " " + cardSuit.symbol + "|\n|    |\n‾‾‾‾‾";
        }
        return "\n＿＿＿\n|    |\n| " + cardName + " " + cardSuit.symbol + "|\n|    |\n‾‾‾‾‾";
    }
}

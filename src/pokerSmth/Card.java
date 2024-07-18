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
        return "\n＿＿＿\n|    |\n| " + cardName + cardSuit + " |\n|    |\n‾‾‾‾‾\n";
//        return "\"CARD\": \"" + cardName + "-of-" + cardSuit + "\"\n";
    }
}

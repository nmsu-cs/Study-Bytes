import java.util.LinkedList;

public class Deck {

    private LinkedList<Card> deck;
    private String title;


    public Deck(String title) {
        this.title = title;
        deck = new LinkedList<>();
    }

    public void addCard(Card card){
        deck.add(card);
    }


    public String getTitle() {
        return title;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }
}

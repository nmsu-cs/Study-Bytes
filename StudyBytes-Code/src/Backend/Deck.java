package Backend;

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

    public void addCard(String term, String definition){
        Card card = new Card(term, definition);
        deck.add(card);
    }

    public void removeCard(Card card){
        deck.remove(card);
    }

    public String getTitle() {
        return title;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }
}

package Backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DeckManager {
    private List<Deck> decks;
    private int selectedDeckIndex;

    public DeckManager(List<Deck> decks) {
        this.decks = decks;
        this.selectedDeckIndex = 0;
    }

    public void start() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            char input;
            printDecks();
            while ((input = (char) reader.read()) != 'q') { // 'q' to quit
                {

                    switch (input) {
                        case 'e': // select previous
                            selectPreviousDeck();
                            break;
                        case 'r': // select next
                            selectNextDeck();
                            break;
                        case 'v': // view cards in deck
                            viewDeck(decks.get(selectedDeckIndex));
                            break;
                        case 'c': // show deck list
                            printDecks();
                        default:
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Deck> createSample(){
        List<Deck> decks = new ArrayList<>();

        Deck deck1 = new Deck("Portal Cards");
        decks.add(deck1);

        deck1.addCard(new Card("Cave Johnson", "Aperture Science Founder"));
        deck1.addCard(new Card("GLaDOS","Genetic Lifeform and Disk Operating System"));
        deck1.addCard(new Card("Chell","Test Subject"));


        Deck deck2 = new Deck("Half-Life Cards");
        decks.add(deck2);

        deck2.addCard(new Card("Gordon Freeman","Black Mesa Scientist"));

        return decks;
    }

    private void printDecks() {
        System.out.println("Select a deck:");
        for (int i = 0; i < decks.size(); i++) {
            String prefix = (i == selectedDeckIndex) ? "> " : "  ";
            System.out.println(prefix + decks.get(i).getTitle());
        }
        printControls();
    }

    private void printControls() {
        System.out.print("________________________________________________________________________________________________");
        System.out.println("\n 'q' to quit | 'e' to select prev. | 'r' to select next |" +
                " 'v' to view deck | 'c' to show decks ");
    }

    private void viewDeck(Deck deck){

        System.out.println("Deck Title: " + deck.getTitle() + "\n");

        for(Card currentCard : deck.getDeck()){
            System.out.println(currentCard.toString());
            System.out.println();
        }

        printControls();
    }

    private void selectNextDeck() {
        selectedDeckIndex = (selectedDeckIndex + 1) % decks.size();
        printDecks();
    }

    private void selectPreviousDeck() {
        selectedDeckIndex = (selectedDeckIndex - 1 + decks.size()) % decks.size();
        printDecks();
    }



    public static void main(String[] args) {
        // Create sample decks
        List<Deck> decks = createSample();

        // Start Terminal UI
        DeckManager terminalUI = new DeckManager(decks);
        terminalUI.start();
    }
}

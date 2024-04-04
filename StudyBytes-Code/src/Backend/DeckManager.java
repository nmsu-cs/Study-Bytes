package Backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeckManager {
    private ArrayList<Deck> decks; // List to store decks
    private int selectedDeckIndex; // Index of the selected deck


    // TODO: Integrate Front-End GUI to Back-End

    public DeckManager() {

    }

    public void start() throws IOException {
        // Starts CSV processes
        CSVHandler csvHandler = new CSVHandler();
        String csv_path = "StudyBytes-Code/src/Backend/decks_csv";
        List<File> csvFiles = csvHandler.findCSVFiles(csv_path);
        decks = csvHandler.processCSVFiles(csvFiles); // Process CSV files and populate decks list

        // Test writing to CSV file
        testWrite(csvHandler);

        // Main terminal UI loop
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            char input;
            printDecks(); // Print the list of decks
            selectedDeckIndex = 0;
            while ((input = (char) reader.read()) != 'q') { // 'q' to quit
                {
                    // Handle user input
                    switch (input) {
                        case 'e': // Select previous deck
                            selectPreviousDeck();
                            break;
                        case 'r': // Select next deck
                            selectNextDeck();
                            break;
                        case 'v': // View cards in deck
                            viewDeck(decks.get(selectedDeckIndex));
                            break;
                        case 'c': // Show deck list
                            printDecks();
                            break;
                        default:
                            // Handle invalid input

                       // TODO: Add input for creating a new deck
                       // TODO: Add input for adding/removing cards to an existing deck

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to test writing to CSV file
    private void testWrite(CSVHandler csvHandler) throws IOException {
        // Create a test deck
        Deck test_write_deck = new Deck("Test Deck");
        test_write_deck.addCard("Cabbage", "Vegetable");
        test_write_deck.addCard("Apple", "Fruit");
        test_write_deck.addCard("Pear", "Fruit");

        // Write the test deck to CSV file
        csvHandler.writeCSVFiles(test_write_deck);
    }

    // Method to print the list of decks
    private void printDecks() {
        System.out.println("Select a deck:");
        for (int i = 0; i < decks.size(); i++) {
            String prefix = (i == selectedDeckIndex) ? "> " : "  ";
            System.out.println(prefix + decks.get(i).getTitle());
        }
        printControls();
    }

    // Method to print control options
    private void printControls() {
        System.out.print("________________________________________________________________________________________________");
        System.out.println("\n 'q' to quit | 'e' to select prev. | 'r' to select next |" +
                " 'v' to view deck | 'c' to show decks ");
    }

    // Method to view cards in a deck
    private void viewDeck(Deck deck) {
        System.out.println("Deck Title: " + deck.getTitle() + "\n");

        // Print each card in the deck
        for (Card currentCard : deck.getDeck()) {
            System.out.println(currentCard.toString());
            System.out.println();
        }

        printControls();
    }

    // Method to select the next deck
    private void selectNextDeck() {
        selectedDeckIndex = (selectedDeckIndex + 1) % decks.size();
        printDecks();
    }

    // Method to select the previous deck
    private void selectPreviousDeck() {
        selectedDeckIndex = (selectedDeckIndex - 1 + decks.size()) % decks.size();
        printDecks();
    }
}

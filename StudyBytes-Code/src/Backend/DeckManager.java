package Backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DeckManager {
    private ArrayList<Deck> decks;
    private int selectedDeckIndex;

    public DeckManager() {

    }

    // CSV READING
    public List<File> findCSVFiles() {

        List<File> csvFiles = new ArrayList<>();
        File directory = new File("StudyBytes-Code/src/Backend/decks_csv");

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                        csvFiles.add(file);
                    }
                }
            }
        } else {
            System.err.println("Provided path is not a directory.");
        }

        return csvFiles;
    }

    public void processCSVFiles(List<File> csvFiles) throws IOException {
        decks = new ArrayList<>();
        for (File file : csvFiles) {
            Deck currentDeck = new Deck(file.getName());
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim(); // Trim leading and trailing whitespace
                    if (line.isEmpty()) {
                        continue; // Skip empty lines
                    }
                    String[] values = line.split(",", 2); // Limit split to 2 parts
                    if (values.length >= 2) {
                        String term = values[0].trim();
                        String definition = values[1].trim();
                        Card currentCard = new Card(term, definition);
                        currentDeck.addCard(currentCard);
                    } else {
                        // Handle invalid CSV format
                        System.err.println("Invalid line in CSV file: " + line);
                    }
                }
                decks.add(currentDeck);
            } catch (IOException e) {
                System.err.println("Error reading CSV file: " + file.getName());
                e.printStackTrace();
            }
        }
    }



    // CSV WRITING



    // Terminal UI
    public void start() throws IOException {

        List<File> csvFiles = findCSVFiles();
        processCSVFiles(csvFiles);


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            char input;
            printDecks();
            selectedDeckIndex = 0;
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

    private void viewDeck(Deck deck) {

        System.out.println("Deck Title: " + deck.getTitle() + "\n");

        for (Card currentCard : deck.getDeck()) {
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
}




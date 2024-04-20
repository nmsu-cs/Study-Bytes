package Backend;

import User_Interface.LaunchFrame;
import User_Interface.LaunchPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private String directoryPath;    // Path to the directory containing CSV files
    private LaunchPanel errorPanel;  // Panel to throw error messages

    public CSVHandler()
    {}

    public CSVHandler(LaunchPanel errorPanel)
    {
        this.errorPanel = errorPanel;
    }

    // Method to find CSV files in the specified directory
    public List<File> findCSVFiles(String pathname) {
        List<File> csvFiles = new ArrayList<>();
        File directory = new File(pathname);

        // Check if the specified path is a directory
        if (directory.isDirectory()) {
            this.directoryPath = pathname; // Set the directory path
            File[] files = directory.listFiles();
            if (files != null) {
                // Iterate through files in the directory
                for (File file : files) {
                    // Check if the file is a CSV file
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                        csvFiles.add(file); // Add CSV file to the list
                    }
                }
            }
        } else {
            // Print error message if the provided path is not a directory
            System.err.println("Provided path is not a directory.");
        }

        return csvFiles; // Return the list of CSV files
    }

    // Method to process CSV files and create decks
    public ArrayList<Deck> processCSVFiles(List<File> csvFiles) throws IOException {
        ArrayList<Deck> decks = new ArrayList<>();
        boolean error = false;

        // Iterate through each CSV file
        for (File file : csvFiles) {
            Deck currentDeck = new Deck(file.getName()); // Create a new deck for each CSV file
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                // Read each line from the CSV file
                while ((line = br.readLine()) != null) {
                    line = line.trim(); // Trim leading and trailing whitespace
                    if (line.isEmpty()) {
                        continue; // Skip empty lines
                    }
                    String[] values = line.split(",", 2); // Split the line into term and definition
                    if (values.length >= 2) {
                        String term = values[0].trim();
                        String definition = values[1].trim();
                        Card currentCard = new Card(term, definition); // Create a new card
                        currentDeck.addCard(currentCard); // Add the card to the deck
                    } else {
                        // Handle invalid CSV format
                        System.err.println("Invalid line in CSV file: " + line);
                        errorPanel.throwError();
                        error = true;
                        break;
                    }
                }
                if (!error) {
                    decks.add(currentDeck); // Add the deck to the list of decks
                }
            } catch (IOException e) {
                // Print error message if there's an error reading the CSV file
                System.err.println("Error reading CSV file: " + file.getName());
                e.printStackTrace();
            }
        }
        return decks; // Return the list of decks
    }

    // Method to write a deck to a CSV file
    public void writeCSVFiles(Deck deck) throws IOException {
        // Construct the output file path
        String outputPath = directoryPath;

        try (FileWriter writer = new FileWriter(outputPath)) {
            // Iterate through each card in the deck and write it to the CSV file
            for (Card card : deck.getDeck()) {
                String term = card.getTerm();
                String definition = card.getDefinition();
                writer.write(term + "," + definition + "\n"); // Write term and definition to the file
                writer.flush(); // Flush the writer to ensure data is written immediately
            }
        } catch (IOException e) {
            // Print error message if there's an error writing to the file
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Deck has been written to " + outputPath); // Writing successful
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}

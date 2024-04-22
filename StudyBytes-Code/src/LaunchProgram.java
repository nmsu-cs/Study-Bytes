import User_Interface.*;
import Backend.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LaunchProgram
{
    public static void main(String[] args) throws IOException {
        CSVHandler csvHandler = new CSVHandler();
        List<File> csvFiles = new ArrayList<>();
        String csv_path = "StudyBytes-Code/src/Backend/decks_csv/data.csv";
        csvHandler.setDirectoryPath(csv_path);
        File dataFilePath = new File("StudyBytes-Code/src/Backend/decks_csv/data.csv");
        csvFiles.add(dataFilePath);
        ArrayList<Deck> decks = csvHandler.processCSVFiles(csvFiles);
        Deck linkedDeck = decks.get(0);

        LaunchFrame frame1 = new LaunchFrame(linkedDeck, csvHandler);  // Frame for deck viewer screen
    }
}
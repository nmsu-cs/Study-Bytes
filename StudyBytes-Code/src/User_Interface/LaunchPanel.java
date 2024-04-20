package User_Interface;

import Backend.CSVHandler;
import Backend.Card;
import Backend.Deck;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LaunchPanel extends JPanel implements ActionListener
{
    private Deck linkedDeck;
    private LaunchFrame frame;                // Frame this panel exits in
    private CSVHandler csvHandler;

    private GridBagConstraints constraints;   // GridBagConstraints object to modify constraints
    private JTextArea termField;              // Text field for card term
    private JTextArea definitionField;        // Text field for card definition
    private Border border;                    // Border for panels and text fields
    private JButton editButton;
    private JButton viewButton;
    private JButton downloadButton;          // Button to download deck as CSV
    private JButton uploadButton;            // Button to upload deck from CSV

    public LaunchPanel(Deck linkedDeck, LaunchFrame frame, CSVHandler csvHandler){
        this.linkedDeck = linkedDeck;
        this.frame = frame;
        this.csvHandler = csvHandler;


        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        editButton = new JButton();
        editButton.setBounds(200,100,50,50);
        editButton.setText("Edit");
        editButton.addActionListener( this);

        viewButton = new JButton();
        viewButton.setBounds(50,100,50,50);
        viewButton.setText("View");
        viewButton.addActionListener( this);

        // Create and set up download button
        downloadButton = new JButton("Download");
        downloadButton.addActionListener(this);
        downloadButton.setBounds(350, 100, 50, 50);

        // Create and set up upload button
        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(this);
        uploadButton.setBounds(500, 100, 50, 50);

        // Add buttons to panel
        this.add(editButton);
        this.add(viewButton);
        this.add(downloadButton);
        this.add(uploadButton);

        if (linkedDeck.getDeck().size() == 0)
        {   // Deck is empty, remove viewButton and download functionality
            viewButton.setEnabled(false);
            downloadButton.setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            frame.dispose();
            DeckEditFrame edit = new DeckEditFrame(linkedDeck, csvHandler);
        } else if (e.getSource() == viewButton) {
            frame.dispose();
            FlashcardFrame view = new FlashcardFrame(linkedDeck, csvHandler);
        }

        if (e.getSource() == downloadButton) {   // Launch option pane
            String message;
            int paneResponse;
            int fileResponse;
            JFileChooser fileChooser;
            File savePath;
            String stringPath;

            message = "Are you sure you want to download this deck as a CSV file?";
            paneResponse = JOptionPane.showConfirmDialog(null, message, "Confirm Download", JOptionPane.YES_NO_OPTION);

            if (paneResponse == 0) {   // "Yes" clicked, launch file chooser
                fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
                fileChooser.setFileFilter(filter);
                fileResponse = fileChooser.showSaveDialog(null);

                if (fileResponse == JFileChooser.APPROVE_OPTION) {   // File selected and approved, save file path
                    savePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    stringPath = savePath.toString();

                    // Write data to file and save
                    CSVHandler newHandler = new CSVHandler();
                    newHandler.setDirectoryPath(stringPath);
                    try {
                        newHandler.writeCSVFiles(linkedDeck);
                        JOptionPane.showMessageDialog(null, "Deck downloaded successfully!", null, JOptionPane.PLAIN_MESSAGE);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

        if (e.getSource() == uploadButton) {   // Launch option pane
            String message;
            int paneResponse;
            int fileResponse;
            JFileChooser fileChooser;
            File savePath;
            String stringPath;

            message = "Uploading a deck will delete the current deck. Continue?";
            paneResponse = JOptionPane.showConfirmDialog(null, message, "Confirm Upload", JOptionPane.YES_NO_OPTION);

            if (paneResponse == 0) {   // "Yes" clicked, launch file chooser
                fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
                fileChooser.setFileFilter(filter);
                fileResponse = fileChooser.showOpenDialog(null);

                if (fileResponse == JFileChooser.APPROVE_OPTION) {   // File selected and approved, save file path
                    savePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    stringPath = savePath.toString();

                    // Write data to file and save
                    CSVHandler newHandler = new CSVHandler(this);
                    List<File> csvFiles = new ArrayList<>();
                    newHandler.setDirectoryPath(stringPath);
                    csvFiles.add(savePath);
                    try {
                        ArrayList<Deck> decks = newHandler.processCSVFiles(csvFiles);
                        linkedDeck = decks.get(0);
                        csvHandler.writeCSVFiles(linkedDeck);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        newHandler.writeCSVFiles(linkedDeck);
                        JOptionPane.showMessageDialog(null, "Deck uploaded successfully!", null, JOptionPane.PLAIN_MESSAGE);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    /**
     * Launches an option pane, informing the user that their upload failed
     */
    public void throwError ()
    {
        String message;

        message = "ERROR: File upload failed!";
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

}

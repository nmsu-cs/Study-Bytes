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

        GridBagConstraints constraints = new GridBagConstraints();

        this.setBackground(new Color(204, 255, 255));
        this.setLayout(new GridBagLayout());

        // Set up title label
        JLabel title = new JLabel("Study Bytes");
        title.setFont(new Font("Average", Font.BOLD, 100));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(title, constraints);

        // Set up sub title label
        JLabel subTitle = new JLabel("The Superior Study Tool");
        subTitle.setFont(new Font("Average", Font.ITALIC, 36));

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(subTitle, constraints);

        // Set up view button
        viewButton = new JButton();
        viewButton.setText("Study My Deck");
        viewButton.setFont(new Font("Average", Font.PLAIN, 24));
        viewButton.setPreferredSize(new Dimension(300, 100));
        viewButton.addActionListener( this);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(50, 0, 5, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(viewButton, constraints);

        // Set up edit button
        editButton = new JButton();
        editButton.setText("Edit My Deck");
        editButton.setPreferredSize(new Dimension(150, 50));
        editButton.addActionListener( this);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(editButton, constraints);

        // Set up download button
        downloadButton = new JButton("Download Deck");
        downloadButton.setPreferredSize(new Dimension(150, 50));
        downloadButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(downloadButton, constraints);


        // Set up upload button
        uploadButton = new JButton("Upload Deck");
        uploadButton.setPreferredSize(new Dimension(150, 50));
        uploadButton.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;

        this.add(uploadButton, constraints);

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

            message = "Download this deck as a CSV file?";
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

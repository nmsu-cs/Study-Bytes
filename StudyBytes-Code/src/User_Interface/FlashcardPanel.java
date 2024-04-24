package User_Interface;

import Backend.CSVHandler;
import Backend.Card;
import Backend.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardPanel extends JPanel implements ActionListener
{
    /**
     * Sets up the panel for the flashcard flip screen
     */

    Deck linkedDeck;                 // Current deck being used
    FlashcardFrame frame;            // Frame that this panel is placed in
    CSVHandler csvHandler;

    GridBagConstraints constraints;  // Constraints object
    JButton flashcard;               // Flashcard button to flip
    JButton prevCard;                // Button to switch to previous card
    JButton nextCard;                // Button to switch to next card
    JLabel indexLabel;               // Label to display position of current card
    JButton backButton;              // Button to return to launch screen
    Card displayedCard;              // Current card being displayed
    String term;                     // Term value for current card being displayed
    String definition;               // Definition value for current card being displayed
    boolean termDisplayed;           // True if term is displayed, false if definition is displayed
    int currIndex;                   // Index of the current card being displayed (starting at 1)
    int totalCards;                  // Number of cards in the deck

    public FlashcardPanel(Deck linkedDeck, FlashcardFrame frame, CSVHandler csvHandler)
    {
        this.linkedDeck = linkedDeck;
        this.frame = frame;
        this.csvHandler = csvHandler;

        // Set up panel attributes
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(204, 255, 255));
        constraints = new GridBagConstraints();

        // Create and place flashcard button in center of screen
        flashcard = new JButton();
        flashcard.addActionListener(this);
        flashcard.setPreferredSize(new Dimension(900, 600));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 25, 0);

        this.add(flashcard, constraints);

        // Create and add previous card button
        prevCard = new JButton("Prev");
        prevCard.addActionListener(this);
        prevCard.setPreferredSize(new Dimension(150, 75));

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(0, 0, 0, 0);

        this.add(prevCard, constraints);

        // Create and add next button card
        nextCard = new JButton("Next");
        nextCard.addActionListener(this);
        nextCard.setPreferredSize(new Dimension(150, 75));

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0, 0, 0, 0);

        this.add(nextCard, constraints);

        // Create and add label
        indexLabel = new JLabel();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0, 10, 0, 10);

        this.add(indexLabel, constraints);

        // Create and add back button
        backButton = new JButton("Exit");
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(150, 50));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(0, 75, 0, 0);

        this.add(backButton, constraints);

        // Set initial card shown
        displayedCard = linkedDeck.getDeck().get(0);

        // Set initial flashcard data
        term = addNewlines(displayedCard.getTerm());
        definition = addNewlines(displayedCard.getDefinition());
        flashcard.setText("<html><center>" + term + "<center><html>");
        termDisplayed = true;

        // Set initial indexLabel text
        currIndex = 1;
        totalCards = linkedDeck.getDeck().size();
        indexLabel.setText(currIndex + "/" + totalCards);

        // Ensure correct buttons are enabled/disabled
        disableButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backButton)
        {   // Back button clicked, return to launch screen
            frame.dispose();
            LaunchFrame launchFrame = new LaunchFrame(linkedDeck, csvHandler);
        }

        if (e.getSource() == flashcard)
        {   // Flashcard clicked, flip card
            if (termDisplayed)
            {   // Show definition
                definition = addNewlines(definition);   // Format to wrap long text on button
                flashcard.setText("<html><center>" + definition + "<center><html>");
                termDisplayed = false;
            }
            else
            {   // Show term
                term = addNewlines(term);   // Format to wrap long text on button
                flashcard.setText("<html><center>" + term + "<center><html>");
                termDisplayed = true;
            }
        }

        if (e.getSource() == nextCard)
        {   // Next button clicked, update values for flashcard button
            displayedCard = linkedDeck.getDeck().get(linkedDeck.getDeck().indexOf(displayedCard) + 1);
            term = addNewlines(displayedCard.getTerm());
            definition = addNewlines(displayedCard.getDefinition());
            flashcard.setText("<html><center>" + term + "<center><html>");

            // Update index label
            ++currIndex;
            indexLabel.setText(currIndex + "/" + totalCards);

            // Ensure correct buttons are enabled/disabled
            disableButtons();
        }

        if (e.getSource() == prevCard)
        {   // Prev button clicked, update values for flashcard button
            displayedCard = linkedDeck.getDeck().get(linkedDeck.getDeck().indexOf(displayedCard) -1);
            term = addNewlines(displayedCard.getTerm());
            definition = addNewlines(displayedCard.getDefinition());
            flashcard.setText("<html><center>" + term + "<center><html>");

            // Update index label
            --currIndex;
            indexLabel.setText(currIndex + "/" + totalCards);

            // Ensure correct buttons are enabled/disabled
            disableButtons();
        }
    }

    /**
     * Disables either the prev button, next button, or neither, depending on the index of the current card being displayed
     */
    public void disableButtons()
    {
        if (currIndex == 1)
        {   // Disable prev button
            prevCard.setEnabled(false);
        }
        else
        {   // Ensure prev button is enabled
            prevCard.setEnabled(true);
        }

        if (currIndex == linkedDeck.getDeck().size())
        {   // Disable next button
            nextCard.setEnabled(false);
        }
        else
        {   // Ensure next button is enabled
            nextCard.setEnabled(true);
        }
    }

    /**
     * Adds a newline character every 100 characters in a String
     *
     * @param input
     * @return
     */
    public static String addNewlines(String input) {
        StringBuilder builder = new StringBuilder(input);
        int offset = 0;
        for (int i = 100; i < builder.length(); i += 100) {
            builder.insert(i + offset, '\n');
            offset++;
        }
        return builder.toString();
    }

}
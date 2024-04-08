package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardFlipPanel extends JPanel implements ActionListener
{
    /**
     * This class sets up the center panel for FlashcardFrame.
     * An instance of this class is a flippable flashcard.
     */
    private TestData deck;          // Deck data object
    private JButton flipButton;     // Button to "flip" flashcard
    private boolean termDisplayed;  // True if term displayed, false if definition displayed
    private TestDataStructure data; // Data set to use for text display
    private int currCardIndex;      // Tracks the index of the current card on display
    private boolean prevButtonEnabled;
    private boolean nextButtonEnabled;

    public CardFlipPanel(TestData deck)
    {
        this.deck = deck;

        Border cardBorder = BorderFactory.createEtchedBorder();    // Border for card

        // Set attributes of panel
        this.setLayout(new BorderLayout());    // Will be useful later for adding a label to this panel
        this.setBackground(Color.WHITE);

        // Set attributes of flipButton
        flipButton = new JButton();;
        flipButton.setBorder(cardBorder);       // Set card border
        flipButton.addActionListener(this);  // Add action listener to card button

        // Set initial text on card (initially term)
        currCardIndex = 0;
        data = deck.studySet.get(0);
        termDisplayed = true;
        flipButton.setText(data.term);

        // Add button to panel
        this.add(flipButton, BorderLayout.CENTER);

    }

    /**
     * Returns the index of the current card being displayed
     *
     * @return current index
     */
    public int getCurrCardIndex()
    {
        return currCardIndex;
    }

    /**
     * Returns the max index in the deck
     *
     * @return max index
     */
    public int getMaxIndex()
    {
        return deck.studySet.size() - 1;
    }

    /**
     * Sets current card to previous card in the deck
     */
    public void prevCard()
    {
        termDisplayed = true;
        data = deck.studySet.get(--currCardIndex);       // Set card data to card in previous index in array and update currIndex
        flipButton.setText(data.term);      // Display new text
    }

    /**
     * Sets current card to next card in the deck
     */
    public void nextCard()
    {
        termDisplayed = true;
        data = deck.studySet.get(++currCardIndex);       // Set card data to card in next index in array and update currIndex
        flipButton.setText(data.term);      // Display new text
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == flipButton)
        {
            // Flip card
            if (termDisplayed) {
                flipButton.setText(data.definition);
                termDisplayed = false;
            }
            else {
                flipButton.setText(data.term);
                termDisplayed = true;
            }
        }
    }
}


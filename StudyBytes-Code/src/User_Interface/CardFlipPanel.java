package User_Interface;

import Backend.Card;
import Backend.Deck;

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
    private Deck linkedDeck;          // Deck data object
    private JButton flipButton;     // Button to "flip" flashcard
    private boolean termDisplayed;  // True if term displayed, false if definition displayed
    private Card data; // Data set to use for text display
    private int currCardIndex;      // Tracks the index of the current card on display
    private boolean prevButtonEnabled;
    private boolean nextButtonEnabled;

    public CardFlipPanel(Deck linkedDeck)
    {
        this.linkedDeck = linkedDeck;

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
        data = linkedDeck.getDeck().peek();
        termDisplayed = true;
        flipButton.setText(data.getTerm());

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
        return linkedDeck.getDeck().size() - 1;
    }

    /**
     * Sets current card to previous card in the deck
     */
    public void prevCard()
    {
        termDisplayed = true;
        data = linkedDeck.getDeck().get(--currCardIndex);       // Set card data to card in previous index in array and update currIndex
        flipButton.setText(data.getTerm());      // Display new text
    }

    /**
     * Sets current card to next card in the deck
     */
    public void nextCard()
    {
        termDisplayed = true;
        data = linkedDeck.getDeck().get(++currCardIndex);       // Set card data to card in next index in array and update currIndex
        flipButton.setText(data.getTerm());      // Display new text
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == flipButton)
        {
            // Flip card
            if (termDisplayed) {
                flipButton.setText(data.getDefinition());
                termDisplayed = false;
            }
            else {
                flipButton.setText(data.getTerm());
                termDisplayed = true;
            }
        }
    }
}


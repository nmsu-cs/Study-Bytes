package User_Interface;

import javax.swing.*;

public class IndexLabel extends JLabel
{
    /**
     * This class sets up the label to communicate the index of the current card being viewed (with indices beginning at 1), and the total number of cards in the deck
     */

    private int currCard;   // Index of current card being viewed
    private int total;  // Total number of cards in deck
    private CardFlipPanel centerPanel;  // CardFlipPanel object being used

    public IndexLabel(CardFlipPanel centerPanel)
    {
        currCard = 1;   // Initial card being viewed is the first card in the deck
        total = centerPanel.getMaxIndex() + 1;
        this.centerPanel = centerPanel;

        // Set text for label
        this.setText(buildLabelString());
    }

    /**
     * Creates the string to be displayed in the label
     *
     * @return label text
     */
    public String buildLabelString()
    {
        return currCard + "/" + total;
    }

    /**
     * Updates the value of currCard after a button is clicked in FlashcardSouthPanel, and sets the updated text field
     */
    public void updateCurrCard()
    {
        currCard = centerPanel.getCurrCardIndex() + 1;
        this.setText(buildLabelString());
    }
}

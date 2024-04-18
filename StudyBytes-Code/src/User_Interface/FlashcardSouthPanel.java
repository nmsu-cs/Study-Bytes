package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardSouthPanel extends JPanel implements ActionListener
{
    /**
     * This class sets up the south panel of the flashcard frame, containing the buttons to flip between cards
     */

    // "next" and "previous" buttons
    private JButton prevButton;
    private JButton nextButton;

    private CardFlipPanel cardPanel;   // Flashcard panel for this frame
    private IndexLabel label;          // Label to display current card index and total number of cards

    public FlashcardSouthPanel(CardFlipPanel centerPanel)
    {
        // Define cardPanel object
        cardPanel = centerPanel;

        // Set attributes for frame
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));   // Flowlayout with 15 pixels of space between elements and 10 pixels of space between elements and top of panel
        this.setPreferredSize(new Dimension(0, 150));
        this.setBackground(Color.WHITE);

        // Create "next" and "previous" buttons
        prevButton = new JButton();
        nextButton = new JButton();


        // Set button sizes
        prevButton.setPreferredSize(new Dimension(300,100));
        nextButton.setPreferredSize(new Dimension(300, 100));

        // Add "Prev" and "Next" labels to buttons
        prevButton.setText("Prev");
        nextButton.setText("Next");

        // Add action listeners to buttons
        prevButton.addActionListener(this);
        nextButton.addActionListener(this);


        // Set "Prev" button initially disabled
        prevButton.setEnabled(false);

        // Create label to display index of current card
        label = new IndexLabel(cardPanel);

        // Add buttons and label to frame
        this.add(prevButton);
        this.add(label);
        this.add(nextButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Use buttons to move through study deck
        if (e.getSource() == prevButton)
        {   // Prev button clicked

            nextButton.setEnabled(true);    // Ensure next button enabled
            cardPanel.prevCard();           // Move to previous card

            if (cardPanel.getCurrCardIndex() == 0)
            {   // Disable prev button to prevent moving out of array
                prevButton.setEnabled(false);
            }
            else
            {   // Ensure prev button is enabled
                prevButton.setEnabled(true);
            }

            label.updateCurrCard();     // Update label text
        }

        if (e.getSource() == nextButton)
        {   // Next button clicked

            prevButton.setEnabled(true);    // Ensure prev button enabled
            cardPanel.nextCard();           // Move to next card

            if (cardPanel.getCurrCardIndex() == cardPanel.getMaxIndex())
            {   // Disable next button to prevent moving out of array
                nextButton.setEnabled(false);
            }
            else
            {   // Ensure next button is enabled
                nextButton.setEnabled(true);
            }

            label.updateCurrCard();     // Update label text
        }
    }
}



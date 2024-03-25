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

    private JButton flipButton;     // Button to "flip" flashcard
    private boolean termDisplayed;  // True if term displayed, false if definition displayed
    private TestData data;          // Data set to use for text display

    public CardFlipPanel()
    {
        Border cardBorder = BorderFactory.createLineBorder(Color.BLACK, 3);    // Border for card

        // Set attributes of panel
        this.setLayout(new BorderLayout());    // Will be useful later for adding a label to this panel
        this.setBackground(Color.WHITE);

        // Set attributes of flipButton
        flipButton = new JButton();;
//        flipButton.setBorder(cardBorder);     // Make button border invisible
//        this.setBorder(cardBorder);
        flipButton.setBackground(Color.RED);
        flipButton.addActionListener(this);

        // Set initial text on card (initially term)
        data = new TestData();
        termDisplayed = true;
        flipButton.setText(data.studySet[0].term);

        // Add button to panel
        this.add(flipButton, BorderLayout.CENTER);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == flipButton)
        {
            // Flip card
            if (termDisplayed) {
                flipButton.setText(data.studySet[0].definition);
                termDisplayed = false;
            }
            else {
                flipButton.setText(data.studySet[0].term);
                termDisplayed = true;
            }
        }
    }
}

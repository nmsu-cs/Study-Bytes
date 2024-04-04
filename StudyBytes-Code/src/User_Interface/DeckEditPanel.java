package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckEditPanel extends JPanel implements ActionListener
{
    /**
     * This class sets up the panel that fills the entire deck edit frame
     */

    private GridBagConstraints constraints;  // GridBagConstraints object
    private JButton saveButton;              // Button to save changes to deck

    TestData deck;                           // Deck of cards, with each card containing a term and a definition

    public DeckEditPanel(TestData deck)
    {
        this.deck = deck;

        // Set up panel attributes
        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());        // Set layout of panel to GridBagLayout
        constraints = new GridBagConstraints();     // Create GridBagConstraints object

        // Add card edit panels to this panel
        for (int i = 0; i < deck.studySet.length; ++i)
        {
            constraints.gridx = 0;
            constraints.gridy = i + 1;
            constraints.gridwidth = 2;
            constraints.insets = new Insets(25, 0, 25, 0);
            this.add(new CardEditPanel(deck.studySet[i]), constraints);
        }

        // Create save button
        saveButton = new JButton("Save Changes to Deck");
        saveButton.addActionListener(this);

        // Set save button attributes
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(25, 0, 0, 0);
        saveButton.setPreferredSize(new Dimension(200, 40));

        this.add(saveButton, constraints);  // Add save button to panel
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == saveButton)
        {   // Save button clicked, save attributes of deck object to database
            for (int i = 0; i < deck.studySet.length; ++i)
            {
                System.out.println(deck.studySet[i].term + ": " + deck.studySet[i].definition);
            }
        }
    }
}
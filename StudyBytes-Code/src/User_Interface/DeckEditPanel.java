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
    private JButton addButton;               // Button to add a new card to deck

    TestData deck;                           // Deck of cards, with each card containing a term and a definition

    public DeckEditPanel(TestData deck)
    {
        this.deck = deck;

        // Set up panel attributes
        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());        // Set layout of panel to GridBagLayout
        constraints = new GridBagConstraints();     // Create GridBagConstraints object

        // Add card edit panels to this panel
        for (int i = 0; i < deck.studySet.size(); ++i)
        {
            constraints.gridx = 0;
            constraints.gridy = i + 1;
            constraints.insets = new Insets(25, 0, 25, 0);
            this.add(new CardEditPanel(deck.studySet.get(i), this), constraints);
        }

        // Create save button
        saveButton = new JButton("Save Changes to Deck");
        saveButton.addActionListener(this);

        // Set save button attributes
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(25, 0, 0, 0);
        saveButton.setPreferredSize(new Dimension(200, 40));

        this.add(saveButton, constraints);  // Add save button to panel

        // Create add button
        addButton = new JButton("New Card");
        addButton.addActionListener(this);

        // Set add button attributes
        constraints.gridx = 0;
        constraints.gridy = deck.studySet.size() + 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 25, 0);
        addButton.setPreferredSize(new Dimension(300, 40));

        this.add(addButton, constraints);
    }

    /**
     * Removes a card from the deck and deletes the card's panel
     *
     * @param card card to remove from deck
     * @param panel panel that contains card's data
     */
    public void deleteCard(TestDataStructure card, CardEditPanel panel)
    {
        deck.studySet.remove(card);
        this.remove(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == saveButton)
        {   // Save button clicked, save attributes of deck object to database
            for (int i = 0; i < deck.studySet.size(); ++i)
            {
                System.out.println(deck.studySet.get(i).term + ": " + deck.studySet.get(i).definition);
            }
        }

        if (e.getSource() == addButton)
        {   // Add button clicked, create new card panel
            constraints.gridx = 0;
            constraints.gridy = deck.studySet.size() + 1;
            constraints.insets = new Insets(25, 0, 25, 0);
            deck.studySet.add(new TestDataStructure("", ""));   // Add empty card to deck
            this.add(new CardEditPanel(deck.studySet.get(deck.studySet.size() - 1), this), constraints);

            // Move down add button
            this.remove(addButton);
            constraints.gridx = 0;
            constraints.gridy = deck.studySet.size() + 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(0, 0, 25, 0);
            this.add(addButton, constraints);
        }
    }
}
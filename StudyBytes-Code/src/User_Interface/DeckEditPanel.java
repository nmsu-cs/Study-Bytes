package User_Interface;

import Backend.Card;
import Backend.Deck;
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

    Deck linkedDeck;                         // Deck of cards, with each card containing a term and a definition

    public DeckEditPanel(Deck linkedDeck)
    {
        this.linkedDeck = linkedDeck;

        // Set up panel attributes
        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());        // Set layout of panel to GridBagLayout
        constraints = new GridBagConstraints();     // Create GridBagConstraints object

        // Add card edit panels to this panel
        for (int i = 0; i < linkedDeck.getDeck().size(); ++i)
        {
            constraints.gridx = 0;
            constraints.gridy = i + 1;
            constraints.insets = new Insets(25, 0, 25, 0);
            this.add(new CardEditPanel(linkedDeck.getDeck().get(i), this), constraints);
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
        constraints.gridy = linkedDeck.getDeck().size() + 1;
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
    public void deleteCard(Card card, CardEditPanel panel)
    {
        linkedDeck.getDeck().remove(card);
        this.remove(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == saveButton)
        {   // Save button clicked, save attributes of deck object to database
            for (int i = 0; i < linkedDeck.getDeck().size(); ++i)
            {
                System.out.println(linkedDeck.getDeck().get(i).getTerm() + ": " + linkedDeck.getDeck().get(i).getDefinition());
            }
        }

        if (e.getSource() == addButton)
        {   // Add button clicked, create new card panel
            constraints.gridx = 0;
            constraints.gridy = linkedDeck.getDeck().size() + 1;
            constraints.insets = new Insets(25, 0, 25, 0);
            linkedDeck.getDeck().add(new Card("", ""));   // Add empty card to deck
            this.add(new CardEditPanel(linkedDeck.getDeck().get(linkedDeck.getDeck().size() - 1), this), constraints);

            // Move down add button
            this.remove(addButton);
            constraints.gridx = 0;
            constraints.gridy = linkedDeck.getDeck().size() + 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = new Insets(0, 0, 25, 0);
            this.add(addButton, constraints);
        }
    }
}
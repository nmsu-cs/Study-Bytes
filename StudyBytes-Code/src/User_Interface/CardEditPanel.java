package User_Interface;

import Backend.Deck;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardEditPanel extends JPanel implements ActionListener
{
    /**
     * This class acts as the format for a panel that allows the user to edit a card
     */

    private GridBagConstraints constraints;   // GridBagConstraints object to modify constraints
    private JTextArea termField;              // Text field for card term
    private JTextArea definitionField;        // Text field for card definition
    private Border border;                    // Border for panels and text fields
    private JButton editButton;               // Button to update contents of card
    private JButton deleteButton;             // Button to delete card from deck

    TestDataStructure card;  // Card attributes for the text fields for this card
    DeckEditPanel editPage;  // Deck edit panel, used to call deleteCard() method in DeckEditPanel instance

    public CardEditPanel(TestDataStructure card, DeckEditPanel editPage)
    {
        this.card = card;
        this.editPage = editPage;

        // Set up panel attributes
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        border = BorderFactory.createLineBorder(Color.BLACK);    // Border for card
        this.setBorder(border);
        this.setPreferredSize(new Dimension(1000, 400));
        this.setBackground(Color.WHITE);

        // Create text fields for cards
        termField = new JTextArea(this.card.term);
        definitionField = new JTextArea(this.card.definition);

        // Constraints for termField
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 10, 0, 10);

        // Attributes for termField
        termField.setPreferredSize(new Dimension(450, 225));
        termField.setBorder(border);
        termField.setLineWrap(true);    // Enable text wrapping
        termField.setWrapStyleWord(true);  // Wrap at word boundaries

        this.add(termField, constraints);

        // Constraints for definitionField
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 10, 0, 10);

        // Attributes for definitionField
        definitionField.setPreferredSize(new Dimension(450, 225));
        definitionField.setBorder(border);
        definitionField.setLineWrap(true);       // Enable text wrapping
        definitionField.setWrapStyleWord(true);  // Wrap at word boundaries

        this.add(definitionField, constraints);

        // Create "Save" button to save edits
        editButton = new JButton("Save Card");
        editButton.addActionListener(this);

        // Constraints and attributes of "Save" button
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(15, 0, 0, 0);
        editButton.setPreferredSize(new Dimension(100, 40));

        this.add(editButton, constraints);

        // Create delete button
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);

        // Set attributes of delete button
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 15, 0);
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        deleteButton.setPreferredSize(new Dimension(100, 40));

        this.add(deleteButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == editButton)
        {   // Edit button clicked, update card attributes
            card.term = termField.getText();
            card.definition = definitionField.getText();
        }

        if (e.getSource() == deleteButton)
        {   // Delete button clicked, remove card from deck, and delete this panel
            editPage.deleteCard(card, this);
        }
    }
}
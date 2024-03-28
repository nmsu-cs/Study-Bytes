package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardEditPanel extends JPanel
{
    /**
     * This class acts as the format for a panel that allows the user to edit a card
     */

    GridBagConstraints constraints;   // GridBagConstraints object to modify constraints
    JTextArea termField;              // Text field for card term
    JTextArea definitionField;        // Text field for card definition
    Border border;                    // Border for panels and text fields

    TestDataStructure card;  // Card attributes for the text fields for this card

    public CardEditPanel(TestDataStructure card)
    {
        this.card = card;

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
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Attributes for termField
        termField.setPreferredSize(new Dimension(200, 200));
        termField.setBorder(border);
        termField.setLineWrap(true);    // Enable text wrapping
        termField.setWrapStyleWord(true);  // Wrap at word boundaries
        this.add(termField, constraints);

        // Constraints for definitionField
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Attributes for definitionField
        definitionField.setPreferredSize(new Dimension(200, 200));
        definitionField.setBorder(border);
        definitionField.setLineWrap(true);       // Enable text wrapping
        definitionField.setWrapStyleWord(true);  // Wrap at word boundaries
        this.add(definitionField, constraints);

    }
}
package User_Interface;

import Backend.Card;
import Backend.Deck;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPanel extends JPanel implements ActionListener
{
    private Deck linkedDeck;
    private GridBagConstraints constraints;   // GridBagConstraints object to modify constraints
    private JTextArea termField;              // Text field for card term
    private JTextArea definitionField;        // Text field for card definition
    private Border border;                    // Border for panels and text fields
    private JButton editButton;
    private JButton viewButton;

    public LaunchPanel(Deck linkedDeck){
        this.linkedDeck = linkedDeck;

        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout());

        editButton = new JButton();
        editButton.setBounds(200,100,50,50);
        editButton.setText("Edit");
        editButton.addActionListener( this);

        viewButton = new JButton();
        viewButton.setBounds(50,100,50,50);
        viewButton.setText("View");
        viewButton.addActionListener( this);

        this.add(editButton);
        this.add(viewButton);

        if (linkedDeck.getDeck().size() == 0)
        {   // Deck is empty, remove viewButton functionality
            viewButton.setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editButton){
            this.setVisible(false);
            DeckEditFrame edit = new DeckEditFrame(linkedDeck);
        }else if(e.getSource() == viewButton){
            this.setVisible(false);
            FlashcardFrame view = new FlashcardFrame(linkedDeck);
        }
    }

}

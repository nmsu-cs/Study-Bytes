package User_Interface;

import Backend.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardFrame extends JFrame
{
    private Deck linkedDeck;  // Deck data object

    private JButton backButton;
    FlashcardPanel panel;

    public FlashcardFrame(Deck linkedDeck)
    {
        this.linkedDeck = linkedDeck;

        // Set up frame
        this.setTitle("Study Bytes");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create and add flashcard panel
        panel = new FlashcardPanel(linkedDeck, this);
        this.add(panel);

        this.setVisible(true);  // Make frame visible

    }


}


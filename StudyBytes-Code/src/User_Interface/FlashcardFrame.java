package User_Interface;

import Backend.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardFrame extends JFrame implements ActionListener
{
    private Deck linkedDeck;  // Deck data object

    private JButton backButton;

    public FlashcardFrame(Deck linkedDeck)
    {
        this.linkedDeck = linkedDeck;

        // Set up frame
        this.setTitle("Study Bytes");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout(10, 10));   // Frame has border layout with space of ten pixels between panels

        // Get the content pane and set its background color (for the color between panels)
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);

        backButton = new JButton("<-");
        backButton.addActionListener(this);
        backButton.setPreferredSize(new Dimension(40, 40));
        this.add(backButton);


        // Create main panel
        CardFlipPanel cardPanel = new CardFlipPanel(this.linkedDeck);    // Panel to display flashcards

        // Create south panel
        FlashcardSouthPanel southPanel = new FlashcardSouthPanel(cardPanel);     // Panel containing buttons to flip through cards

        // Create border panels
        JPanel northPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // Set border panel sizes
        northPanel.setPreferredSize(new Dimension(0, 150));
        eastPanel.setPreferredSize(new Dimension(250, 0));
        westPanel.setPreferredSize(new Dimension(250, 0));

        // Set border panel colors
        northPanel.setBackground(Color.WHITE);

        southPanel.setBackground(Color.WHITE);
        eastPanel.setBackground(Color.WHITE);
        westPanel.setBackground(Color.WHITE);

        // Add panels to frame
        this.add(cardPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);

        this.setVisible(true);  // Make frame visible

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            this.setVisible(false);
            LaunchFrame launch = new LaunchFrame(linkedDeck);
        }
    }
}


package User_Interface;

import javax.swing.*;
import java.awt.*;

public class FlashcardFrame extends JFrame
{
    public FlashcardFrame()
    {
        // Set up frame
        this.setTitle("Study Bytes");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(500, 500);
        this.setLayout(new BorderLayout());

        // Create main panel
        CardFlipPanel cardPanel = new CardFlipPanel();    // Panel to display flashcards

        // Create border panels
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // Set border panel sizes
        northPanel.setPreferredSize(new Dimension(0, 150));
        southPanel.setPreferredSize(new Dimension(0, 150));
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

        this.setVisible(true);




    }
}

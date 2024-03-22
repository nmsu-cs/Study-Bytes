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
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // Create panels
        JPanel cardPanel = new JPanel();    // Panel to display flashcards
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        // Set border panel sizes
        northPanel.setPreferredSize(new Dimension(0, 200));
        southPanel.setPreferredSize(new Dimension(0, 200));
        eastPanel.setPreferredSize(new Dimension(300, 0));
        westPanel.setPreferredSize(new Dimension(300, 0));

        // Panel colors
        cardPanel.setBackground(Color.WHITE);
        northPanel.setBackground(Color.BLUE);
        southPanel.setBackground(Color.RED);
        eastPanel.setBackground(Color.GREEN);
        westPanel.setBackground(Color.PINK);

        // Add panels to frame
        this.add(cardPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);


    }
}

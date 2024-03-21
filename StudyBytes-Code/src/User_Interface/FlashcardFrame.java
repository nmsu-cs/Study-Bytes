package User_Interface;

import javax.swing.*;
import java.awt.*;

public class FlashcardFrame extends JFrame
{
    public FlashcardFrame()
    {
        this.setTitle("Study Bytes");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        JPanel cardPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel westPanel = new JPanel();

        cardPanel.setPreferredSize(new Dimension(25, 25));
        northPanel.setPreferredSize(new Dimension(100, 100));
        southPanel.setPreferredSize(new Dimension(100, 100));
        eastPanel.setPreferredSize(new Dimension(100, 100));
        westPanel.setPreferredSize(new Dimension(100, 100));

        cardPanel.setBackground(Color.WHITE);
        northPanel.setBackground(Color.BLUE);
        southPanel.setBackground(Color.RED);
        eastPanel.setBackground(Color.GREEN);
        westPanel.setBackground(Color.PINK);

        this.add(cardPanel, BorderLayout.CENTER);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);


    }
}

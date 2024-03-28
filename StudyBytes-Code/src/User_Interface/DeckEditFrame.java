package User_Interface;

import javax.swing.*;

public class DeckEditFrame extends JFrame
{
    /**
     * This class sets up the frame for the deck editor screen
     */

    public DeckEditFrame()
    {
        // Set up frame
        this.setTitle("Study Bytes");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Create panel to fill entire frame
        DeckEditPanel panel = new DeckEditPanel();

        // Create JScrollPane for deck edit panel and add it to frame
        JScrollPane scrollPane = new JScrollPane(panel);
        this.add(scrollPane);

        this.setVisible(true);
    }

}
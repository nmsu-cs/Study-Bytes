package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardEditPanel extends JPanel
{
    /**
     * This class acts as the format for a panel that allows the user to edit a card
     */

    public CardEditPanel()
    {
        // Set up panel attributes
        Border border = BorderFactory.createLineBorder(Color.BLACK);    // Border for card
        this.setBorder(border);
        this.setPreferredSize(new Dimension(1000, 400));
        this.setBackground(Color.WHITE);

    }
}
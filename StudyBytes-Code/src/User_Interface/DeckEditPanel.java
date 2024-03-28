package User_Interface;

import javax.swing.*;
import java.awt.*;

public class DeckEditPanel extends JPanel
{
    /**
     * This class sets up the panel that fills the entire deck edit frame
     */

    GridBagConstraints constraints;  // GridBagConstraints object to specify locations of objects in panel

    public DeckEditPanel()
    {
        this.setBackground(Color.WHITE);

        // Setup layout attributes
        this.setLayout(new GridBagLayout());        // Set layout of panel to GridBagLayout
        constraints = new GridBagConstraints();     // Create GridBagConstraints object

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(50, 25, 25, 25);
        this.add(new CardEditPanel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(25, 25, 25, 25);
        this.add(new CardEditPanel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(25, 25, 25, 25);
        this.add(new CardEditPanel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(25, 25, 25, 25);
        this.add(new CardEditPanel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(25, 25, 50, 25);
        this.add(new CardEditPanel(), constraints);

    }
}
package User_Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FlashcardLabel extends JLabel
{
    /**
     * This class acts as the format for one side of a flashcard, to be used as a label in User_Interface.FlashcardFrame
     */

    public FlashcardLabel()
    {
        Border cardBorder = BorderFactory.createLineBorder(Color.BLACK, 3);    // Border for card

        this.setHorizontalAlignment(CENTER);    // Center text on label
        this.setBorder(cardBorder);             // Add border to card label
        TestData data = new TestData();
        this.setText(data.studySet.get(0).term);


    }

}


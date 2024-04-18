package User_Interface;

import Backend.Deck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchFrame extends JFrame{

    Deck linkedDeck;
    public LaunchFrame(Deck linkedDeck){


        this.setTitle("StudyBytes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(420,420);
        //this.setLayout(null);
        LaunchPanel panel = new LaunchPanel(linkedDeck);
        this.add(panel);
        this.setVisible(true);
    }
}

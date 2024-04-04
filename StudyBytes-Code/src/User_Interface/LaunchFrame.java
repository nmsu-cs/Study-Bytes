package User_Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchFrame extends JFrame implements ActionListener {
    JButton editButton;
    JButton viewButton;

    TestData deck;
    public LaunchFrame(TestData deck){
        this.deck = deck;

        editButton = new JButton();
        editButton.setBounds(200,100,50,50);
        editButton.setText("Edit");
        editButton.addActionListener( this);

        viewButton = new JButton();
        viewButton.setBounds(50,100,50,50);
        viewButton.setText("View");
        viewButton.addActionListener( this);

        this.setTitle("StudyBytes");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(420,420);
        this.setLayout(null);
        this.setVisible(true);
        editButton.addActionListener(this);

        this.add(editButton);
        this.add(viewButton);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == editButton){
            this.dispose();
            DeckEditFrame spawnEdit = new DeckEditFrame(deck);
        }else if(e.getSource() == viewButton){
            this.dispose();
            FlashcardFrame spawnViewFlash = new FlashcardFrame(deck);
        }
    }
}

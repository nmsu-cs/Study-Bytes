import User_Interface.*;
import Backend.*;

import java.util.LinkedList;

public class LaunchProgram
{
    public static void main(String[] args)
    {
        // Create starting deck
        Deck linkedDeck = new Deck("Default Deck");

        Card card1 = new Card("bag", "a container made of flexible material with an opening at the top, used for carrying things");
        Card card2 = new Card("programming", "Computer programming or coding is the composition of sequences of instructions, called programs, that computers can follow to perform tasks. It involves designing and implementing algorithms, step-by-step specifications of procedures, by writing code in one or more programming languages.");

        linkedDeck.addCard(card1);
        linkedDeck.addCard(card2);

        LaunchFrame frame1 = new LaunchFrame(linkedDeck);  // Frame for deck viewer screen
    }
}
import User_Interface.*;
import Backend.*;

import java.util.LinkedList;

public class LaunchProgram
{
    public static void main(String[] args)
    {
        // Create deck
        Deck linkedDeck = new Deck("Default Deck");

        LaunchFrame frame1 = new LaunchFrame(linkedDeck);  // Frame for deck viewer screen
        //DeckEditFrame frame2 = new DeckEditFrame(deck);  // Frame for deck edit screen
    }
}
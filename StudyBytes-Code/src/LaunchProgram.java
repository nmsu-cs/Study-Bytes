import User_Interface.*;

public class LaunchProgram
{
    public static void main(String[] args)
    {
        TestData deck = new TestData();

        LaunchFrame frame1 = new LaunchFrame(deck);  // Frame for deck viewer screen
        //DeckEditFrame frame2 = new DeckEditFrame(deck);  // Frame for deck edit screen
    }
}
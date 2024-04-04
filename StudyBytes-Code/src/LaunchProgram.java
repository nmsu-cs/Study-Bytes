import User_Interface.*;

public class LaunchProgram
{
    public static void main(String[] args)
    {
        TestData deck = new TestData();

//        FlashcardFrame frame1 = new FlashcardFrame();  // Frame for deck viewer screen
        DeckEditFrame frame2 = new DeckEditFrame(deck);  // Frame for deck edit screen
    }
}
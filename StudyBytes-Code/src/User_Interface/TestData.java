package User_Interface;

import java.util.ArrayList;

public class TestData
{
    /**
     * This class is used to create an example study set, with each object being an instance of "User_Interface.TestDataStructure"
     */

    // Array to hold cards (studySet array will be accessed by User_Interface.FlashcardFrame)
    public ArrayList<TestDataStructure> studySet;

    /**
     * Populate studySet with example cards
     */
    public TestData()
    {
        studySet = new ArrayList<>();

        studySet.add(new TestDataStructure("pamphlet", "a small booklet or leaflet containing information or arguments about a single subject"));
        studySet.add(new TestDataStructure("love", "an intense feeling of deep affection"));
        studySet.add(new TestDataStructure("insanity", "the state of being seriously mentally ill; madness"));
        studySet.add(new TestDataStructure("ewe", "a female sheep, especially when fully mature"));
        studySet.add(new TestDataStructure("game", "a form of play or sport, especially a competitive one played according to rules and decided by skill, strength, or luck"));

    }

}



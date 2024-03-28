package User_Interface;

public class TestData
{
    /**
     * This class is used to create an example study set, with each object being an instance of "User_Interface.TestDataStructure"
     */

    // Array to hold cards (studySet array will be accessed by User_Interface.FlashcardFrame)
    public TestDataStructure[] studySet;

    /**
     * Populate studySet with example cards
     */
    public TestData()
    {
        studySet = new TestDataStructure[5];

        studySet[0] = new TestDataStructure("pamphlet", "a small booklet or leaflet containing information or arguments about a single subject");
        studySet[1] = new TestDataStructure("love", "an intense feeling of deep affection");
        studySet[2] = new TestDataStructure("insanity", "the state of being seriously mentally ill; madness");
        studySet[3] = new TestDataStructure("ewe", "a female sheep, especially when fully mature");
        studySet[4] = new TestDataStructure("game", "a form of play or sport, especially a competitive one played according to rules and decided by skill, strength, or luck");

    }

}



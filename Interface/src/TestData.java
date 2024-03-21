public class TestData
{
    /**
     * This class is used to create an example study set, with each object being an instance of "TestDataStructure"
     */

    // Array to hold cards (studySet array will be accessed by FlashcardFrame)
    TestDataStructure[] studySet = new TestDataStructure[4];

    /**
     * Populate studySet with example cards
     */
    public TestData()
    {
        studySet[0] = new TestDataStructure("pamphlet", "a small booklet or leaflet containing information or arguments about a single subject");
        studySet[1] = new TestDataStructure("love", "an intense feeling of deep affection");
        studySet[2] = new TestDataStructure("insanity", "the state of being seriously mentally ill; madness");
        studySet[3] = new TestDataStructure("ewe", "a female sheep, especially when fully mature");

    }




}
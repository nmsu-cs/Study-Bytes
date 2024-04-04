package Backend;

public class Card {

    private String term;
    private String definition;


    public Card(){
        this.term = null;
        this.definition = null;
    }
    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String toString(){
        return "Term: " + term + "\n" + "Definition: " + definition;
    }
}

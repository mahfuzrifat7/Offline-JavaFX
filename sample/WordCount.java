package sample;

import javafx.beans.property.SimpleStringProperty;

public class WordCount {

    private SimpleStringProperty word;
    private int count;

    public WordCount(String word, int count) {
        this.word = new SimpleStringProperty(word);
        this.count = count;
    }

    public String getWord() {
        return word.get();
    }

    public void setWord(SimpleStringProperty word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
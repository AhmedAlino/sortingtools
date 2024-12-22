package sorting;

import org.jetbrains.annotations.NotNull;

public class Word implements Comparable<Word> {
    private int occurrence;
    private String word;

    public Word(int occurrence, String word) {
        this.occurrence = occurrence;
        this.word = word;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(@NotNull Word word) {
        if (Integer.compare(this.occurrence, word.occurrence) == 0) {
            return this.word.compareTo(word.word);
        }
        return Integer.compare(this.occurrence, word.occurrence);
    }
}

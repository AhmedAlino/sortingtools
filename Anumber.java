package sorting;

import org.jetbrains.annotations.NotNull;


public class Anumber implements Comparable<Anumber> {

    public static void main(String... a) {

    }
    private int occurrence;
    private Long number;

    public Anumber(Long number, int occurrence) {
        this.occurrence = occurrence;
        this.number = number;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public Long getNumber() {
        return number;
    }

    @Override
    public int compareTo(@NotNull Anumber number) {
        if (Long.compare(this.occurrence, number.occurrence) == 0) {
            return Long.compare(this.number, number.number);
        }
        return Long.compare(this.occurrence, number.occurrence);
    }
}

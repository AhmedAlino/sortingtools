package sorting;

import org.jetbrains.annotations.NotNull;

public class Line implements Comparable<Line> {
    private int occurrence;
    private String line;

    public Line(int occurrence, String word) {
        this.occurrence = occurrence;
        this.line = word;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public String getLine() {
        return line;
    }

    @Override
    public int compareTo(@NotNull Line line) {
        if (Integer.compare(this.occurrence, line.occurrence) == 0) {
            return this.line.compareTo(line.line);
        }
        return Integer.compare(this.occurrence, line.occurrence);
    }
}

package day04;

public class Line {
    
    private String line;
    private int score;
    private int counter;

    public Line(String line, int score, int counter) {
        this.line = line;
        this.score = score;
        this.counter = counter;
    }

    public String toString() {
        return String.format("line: %s, score: %d, counter: %d", this.line, this.score, this.counter);
    }

    public String getLine() {
        return this.line;
    }

    public int getScore() {
        return this.score;
    }

    public int getCounter() {
        return this.counter;
    }

    public void putString(String line) {
        this.line = line;
    }

    public void putScore(int score) {
        this.score = score;
    }

    public void putCounter(int counter) {
        this.counter = counter;
    }
}

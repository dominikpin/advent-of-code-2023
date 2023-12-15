package day15;

public class Lens {

    private String label;
    private int number;

    public Lens(String label, int number) {
        this.label = label;
        this.number = number;
    }

    public String toString() {
        return String.format("[%s %d]", label, number);
    }

    public String getLabel() {
        return this.label;
    }

    public int getNumber() {
        return this.number;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

package day16;

public class Tile {
    
    private boolean[] isFromLeftRightTopBot = new boolean[4];

    public boolean getIsFromDirection(int direction) {
        return this.isFromLeftRightTopBot[direction];
    }

    public void setIsFromDirection(int direction, boolean trueOrFalse) {
        this.isFromLeftRightTopBot[direction] = trueOrFalse;
    }
}

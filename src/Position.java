public class Position {
    private int row;
    private int col;

    public Position(String locations) {
        row = locations.charAt(0) - 'A';
        col = locations.charAt(1) - '1';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return (char)(row + 'A')+""+(col+1);
    }
}

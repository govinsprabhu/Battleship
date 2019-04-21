import exceptions.InvalidInputException;
import utills.Constants;

public class Player {
    private Board board;
    private int index;
    private String[] targets;

    public String[] getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets.split(" ");
    }

    public Player() {

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isOver(){
        return this.index >= targets.length;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void initiateBoard(String board) throws InvalidInputException {
        this.board = new Board(board);
    }

    public Position getNextPosition(){
        if(isOver()){
            return null;
        }
        return new Position(targets[index++]);
    }
    public boolean placeTheShip(int strength, int width, int length, String locations) throws InvalidInputException {
        int row = locations.charAt(0) - 'A';
        int col = locations.charAt(1) - '1';
        if (length > board.getWidth() || length < 1){
            throw new InvalidInputException(String.format("value of width should be between %d and %d", 1, board.getLength()));
        }

        if (width > board.getWidth() || width < 1){
            throw new InvalidInputException(String.format("value of width should be between %d and %s", 1, (board.getWidth())));
        }

        for (int i = row; i < row + length; i++) {
            for (int j = col; j < col + width; j++) {
                if (board.isShipInPosition(i, j)){
                    System.out.println("Another ship already in the location. Give another location. ");
                    return false;
                }
            }
        }
        for (int i = row; i < row + length; i++) {
            for (int j = col; j < col + width; j++) {
                board.increment(i, j, strength);
            }
        }
        return true;
    }

    public boolean areAllShipsDistroyed(){
        return board.getNumberOfActiveBlocks() == 0;
    }

}

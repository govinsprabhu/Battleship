package pojo;

import exceptions.InvalidInputException;
import utills.Constants;

public class Board {
    private int[][] board;
    private int length;
    private int width;
    private int numberOfActiveBlocks;
    private char lengthAlphabet;

    public Board(String boundary) throws InvalidInputException {
        String[] boundaries = boundary.split(" ");
        lengthAlphabet = boundaries[1].charAt(0);
        length = lengthAlphabet - Constants.CHARACTER_A + 1;
        if (length < 1 || length > 26){
            System.out.println("Invalid boundary. Enter a character between 'A' and 'Z' ");
            throw new InvalidInputException("Invalid boundary. Enter a character between 'A' and 'Z' ");
        }
        width = Integer.parseInt(boundaries[0]);

        if (width < 1 ||  width > 9){
            System.out.println("Invalid boundary. Enter a number between 1 and 9 ");
            throw new InvalidInputException("Invalid boundary. Enter a number between 1 and 9 ");
        }

        this.board =  new int[length + 1][width];
    }

    public void increment(int i, int j, int strength){
        board[i][j] += strength;
        numberOfActiveBlocks += strength;
    }

    public boolean isShipInPosition(Position position){
        return isShipInPosition(position.getRow(), position.getCol());
    }

    public boolean isShipInPosition(int row, int col) {
        return board[row][col] != 0;
    }

    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void decrement(Position position){
        numberOfActiveBlocks--;
        board[position.getRow()][position.getCol()]--;
    }

    public int getNumberOfActiveBlocks() {
        return numberOfActiveBlocks;
    }

    public void setNumberOfActiveBlocks(int numberOfActiveBlocks) {
        this.numberOfActiveBlocks = numberOfActiveBlocks;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int area(){
        return length * width;
    }

    public char getLengthAlphabet() {
        return lengthAlphabet;
    }

    public void setLengthAlphabet(char lengthAlphabet) {
        this.lengthAlphabet = lengthAlphabet;
    }
}

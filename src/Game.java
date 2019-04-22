import exceptions.InvalidInputException;
import pojo.Player;
import pojo.Position;
import utills.Constants;
import enums.ShipType;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    public Game() {
        player1 = new Player();
        player2 = new Player();
    }

    public static void main(String[] args) throws InvalidInputException {
        Game game = new Game();
        game.startGame();
    }

    public void startGame() throws InvalidInputException {
        readInput();
        //can uncomment the below line to see the ship position in board for player 1 and player 2
        //printBoard();
        play();
        //printBoard();
        printResult();
    }

    public void printResult() {
        if (player1.areAllShipsDistroyed()) {
            System.out.println("Player-2 won the battle");
        } else if (player2.areAllShipsDistroyed()) {
            System.out.println("Player-1 won the battle");
        } else {
            System.out.println("Both players has their ships. Declaring peace");
        }
    }

    public void play() {
        boolean isPlayer1 = true;
        while (!player1.isOver() || !player2.isOver()) {
            if (isPlayer1) {
                if (player1.isOver()) {
                    System.out.println("Player 1 has no more missiles left");
                    isPlayer1 = !isPlayer1;
                    continue;
                }
                Position position = player1.getNextPosition();
                String hitOrMiss;
                if (!player2.getBoard().isShipInPosition(position)) {
                    hitOrMiss = Constants.MISSED;
                    isPlayer1 = !isPlayer1;
                } else {
                    hitOrMiss = Constants.HIT;
                    player2.getBoard().decrement(position);
                }
                System.out.println(String.format("Player-1 fires a missile with target %s which %s", position, hitOrMiss));
            } else if (!player2.isOver()) {
                String hitOrMiss;
                if (player2.isOver()) {
                    System.out.println("Player 2 has no more missiles left");
                    isPlayer1 = !isPlayer1;
                    continue;
                }
                Position position = player2.getNextPosition();
                if (!player1.getBoard().isShipInPosition(position)) {
                    hitOrMiss = Constants.MISSED;
                    isPlayer1 = !isPlayer1;
                } else {
                    hitOrMiss = Constants.HIT;
                    player1.getBoard().decrement(position);
                }
                System.out.println(String.format("Player-2 fires a missile with target %s which %s", position, hitOrMiss));
            }
        }
    }

    public void printBoard() {
        System.out.println("Player 1 ship positions");
        player1.getBoard().printBoard();
        System.out.println("Player 2 ship positions");
        player2.getBoard().printBoard();
    }

    public void readInput() throws InvalidInputException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.print("Enter area boundaries:");
        String boundary = scanner.nextLine();
        player1.initiateBoard(boundary);
        player2.initiateBoard(boundary);
        System.out.print("\nNumber of battleships:");
        int numberOfBattleShips = Integer.parseInt(scanner.nextLine());
        System.out.println(numberOfBattleShips);
        if (numberOfBattleShips < 1 || numberOfBattleShips  > player1.getBoard().area()){
            throw new InvalidInputException(String.format("Number of battleship should be between %d and %d", 1, player1.getBoard().area()));
        }
        for (int i = 0; i < numberOfBattleShips; i++) {
            createBattleShip(scanner, i);
        }
        System.out.print("\nMissile targets for player A:");
        player1.setTargets(scanner.nextLine());
        System.out.print("\nMissile targets for player B:");
        player2.setTargets(scanner.nextLine());
    }

    private void createBattleShip(Scanner scanner, int i) throws InvalidInputException {
        System.out.print(String.format("\nType for battleship %d:", i + 1));
        String battleShipType = scanner.nextLine().trim();
        ShipType shipType = ShipType.getShipType(battleShipType);
        if (shipType == null){
                throw new InvalidInputException("'P' and 'Q' are the only valid input parameters");
        }
        int strength = getStrength(shipType);
        System.out.print(String.format("\nDimension for battleship %d:", i + 1));
        String[] dimensions = scanner.nextLine().split(" ");
        int length = Integer.parseInt(dimensions[0]);
        int width = Integer.parseInt(dimensions[1]);
        placeTheShipForPlayer1(scanner, i, strength, length, width);
        placeTheShipForPlayer2(scanner, i, strength, length, width);
    }

    private void placeTheShipForPlayer1(Scanner scanner, int i, int strength, int length, int width) throws InvalidInputException {
        String locations = "";
        do {
            System.out.print(String.format("\nLocation of battleship %d for player A:", i + 1));
            locations = scanner.nextLine();
        }while (!player1.placeTheShip(strength, length, width, locations));
    }

    private void placeTheShipForPlayer2(Scanner scanner, int i, int strength, int length, int width) throws InvalidInputException {
        String locations = "";
        do {
            System.out.print(String.format("\nLocation of battleship %d for player B:", i + 1));
            locations = scanner.nextLine();
        }while (!player2.placeTheShip(strength, length, width, locations));
    }

    public int getStrength(ShipType shipType) {
        return shipType == ShipType.P ? 1 : 2;
    }


}

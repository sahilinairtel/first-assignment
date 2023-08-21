import java.util.HashMap;
import java.util.Map;

class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

class Board {
    private static final int SIZE = 100;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public Board() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        // Initialize snakes and ladders positions
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(20, 38);
    }

    public int getNewPosition(int currentPosition, int diceValue) {
        int newPosition = currentPosition + diceValue;
        if (newPosition > SIZE) {
            return currentPosition;
        }
        // Check for snake or ladder
        if (snakes.containsKey(newPosition)) {
            newPosition = snakes.get(newPosition);
        } else if (ladders.containsKey(newPosition)) {
            newPosition = ladders.get(newPosition);
        }
        return newPosition;
    }
}

class Dice {
    public int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}

class SnakeAndLadderGame {
    private Board board;
    private Player player1;
    private Player player2;
    private Dice dice;

    public SnakeAndLadderGame(String player1Name, String player2Name) {
        board = new Board();
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        dice = new Dice();
    }

    public void play() {
        Player currentPlayer = player1;
        while (currentPlayer.getPosition() < Board.SIZE) {
            int diceValue = dice.roll();
            int newPosition = board.getNewPosition(currentPlayer.getPosition(), diceValue);
            currentPlayer.setPosition(newPosition);

            System.out.println(currentPlayer.getName() + " rolled a " + diceValue +
                    " and moved to position " + newPosition);

            if (newPosition == Board.SIZE) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            // Switch player
            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SnakeAndLadderGame game = new SnakeAndLadderGame("Player1", "Player2");
        game.play();
    }
}
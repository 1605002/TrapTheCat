package server;

import game.Turn;
import game.Cell;
import game.player.PlayerInfo;
import util.NetworkUtil;

import java.io.FileWriter;
import java.util.Random;

public class Game implements Runnable {
    public static final int NUMBER_OF_BLOCKED_CELL = 7;
    private NetworkUtil trapper;
    private NetworkUtil cat;
    private PlayerInfo trapperInfo;
    private PlayerInfo catInfo;

    public Game(NetworkUtil trapper, NetworkUtil cat, PlayerInfo trapperInfo, PlayerInfo catInfo) {
        this.trapper = trapper;
        this.cat = cat;
        this.trapperInfo = trapperInfo;
        this.catInfo = catInfo;

        new Thread(this).start();
    }

    public void run() {

        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_BLOCKED_CELL; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = new Cell(x, y);
            trapper.write(cell);
            cat.write(cell);
            System.out.println(cell.getX() + " - " + cell.getY() + " blocked");
        }


        Turn turn = new Turn();

        int moveCount = 0;

        while (true) {
            Cell cell;
            if (turn.getTurn()==0) {
                cell = (Cell) trapper.read();
                cat.write(cell);

                System.out.println(trapperInfo.getName() + " blocked (" + cell.getX() + "," + cell.getY() + ")");
            }
            else {
                cell = (Cell) cat.read();
                trapper.write(cell);

                System.out.println(catInfo.getName() + " moved to (" + cell.getX() + "," + cell.getY() + ")");
            }

            if (cell.getX()==-1 && cell.getY()==-1) {
                if (turn.getTurn()==1) System.out.println("Trapper has won!");
                else System.out.println("Cat has won!");
                System.out.println("Game finished!");
                saveScoreToFile(turn.getTurn()^1, moveCount);
                break;
            }

            moveCount++;
            turn.toggle();
        }
    }

    private void saveScoreToFile(int winnerType, int moveCount) {
        // do something
    }
}

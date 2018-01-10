package server;

import game.Turn;
import game.Cell;
import util.NetworkUtil;

import java.util.Random;

public class Game implements Runnable {
    NetworkUtil trapper;
    NetworkUtil cat;

    public Game(NetworkUtil trapper, NetworkUtil cat) {
        this.cat = cat;
        this.trapper = trapper;
        new Thread(this).start();
    }

    public void run() {

        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = new Cell(x, y);
            trapper.write(cell);
            cat.write(cell);
        }


        Turn turn = new Turn();

        while (true) {
            Cell cell;
            if (turn.getTurn()==0) {
                cell = (Cell) trapper.read();
                cat.write(cell);
            }
            else {
                cell = (Cell) cat.read();
                trapper.write(cell);
            }

            turn.toggle();
        }
    }
}

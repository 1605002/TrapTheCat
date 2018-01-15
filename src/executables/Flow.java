package executables;

import game.Cat;
import game.Player;
import game.Trapper;
import game.Turn;

public class Flow implements Runnable {
    Player trapper, cat;
    Turn turn;

    public Flow(Trapper trapper, Cat cat, Turn turn) {
        this.trapper = trapper;
        this.cat = cat;
        this.turn = turn;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if (turn.getTurn()==0) trapper.makeMove();
            else cat.makeMove();
            turn.toggle();
        }
    }
}

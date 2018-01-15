package executables;

import game.Cat;
import game.Player;
import game.Trapper;
import game.Turn;

public class Flow implements Runnable {
    Player trapper, cat;

    public Flow(Trapper trapper, Cat cat) {
        this.trapper = trapper;
        this.cat = cat;
        new Thread(this).start();
    }

    @Override
    public void run() {
        Turn turn = new Turn();

        while (true) {
            if (turn.getTurn()==0) trapper.makeMove();
            else cat.makeMove();
        }
    }
}

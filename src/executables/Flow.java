package executables;

import game.*;

public class Flow implements Runnable {
    private Player trapper, cat;
    private Turn turn;
    private static boolean finished;

    public Flow(Trapper trapper, Cat cat, Turn turn) {
        this.trapper = trapper;
        this.cat = cat;
        this.turn = turn;
        finished = false;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if (turn.getTurn()==0) {
                trapper.makeMove();
                if (trapper.hasWon()) {
                    cat.writeToServer(new Cell(-1, -1));
                    break;
                }
            }
            else {
                cat.makeMove();
                if (cat.hasWon()) {
                    trapper.writeToServer(new Cell(-1, -1));
                    break;
                }
            }
            turn.toggle();
        }

        finished = true;
        if (turn.getTurn()==0) System.out.println("Trapper has won!");
        else System.out.println("Cat has won!");
    }

    public static boolean isFinished() {
        return finished;
    }
}

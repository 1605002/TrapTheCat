package game;

import util.NetworkUtil;

public class Trapper implements Player {
    Grid grid;
    NetworkUtil server;
    boolean isHuman;

    public Trapper(Grid grid, NetworkUtil server) {
        this.grid = grid;
        this.server = server;
        isHuman = false;
    }

    public Trapper(Grid grid) {
        this.grid = grid;
        isHuman = true;
    }

    public Cell makeMove() {
        Cell cell;
        if (isHuman) {
            cell = (Cell) server.read();
        } else {
            cell = moveByAI();
        }
    }
    public Cell moveByAI() {

    }
}

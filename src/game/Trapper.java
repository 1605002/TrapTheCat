package game;

import util.NetworkUtil;

public class Trapper extends Player {
    private boolean isHuman = false;

    public Trapper(Grid grid, NetworkUtil server) {
        super(grid, server);
    }

    public Trapper(Grid grid) {
        super(grid);
    }

    public Cell makeMove() {
        return super.readFromServer();
    }
}

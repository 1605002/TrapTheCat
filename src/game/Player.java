package game;

import util.NetworkUtil;

public abstract class Player {
    private Grid grid;
    private NetworkUtil server;

    public Player(Grid grid, NetworkUtil server) {
        this.grid = grid;
        this.server = server;
    }

    public Player(Grid grid) {
        this.grid = grid;
    }

    abstract public Cell makeMove();

    public Cell readFromServer() {
        return (Cell) server.read();
    }
}

package game.player;

import game.Cell;
import game.Grid;
import util.NetworkUtil;

public abstract class Player {
    protected Grid grid;
    private NetworkUtil server;

    public Player(Grid grid, NetworkUtil server) {
        this.grid = grid;
        this.server = server;
    }

    abstract public void makeMove();
    abstract public boolean hasWon();

    public Cell readFromServer() {
        return (Cell) server.read();
    }

    public void writeToServer(Cell cell) {
        server.write(cell);
    }
}

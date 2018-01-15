package game;

import util.NetworkUtil;

public abstract class Player {
    protected Grid grid;
    private NetworkUtil server;

    public Player(Grid grid, NetworkUtil server) {
        this.grid = grid;
        this.server = server;
    }

    abstract public void makeMove();

    public Cell readFromServer() {
        return (Cell) server.read();
    }

    public void writeToServer(Cell cell) {
        System.out.println("Trying to write " + cell.getX() + " , " + cell.getY());
        server.write(cell);
    }
}

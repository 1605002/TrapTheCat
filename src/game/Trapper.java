package game;

import util.NetworkUtil;

public class Trapper extends Player {
    private boolean isHuman;

    public Trapper(Grid grid, NetworkUtil server) {
        super(grid, server);
        isHuman = false;
    }

    public Trapper(Grid grid) {
        super(grid);
        isHuman = true;
    }

    public Cell makeMove() {
        Cell cell;
        if (isHuman==false) {
            cell = (Cell) super.readFromServer();
        } else {
            cell = moveByAI();
        }

        return cell;
    }

    public Cell moveByAI() {
        return null;
    }
}

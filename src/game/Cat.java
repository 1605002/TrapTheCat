package game;

import util.NetworkUtil;

public class Cat extends Player {
    private boolean isHuman;

    public Cat(Grid grid, NetworkUtil server) {
        super(grid, server);
        isHuman = false;
    }

    public Cat(Grid grid) {
        super(grid);
        isHuman = true;
    }

    public Cell makeMove() {
        Cell cell;
        if (isHuman) {
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

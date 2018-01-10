package game;

import util.NetworkUtil;

import static game.Britto.getClickedCell;

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

    public void makeMove() {
        Cell cell;
        if (isHuman==false) {
            cell = (Cell) super.readFromServer();
        } else {
            while (true) {
                cell = Britto.getClickedCell();
                if (cell != null) {
                    Britto.setClickedCell(null);
                    break;
                }
            }
            super.writeToServer(cell);
        }

        grid.moveTo(cell.getX(), cell.getY());
    }

    public Cell moveByAI() {
        return null;
    }
}

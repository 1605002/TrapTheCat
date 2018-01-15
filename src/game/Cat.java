package game;

import util.NetworkUtil;

import static game.Britto.getClickedCell;

public class Cat extends Player {
    private boolean isHuman;

    public Cat(Grid grid, NetworkUtil server, boolean isHuman) {
        super(grid, server);
        this.isHuman = isHuman;
    }

    public void makeMove() {
        Cell cell;
        if (isHuman==false) {
            cell = (Cell) super.readFromServer();
        } else {
            while (true) {
                if (Britto.getClickedCell() != null) {
                    cell = new Cell(Britto.getClickedCell().getX(), Britto.getClickedCell().getY());
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

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
        System.out.println("Blocked " + cell.getX() + " , " + cell.getY());

        grid.block(cell.getX(), cell.getY());
    }

    public Cell moveByAI() {
        return null;
    }
}

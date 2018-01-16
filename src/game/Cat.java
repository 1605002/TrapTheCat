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
        System.out.println("Cat moved to (" + cell.getX() + "," + cell.getY() + ")");

        grid.moveTo(cell.getX(), cell.getY());
    }

    public Cell moveByAI() {
        return null;
    }

    public boolean hasWon() {
        Cell catPosition = grid.getCatPosition();

        if (catPosition.getX()==0 || catPosition.getY() == 0 ||
                catPosition.getX()== 10 || catPosition.getY() == 10) return true;

        return false;
    }
}
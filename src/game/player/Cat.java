package game.player;

import game.Britto;
import sendable.Cell;
import game.Grid;
import util.NetworkUtil;

import java.util.Random;

public class Cat extends Player {
    private boolean isHuman;

    public Cat(Grid grid, NetworkUtil server, String name, boolean isHuman) {
        super(grid, server, name);
        this.isHuman = isHuman;
    }

    public void makeMove() {
        Cell cell;
        if (isHuman==false) {
            if (server==null) cell = moveByAI();
            else cell = (Cell) super.readFromServer();
        } else {
            while (true) {
                if (Britto.getClickedCell() != null) {
                    cell = new Cell(Britto.getClickedCell().getX(), Britto.getClickedCell().getY());
                    Britto.setClickedCell(null);
                    break;
                }
            }
            if (server!=null) super.writeToServer(cell);
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
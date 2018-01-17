package game.player;

import game.*;
import game.Britto;
import util.NetworkUtil;

public class Trapper extends Player {
    private boolean isHuman;

    public Trapper(Grid grid, NetworkUtil server, boolean isHuman) {
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
        System.out.println("Trapper blocked (" + cell.getX() + "," + cell.getY() + ")");

        grid.block(cell.getX(), cell.getY());
    }

    public Cell moveByAI() {
        return null;
    }

    public boolean hasWon() {
        Cell catPosition = grid.getCatPosition();

        if (catPosition.getX()%2==0) {
            for (int i = 0; i < 6; i++) {
                if (!grid.getStausOfBritto(catPosition.getX()+Britto.dxEven[i],
                        catPosition.getY()+Britto.dyEven[i])) return false;
            }
        } else {
            for (int i = 0; i < 6; i++) {
                if (!grid.getStausOfBritto(catPosition.getX()+Britto.dxOdd[i],
                        catPosition.getY()+Britto.dyOdd[i])) return false;
            }
        }

        return true;
    }
}

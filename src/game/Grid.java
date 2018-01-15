package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Scanner;

public class Grid {

    static final Paint BLOCKED = Color.DARKGREEN;
    static final Paint OPEN = Color.LIGHTGREEN;
    static final Paint BILAIASE = Color.RED;

    private Scene scene;
    private Britto[][] brittos;
    private Cell catPosition;
    private Group root;
    private Turn turn;

    private int player;

    public Grid(Scene scene, Group root, Turn turn, int player) {

        this.turn = turn;
        this.player = player;
        this.scene = scene;
        this.root = root;

        brittos = new Britto[11][11];

        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                Circle circle = new Circle();
                brittos[i][j] = new Britto(this.scene, circle, i, j, false, turn, player, catPosition);
                this.root.getChildren().add(brittos[i][j].getCircle());
            }
        }

        catPosition = new Cell(5, 5);
        moveTo(5, 5);
    }

    public void block(int x, int y) {
        brittos[x][y].setStatus(true);
        brittos[x][y].getCircle().setFill(BLOCKED);
    }

    public void moveTo(int x, int y) {

        int oldX = catPosition.getX();
        int oldY = catPosition.getY();
        brittos[oldX][oldY].getCircle().setFill(OPEN);
        brittos[oldX][oldY].setStatus(false);
        catPosition = new Cell(x, y);
        brittos[x][y].setStatus(true);
        brittos[x][y].getCircle().setFill(BILAIASE);
    }

}

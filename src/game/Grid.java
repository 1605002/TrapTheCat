package game;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sendable.Cell;

public class Grid {

    static final Paint BLOCKED = Color.DARKGREEN;
    static final Paint OPEN = Color.LIGHTGREEN;
    static final Paint BILAIASE = Color.RED;

    private ImageView catImage;
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
        catImage = new ImageView(new Image(getClass().getResourceAsStream("simonscat.png")));
        catImage.setPreserveRatio(true);

        brittos = new Britto[11][11];

        catPosition = new Cell(5, 5);

        for(int i = 0; i < 11; i++) {
            for(int j = 0; j < 11; j++) {
                Circle circle = new Circle();
                brittos[i][j] = new Britto(this.scene, circle, i, j, false, turn, player, catPosition);
                this.root.getChildren().add(brittos[i][j].getCircle());
            }
        }

        catImage.setX(brittos[5][5].getCircle().getCenterX()-55);
        catImage.setY(brittos[5][5].getCircle().getCenterY()-55);

        this.root.getChildren().add(catImage);

        //moveTo(5, 5);
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
        catPosition.setX(x);
        catPosition.setY(y);
        brittos[x][y].setStatus(true);
        brittos[x][y].getCircle().setFill(BILAIASE);

        TranslateTransition trtl = new TranslateTransition();
        trtl.setByX(brittos[x][y].getCircle().getCenterX()-brittos[oldX][oldY].getCircle().getCenterX());
        trtl.setByY(brittos[x][y].getCircle().getCenterY()-brittos[oldX][oldY].getCircle().getCenterY());
        trtl.setDuration(Duration.millis(300));
        trtl.setCycleCount(1);
        trtl.setNode(catImage);
        trtl.play();

    }

    public Cell getCatPosition() {
        return catPosition;
    }

    public boolean getStausOfBritto(int i, int j) {
        return brittos[i][j].isStatus();
    }
}

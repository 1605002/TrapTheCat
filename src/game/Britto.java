package game;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Britto {

    private Scene scene;

    public Circle getCircle() {
        return circle;
    }

    private Circle circle;
    private int row;

    public void setStatus(boolean status) {
        this.status = status;
    }

    private int column;

    public boolean isStatus() {
        return status;
    }

    private boolean status = false;
    private Turn turn;


    public Britto() {

    }

    public Britto(Scene scene, Circle circle, int row, int column, boolean status, Turn turn) {

        circle.setCenterX(50+40*row+20*(column%2));
        circle.setCenterY(50+40*column);
        circle.setRadius(19);
        circle.setFill(Color.rgb(202, 240, 70));
        this.turn = turn;

        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStatus(true);
                circle.setFill(Color.DARKGREEN);
            }
        });

        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isStatus() == false)
                    scene.setCursor(Cursor.HAND);
            }
        });

        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });

        this.scene = scene;
        this.circle = circle;
        this.row = row;
        this.column = column;
        this.status = status;
    }
}

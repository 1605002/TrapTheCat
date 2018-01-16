package executables;

import game.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Flow implements Runnable {
    private Player trapper, cat;
    private Turn turn;
    private static boolean finished;
    private String message;
    private Group root;
    private Thread thr;

    public Flow(Trapper trapper, Cat cat, Turn turn, Group root) {
        this.trapper = trapper;
        this.cat = cat;
        this.turn = turn;
        this.root = root;

        finished = false;

        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        while (true) {
            if (turn.getTurn()==0) {
                trapper.makeMove();
                if (trapper.hasWon()) {
                    cat.writeToServer(new Cell(-1, -1));
                    break;
                }
            }
            else {
                cat.makeMove();
                if (cat.hasWon()) {
                    trapper.writeToServer(new Cell(-1, -1));
                    break;
                }
            }
            turn.toggle();
        }

        finished = true;

        if (turn.getTurn()==0) {
            message = "Trapper has won!";
            System.out.println(message);
        }
        else {
            message = "Cat has won!";
            System.out.println(message);
        }

        showWinningStatus(message, root);
    }

    public static boolean isFinished() {
        return finished;
    }

    private void showWinningStatus(String msg, Group root) {
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
              Rectangle rectangle = new Rectangle(0, 0, 500, 500);
              rectangle.setFill(Color.WHITE);
              rectangle.setOpacity(0.5);
              root.getChildren().add(rectangle);

              Label label = new Label(msg);
              label.setFont(Font.font("ubuntu", FontWeight.EXTRA_BOLD, 56));
              label.setAlignment(Pos.CENTER);

              label.setTranslateX(150);
              label.setTranslateY(200);

              Pane stackPane = new StackPane();
              stackPane.getChildren().add(label);

              root.getChildren().add(stackPane);
          }
      });
    }
}

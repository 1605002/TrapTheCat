package executables;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScene {

    Stage stage;
    @FXML
    private Pane pane;

    public void mouseClicked(MouseEvent e) {

        stage = (Stage) ((Label) e.getSource()).getScene().getWindow();
        String text = ((Label) e.getSource()).getText();
        if(text.equals("Multiplayer")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("MultiplayerScene.fxml"));
                stage.setScene(new Scene(root, 600, 600));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        else if(text.equals("Exit")) gotoExit();
        else if(text.equals("About")) gotoAboutScene();
        //if(text.equals("About")) gotoAbout(stage);
        //else if(text.equals("Exit")) gotoExit();

    }

    public void mouseEntered(MouseEvent e) {
        Scene scene = ((Label) e.getSource()).getScene();
        scene.setCursor(Cursor.HAND);
        Label label = (Label) e.getSource();
        label.setTextFill(Color.DARKGREEN);
    }

    public void mouseExited(MouseEvent e) {
        Scene scene = ((Label) e.getSource()).getScene();
        scene.setCursor(Cursor.DEFAULT);
        Label label = (Label) e.getSource();
        label.setTextFill(Color.BLACK);
    }

    public void gotoExit() {
        System.exit(0);
    }

    public void gotoAboutScene() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AboutScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(getClass().getResource("AboutScene.css").toExternalForm());
        stage.setScene(scene);
    }
}

package executables;

import game.*;
import game.player.Cat;
import game.player.Trapper;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.util.Scanner;


public class Main extends Application {
    private Stage window;
    private int option;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Trap The Cat");
        Image image = new Image(getClass().getResourceAsStream("icon1.png"));
        window.getIcons().add(image);
        showStartScene();
    }

    protected void showStartScene() {
        // do stuff
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioTrapper = new RadioButton("Trapper");
        RadioButton radioCat = new RadioButton("Cat");
        radioCat.setToggleGroup(toggleGroup);
        radioTrapper.setToggleGroup(toggleGroup);

        radioCat.setOnMouseClicked(event -> selectCat());
        radioTrapper.setOnMouseClicked(event -> selectTrapper());

        VBox vBox = new VBox(10);

        vBox.getChildren().add(radioCat);
        vBox.getChildren().add(radioTrapper);

        Button button = new Button("Enter game");
        button.setOnAction(event -> {
            if (option== 0 || option == 1) showGameScene();
        });

        vBox.getChildren().add(button);

        vBox.setTranslateX(200);
        vBox.setTranslateY(200);


        root.getChildren().add(vBox);
        window.setScene(scene);
        window.show();
    }

    private void selectCat() {
        option = 1;
    }

    private void selectTrapper() {
        option = 0;
    }

    private void showServerScene() {
        showGameScene();
    }

    private void showGameScene() {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Choose Player (1 for Cat & 0 for Trapper): ");
        //int option = scanner.nextInt();

        NetworkUtil nc = new NetworkUtil("192.168.0.104", 44444);
        Turn turn = new Turn();
        Grid grid = new Grid(scene, root, turn, option);

        Trapper trapper;
        Cat cat;

        if (option==0) {
            trapper = new Trapper(grid, nc, true);
            cat = new Cat(grid, nc, false);
            window.setTitle("Trap The Cat - Trapper");
        } else {
            trapper = new Trapper(grid, nc, false);
            cat = new Cat(grid, nc, true);
            window.setTitle("Trap The Cat - Cat");
        }

        nc.write(new Cell(option, -1));

        for (int i = 0; i < 7; i++) {
            Cell cell = (Cell) nc.read();
            if (cell.getX()==5 && cell.getY()==5) continue;
            grid.block(cell.getX(), cell.getY());
            //System.out.println(cell.getX() + " - " + cell.getY() + " blocked");
        }


        Flow flow = new Flow(trapper, cat, turn, scene, root, this);

        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
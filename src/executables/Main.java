package executables;

import game.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.util.Scanner;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose Player (1 for Cat & 0 for Trapper): ");
        int option = scanner.nextInt();

        NetworkUtil nc = new NetworkUtil("127.0.0.1", 44444);
        Turn turn = new Turn();
        Grid grid = new Grid(scene, root, turn, option);

        Trapper trapper;
        Cat cat;

        if (option==0) {
            trapper = new Trapper(grid);
            cat = new Cat(grid, nc);
            primaryStage.setTitle("Trap The Cat - Trapper");
        } else {
            trapper = new Trapper(grid, nc);
            cat = new Cat(grid);
            primaryStage.setTitle("Trap The Cat - Cat");
        }

        nc.write(new Cell(option, -1));

        for (int i = 0; i < 7; i++) {
            Cell cell = (Cell) nc.read();
            if (cell.getX()==5 && cell.getY()==5) continue;
            grid.block(cell.getX(), cell.getY());
            System.out.println(cell.getX() + " - " + cell.getY() + " blocked");
        }

        new Flow(trapper, cat, turn);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

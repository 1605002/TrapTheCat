package executables;

import baksho.ConfirmBox;
import game.player.Player;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sendable.PlayerInfo;
import sendable.RequestType;
import util.NetworkUtil;

public class CreateGameScene {
    private PlayerInfo selfInfo;
    private NetworkUtil server;

    public CreateGameScene(PlayerInfo selfInfo, NetworkUtil server) {
        this.selfInfo = selfInfo;
        this.server = server;

        if (selfInfo.getPlayerType()==0) Main.window.setTitle("Trap The Cat - Trapper");
        else Main.window.setTitle("Trap The Cat - Cat");

        server.write(new RequestType(RequestType.CREATE_GAME));
        server.write(selfInfo);

        System.out.println(selfInfo.getName() + " created");

        Pane pane = new Pane();

        Rectangle rectangle = new Rectangle(150, 200, 300, 200);
        rectangle.setFill(Color.BLACK);

        Label label = new Label("WAITING");
        //label.setFont();
        label.setTranslateY(290);
        label.setPrefWidth(600);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("ubuntu", FontWeight.BOLD, 24));
        label.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(rectangle, label);

        Scene scene = new Scene(pane, 600, 600);

        Main.window.setScene(scene);

        System.out.println(selfInfo.getName());
        System.out.println(selfInfo.getPlayerType());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PlayerInfo othersInfo;

                while (true) {
                    othersInfo = (PlayerInfo) server.read();
                    Boolean result = ConfirmBox.display("Confirmation",
                                        othersInfo.getName() + " wants to play with you.\n" +
                                                "Do you want to play?");

                    if (result) {
                        server.write(new RequestType(RequestType.REQUEST_ACCEPTED));
                        break;
                    } else {
                        server.write(new RequestType(RequestType.REQUEST_DENIED));
                    }
                }

                new GameScene(selfInfo, othersInfo, server);
            }
        };
    }
}

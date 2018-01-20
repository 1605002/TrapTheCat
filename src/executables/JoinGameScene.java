package executables;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import sendable.PlayerInfo;
import util.NetworkUtil;

public class JoinGameScene {
    private PlayerInfo selfInfo;
    private NetworkUtil server;

    public JoinGameScene(PlayerInfo selfInfo, NetworkUtil server) {
        this.selfInfo = selfInfo;
        this.server = server;

        Pane pane = new Pane();


    }
}

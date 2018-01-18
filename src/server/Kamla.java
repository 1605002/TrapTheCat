package server;

import game.RequestType;
import game.player.PlayerInfo;
import util.NetworkUtil;

import java.util.ArrayList;

public class Kamla implements Runnable {
    private ArrayList<PlayerInfo> adminsList;
    private ArrayList<NetworkUtil> adminsNc;
    private PlayerInfo selfInfo;
    private NetworkUtil kamla;
    private Thread thr;

    public Kamla(ArrayList<PlayerInfo> adminsList,
                 ArrayList<NetworkUtil> adminsNc,
                 PlayerInfo selfInfo,
                 NetworkUtil kamla) {
        this.adminsList = adminsList;
        this.adminsNc = adminsNc;
        this.selfInfo = selfInfo;
        this.kamla = kamla;

        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        while (true) {
            Integer idx = (Integer) kamla.read();

            if (adminsList.get(idx).isAvailable()==false) {
                kamla.write(RequestType.REQUEST_DENIED);
            } else {
                adminsList.get(idx).setAvailable(false);

                NetworkUtil admin = adminsNc.get(idx);
                admin.write(selfInfo);
                RequestType answer = (RequestType) admin.read();

                kamla.write(answer);

                if (answer.getType()==RequestType.REQUEST_ACCEPTED) {
                    if (selfInfo.getPlayerType()==0) {
                        // This kamla is Trapper
                        // So the admin is Cat
                        new Game(kamla, admin, selfInfo, adminsList.get(idx));
                    } else {
                        // This kamla is Cat
                        // So the admin is Trapper
                        new Game(admin, kamla, adminsList.get(idx), selfInfo);
                    }
                    break;
                }

                adminsList.get(idx).setAvailable(true);
            }
        }
    }
}

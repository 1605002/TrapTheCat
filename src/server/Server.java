package server;

import sendable.RequestType;
import sendable.PlayerInfo;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {

        ArrayList<PlayerInfo> adminsList = new ArrayList<>();
        ArrayList<NetworkUtil> adminsNc = new ArrayList<>();

        try {
            ServerSocket ss = new ServerSocket(44444);

            while (true) {
                Socket cs = ss.accept();
                NetworkUtil nc = new NetworkUtil(cs);

                RequestType requestType = (RequestType) nc.read();

                if (requestType.getType()==RequestType.SHOW_HIGH_SCORE) {
                    showHighScore(nc);
                } else if (requestType.getType()==RequestType.CREATE_GAME) {
                    PlayerInfo playerInfo = (PlayerInfo) nc.read();
                    adminsList.add(playerInfo);
                    adminsNc.add(nc);
                } else if (requestType.getType()==RequestType.JOIN_GAME) {
                    PlayerInfo playerInfo = (PlayerInfo) nc.read();
                    new Kamla(adminsList, adminsNc, playerInfo, nc);
                }
            }
        } catch (IOException e) {
            System.out.println("Server status: " + e);
        }
    }

    private static void showHighScore(NetworkUtil nc) {
        // do it
    }
}

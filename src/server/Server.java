package server;

import game.Cell;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Server {

    public static void main(String[] args) {
        Queue<NetworkUtil> trappers = new LinkedList<>();
        Queue<NetworkUtil> cats = new LinkedList<>();

        try {
            ServerSocket ss = new ServerSocket(44444);

            while (true) {
                Socket cs = ss.accept();
                NetworkUtil nc = new NetworkUtil(cs);

                Cell information = (Cell) nc.read();

                if (information.getX()==0) trappers.add(nc);
                else cats.add(nc);

                if (!trappers.isEmpty() && !cats.isEmpty()) {
                    new Game(trappers.peek(), cats.peek());
                    trappers.remove();
                    cats.remove();
                }
            }
        } catch (IOException e) {
            System.out.println("Server status: " + e);
        }
    }
}

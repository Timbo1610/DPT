package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by timme on 03.05.2017.
 */
public class TCPServer  implements Runnable {

    private  int SERVERPORT = 6789;
    private  ServerSocket serverSocket;
    private  Thread listenderThread;

    private  Socket client = null;
    private  Listener clientListener;
    private  ArrayList <Listener> clientList = new ArrayList();
    private RequestManager reqManager;
    private RouteManager routeManager;
    private Boolean running = true;

    protected TCPServer(RequestManager reqManager, RouteManager routeManager )

    {
        this.reqManager = reqManager;
        this.routeManager = routeManager;
    }

    private  void openServerSocket() {

        Socket client = null;
        Listener clientListener;


        try {
            serverSocket = new ServerSocket(SERVERPORT);
            System.out.println("Socket opened on port " + SERVERPORT);


            while(running) {
                addClient(serverSocket.accept());
            }
        } catch (IOException e) {

        }
    }

    private  void addClient(Socket client)
    {

        Listener tempListener = new Listener(client,reqManager,routeManager);
        Thread tempThread = new Thread(tempListener);
        tempThread.start();

        clientList.add(tempListener);
    }

    public  void closeServerSocket() {
        running = false;
        try {
            serverSocket.close();
            System.out.println("Socket closed");
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    @Override
    public void run() {
        openServerSocket();
    }



}

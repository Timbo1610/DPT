package control;

import model.Listener;

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

    protected TCPServer(RequestManager reqManager)
    {
        this.reqManager = reqManager;
    }

    private  void openServerSocket() {

        Socket client = null;
        Listener clientListener;

        try {
            serverSocket = new ServerSocket(SERVERPORT);
            System.out.println("Socket opened on port " + SERVERPORT);


            while(true) {
                addClient(serverSocket.accept());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + SERVERPORT, e);
        }
    }

    private  void addClient(Socket client)
    {
        Listener tempListener = new Listener(client,reqManager);
        Thread tempThread = new Thread(tempListener);
        tempThread.start();

        clientList.add(tempListener);
    }

    public  void closeServerSocket() {
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

package control;

import model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main
{

    private static ArrayList <Request> requestList;
    private static TCPServer tcpServer;
    private static RequestManager reqManger;
    private static Thread serverThread;

    public static void main(String [] args)
    {
        SQLConnection SQLCon =  new SQLConnection();

        //SQLCon.setHost("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11170108?zeroDateTimeBehavior=convertToNull");
        //SQLCon.setUsername("sql11170108");
        //SQLCon.setPassword("gHBfXulEu8");

        reqManger = new RequestManager();
        reqManger.initData();


        tcpServer = new TCPServer(reqManger);
        serverThread = new Thread(tcpServer);
        serverThread.start();


        menuLoop();

        //SQLCon.connect();
        //SQLCon.select();


    }


    private static void menuLoop()
    {
        boolean running = true;
        String input;

        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));

        while(running)
        {
            try {
                input =  buffer.readLine();

                switch ( input)
                {
                    case "listReq":
                        reqManger.listReq();
                        break;
                    case "stop":
                        tcpServer.closeServerSocket();
                        serverThread.join();
                        System.exit(1);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

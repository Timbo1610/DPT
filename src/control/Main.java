package control;

import model.Request;

import java.util.ArrayList;

public class Main
{

    private static ArrayList <Request> requestList;
    private static TCPServer tcpServer;

    public static void main(String [] args)
    {
        SQLConnection SQLCon =  new SQLConnection();

        SQLCon.setHost("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11170108?zeroDateTimeBehavior=convertToNull");
        SQLCon.setUsername("sql11170108");
        SQLCon.setPassword("gHBfXulEu8");

        RequestManager reqManger = new RequestManager();


        TCPServer tcpServer = new TCPServer(reqManger);
        Thread serverThread = new Thread(tcpServer);
        serverThread.start();

        System.out.println("test");


        //SQLCon.connect();
        //SQLCon.select();


    }


}

package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{


    private static TCPServer tcpServer;
    private static RequestManager reqManger;
    private static Thread serverThread;
    private static  RouteManager routeManager;

    public static void main(String [] args)
    {
        SQLConnection SQLCon =  new SQLConnection();

        //SQLCon.setHost("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11170108?zeroDateTimeBehavior=convertToNull");
        //SQLCon.setUsername("sql11170108");
        //SQLCon.setPassword("gHBfXulEu8");

        reqManger = new RequestManager();
        reqManger.initData();

        routeManager = new RouteManager();
        routeManager.setReqList(reqManger.getRequests());
        routeManager.calc();
        routeManager.printRoutes();


        tcpServer = new TCPServer(reqManger,routeManager);
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
                    case "calc":
                        routeManager.calc();
                        routeManager.printRoutes();
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

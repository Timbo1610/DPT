package control;

import com.google.gson.Gson;
import model.Request;
import model.Route;
import model.Vehicle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by timme on 03.05.2017.
 */
public class Listener implements Runnable {

    private final Socket clientSocket;
    private Scanner inputScanner = null;
    private InputStream input = null;
    private OutputStream output = null;
    private String readinput;
    private RequestManager reqManager;
    private RouteManager routeManager;
    private Gson gson = new Gson();

    public Listener(Socket clientSocket,RequestManager reqManager,RouteManager routeManager) {

        this.routeManager = routeManager;
        this.reqManager = reqManager;
        this.clientSocket = clientSocket;

        try {
            input = clientSocket.getInputStream();
            inputScanner = new Scanner(input);
            output = clientSocket.getOutputStream();



        } catch (IOException ex) {

            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            try {
                output.close();
                input.close();
                inputScanner.close();
            } catch (IOException exx) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @Override
    public void run() {
        println("Welcome");
        listen();
    }

    private void listen()
    {
        Request req;
        Vehicle veh;

        while(true) try {
            if (!inputScanner.hasNext())
                break;

            readinput = inputScanner.nextLine();
            System.out.println(readinput);


            switch((readinput.split("@"))[0])
            {
                case "req":
                    req = gson.fromJson(readinput.split( "@")[1],Request.class);
                    reqManager.addRequest(req);
                    routeManager.calc();
                    routeManager.printRoutes();
                    break;

                case "veh":
                    veh = gson.fromJson(readinput.split( "@")[1],Vehicle.class);
                    reqManager.addVehicle(veh);
                    break;
                case "get":
                    for(Route temproute: routeManager.getRouteList())
                    {
                        for(Request tempreq: temproute.getRequests()) {
                            println("req@" + routeManager.getRouteList().indexOf(temproute) +"@"+tempreq.getPassenger()
                                    + " @x1@" +tempreq.getOriginX()
                                    + " @y1@" + tempreq.getOriginY()
                                    + " @x2@" + tempreq.getDestinationX()
                                    + " @y2@" + tempreq.getDestinationY());
                        }
                    }
                   break;
            }

            println("received!");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void println(String message)
    {
        try {

            output.write((message + "\r\n").getBytes() );
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}

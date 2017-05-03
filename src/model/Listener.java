package model;

import com.google.gson.Gson;
import control.RequestManager;

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
    private Gson gson = new Gson();

    public Listener(Socket clientSocket,RequestManager reqManager) {


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

            String jsonString = readinput.split( "@")[1];
            switch((readinput.split("@"))[0])
            {
                case "req":
                    req = gson.fromJson(jsonString,Request.class);
                    reqManager.addRequest(req);
                    break;

                case "veh":
                    veh = gson.fromJson(jsonString,Vehicle.class);
                    reqManager.addVehicle(veh);
                    break;
            }








            output.write("test".getBytes());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }


}

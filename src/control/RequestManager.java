package control;

import com.google.gson.Gson;
import model.Request;
import model.Vehicle;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by timme on 03.05.2017.
 */
public class RequestManager {

    private ArrayList<Request> requests = new ArrayList();
    private ArrayList<Vehicle> vehicles = new ArrayList();
    private Gson gson = new Gson();

    public RequestManager()
    {

    }

    public void addRequest(Request req)
    {
        requests.add(req);
        System.out.println(req.getPassenger() + " added");

        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("res/requests.txt"),
                    true /* append = true */));
            pw.write(gson.toJson(req) + "\n");
            pw.close();
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addVehicle(Vehicle veh)
    {
        vehicles.add(veh);
        System.out.println(veh.getName() + " added");

        try
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File("res/vehicles.txt"),
                    true /* append = true */));
            pw.write(gson.toJson(veh) + "\n");
            pw.close();
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initData()
    {

        requests.addAll(loadJson("res/requests.txt",Request.class));
        vehicles.addAll(loadJson("res/vehicles.txt",Vehicle.class));
        System.out.println("Json`s added!");
    }


    private ArrayList loadJson(String file,Class objclass)
    {
        ArrayList list = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(gson.fromJson(line,objclass));
                System.out.println("added: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void listReq()
    {
        System.out.println("Requests:");
        for (Request req: requests)
        {
            System.out.println(
                    req.getPassenger() + " ; " +
                    req.getOriginX() + " ; " +
                    req.getOriginY() + " ; " +
                    req.getDestinationX() + " ; " +
                    req.getDestinationY() + " ; " +
                    req.getPassenger()
            );
        }
    }
}

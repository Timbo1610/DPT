package control;

import model.Request;
import model.Vehicle;

import java.util.ArrayList;

/**
 * Created by timme on 03.05.2017.
 */
public class RequestManager {

    private ArrayList<Request> requests = new ArrayList();
    private ArrayList<Vehicle> vehicles = new ArrayList();

    public RequestManager()
    {

    }

    public void addRequest(Request req)
    {
        requests.add(req);
        System.out.println(req.getPassenger() + " added");
    }

    public void addVehicle(Vehicle veh)
    {
        vehicles.add(veh);
        System.out.println(veh.getName() + " added");
    }
}

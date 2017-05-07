package model;

import java.util.ArrayList;

/**
 * Created by Tim on 07.05.2017.
 */
public class Route {
    private Vehicle vehicle;
    private ArrayList<Request> requests = new ArrayList<>();

    public Route()
    {

    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }
}

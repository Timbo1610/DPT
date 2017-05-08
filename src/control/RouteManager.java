package control;

import model.Request;
import model.Route;
import model.Vehicle;

import java.util.ArrayList;

/**
 * Created by Tim on 07.05.2017.
 */
public class RouteManager {

    ArrayList<Request> reqList;
    ArrayList<Vehicle> vehList;
    ArrayList<Route> routeList;

    int vehicles = 1;
    int distance = 100;

    public RouteManager()
    {

    }


    public void calc()
    {
        double dist;
        for (Request req: reqList)
        {
            System.out.println(req.getPassenger() + " :");
            for (Request coReq: reqList)
            {
                dist = calcOrigindist(req,coReq);
                if(dist <= 1000)
                {
                    System.out.print(coReq.getPassenger() + " " + dist + " | ");
                }
            }
        }
    }


    private double calcOrigindist(Request req1, Request req2)
    {
        double x = req2.getOriginX()- req1.getOriginX();
        double y = req2.getOriginY()- req1.getOriginY();
        return (Math.sqrt(Math.pow(x,2) + Math.pow(y,2)));
    }


    public void setReqList(ArrayList<Request> reqList) {
        this.reqList = reqList;
    }

    public void setVehList(ArrayList<Vehicle> vehList) {
        this.vehList = vehList;
    }
}

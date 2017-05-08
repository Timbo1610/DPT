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
    ArrayList<Route> routeList = new ArrayList<>();

    int vehicles = 1;
    int distance = 100;

    public RouteManager()
    {

    }


    public void calc()
    {
        int originDist, destinationDist;
        for (Request req: reqList)
        {
            System.out.println();
            System.out.print(req.getPassenger() + " : ");
            for (Request coReq: reqList)
            {
                originDist = calcDist(req.getOriginX(),req.getOriginY(),coReq.getOriginX(),coReq.getOriginY());
                destinationDist = calcDist(req.getDestinationX(),req.getDestinationY(),coReq.getDestinationX(),coReq.getDestinationY());

                if(originDist <= distance && destinationDist <= distance && req != coReq)
                {
                    System.out.print(coReq.getPassenger() + " " + originDist + " : " + destinationDist + " | ");
                    req.getRequests().add(coReq);
                }
            }
        }

        for(Request req:reqList)
        {
            Route tempRoute = null;

            for (Request coReq: req.getRequests()) {

                if(coReq.getRoute() != null && req != coReq)
                {
                    tempRoute = coReq.getRoute();
                   //req.getRoute().getRequests().add(coReq);
                }
            }

            if(tempRoute == null)
            {
                tempRoute = new Route();
                tempRoute.getRequests().add(req);
                //tempRoute.getRequests().addAll(req.getRequests());

                for (Request coReq: req.getRequests()) {
                    coReq.setRoute(tempRoute);
                }
                req.setRoute(tempRoute);
                routeList.add(tempRoute);
            }
            else
            {
                tempRoute.getRequests().add(req);
                req.setRoute(tempRoute);
            }
        }
    }


    private int calcDist(double reqX, double reqY, double coreqX, double coreqY)
    {
        double x = reqX - coreqX;
        double y = reqY - coreqY;
        return (int)Math.round(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)));
    }


    public void printRoutes()
    {
        System.out.println("\n\nRoutes");
        for(Route route: routeList)
        {
            System.out.print("Route"+ routeList.indexOf(route) + " : " + route.getRequests().size() + " passengers... :" );
            for(Request req:route.getRequests())
            {
                System.out.print(req.getPassenger() + " " );
            }
            System.out.println();

        }
    }


    public void setReqList(ArrayList<Request> reqList) {
        this.reqList = reqList;
    }

    public void setVehList(ArrayList<Vehicle> vehList) {
        this.vehList = vehList;
    }
}

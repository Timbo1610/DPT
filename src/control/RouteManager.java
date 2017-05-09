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
        routeList.clear();

        int originDist, destinationDist;
        for (Request req: reqList)
        {
            req.getRequests().clear();
            req.setRoute(null);

            System.out.println();
            System.out.print(req.getPassenger() + " : ");
            for (Request coReq: reqList)
            {
                originDist = calcDist(req.getOriginX(),req.getOriginY(),coReq.getOriginX(),coReq.getOriginY());
                destinationDist = calcDist(req.getDestinationX(),req.getDestinationY(),coReq.getDestinationX(),coReq.getDestinationY());

                int walkingDist = Math.min(req.getWalkingDistance(),coReq.getWalkingDistance());

                if(originDist <= walkingDist && destinationDist <= walkingDist && req != coReq)
                {
                    System.out.print(coReq.getPassenger() + " walkingDist:  " + walkingDist + "m Ori:" + originDist + "m Des: " + destinationDist + "m | ");
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

        for(Route route:routeList)
        {
            route.setPickupLocationX(calcMeetingPoint(route.getRequests())[0]);
            route.setPickupLocationY(calcMeetingPoint(route.getRequests())[1]);
        }
    }


    private int calcDist(double reqX, double reqY, double coreqX, double coreqY)
    {
        double x = reqX - coreqX;
        double y = reqY - coreqY;
        return (int)Math.round(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)));
    }

    //algorithm mid point of polygon
    private int[] calcMeetingPoint(ArrayList<Request> requests)
    {
        //System.out.println("calc meeting point:");
        double x = 0;
        double y = 0;
        double area = 0;
        int n =  requests.size();

        for(int j = 0 ; j < requests.size()-1;j++) {

            double xi = requests.get(j).getOriginX();
            double xi1 = requests.get((j+1)).getOriginX();
            double yi = requests.get(j).getOriginY();
            double yi1 = requests.get((j+1)).getOriginY();

            x = x + (xi + xi1)   *(xi*yi1 - xi1*yi);
            y = y + (yi + yi1)   *(xi*yi1 - xi1*yi);

            //System.out.print(" x: " + xi + " y: " + yi + " " + " x1: " + xi1 + " y1: " + yi1 + " ");
            //System.out.println(" x: " + x + " y: " + y);
        }

        for(int i = 0 ; i < requests.size()-1;i++)
        {

            double xi = requests.get(i).getOriginX();
            double xi1 = requests.get((i+1)).getOriginX();
            double yi = requests.get(i).getOriginY();
            double yi1 = requests.get((i+1)).getOriginY();

            area =+ (yi+yi1)*(xi-xi1);
        }

        area = Math.abs(area/2);

        //System.out.print( " area: " + area + " " );


        x/= (n*area);
        y/= (n*area);


        return new int[]{(int)x,(int)y};
    }






    public void printRoutes()
    {
        System.out.println("\n\nRoutes");
        for(Route route: routeList)
        {
            System.out.print("Route"+ routeList.indexOf(route) + " : " + route.getRequests().size() + " passengers.. " );
            //System.out.print("pick-up: " + route.getPickupLocationX() + ":" + route.getPickupLocationY() + " ");
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


    public ArrayList<Request> getReqList() {
        return reqList;
    }

    public ArrayList<Vehicle> getVehList() {
        return vehList;
    }

    public ArrayList<Route> getRouteList() {
        return routeList;
    }
}

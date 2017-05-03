package control;

import model.Request;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.gavaghan.geodesy.Ellipsoid;

import java.util.ArrayList;

public class main
{

    private static ArrayList <Request> requestList;


    public static void main(String [] args)
    {
        SQLConnection SQLCon =  new SQLConnection();

        SQLCon.setHost("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11170108?zeroDateTimeBehavior=convertToNull");
        SQLCon.setUsername("sql11170108");
        SQLCon.setPassword("gHBfXulEu8");

        SQLCon.connect();
        SQLCon.select();

    }

    private void calcDistace()
    {
        Ellipsoid reference = Ellipsoid.WGS84;
        GeodeticCalculator gc = new GeodeticCalculator();

        GlobalCoordinates p1 = new GlobalCoordinates(53.095391, 8.822539);
        GlobalCoordinates p2 = new GlobalCoordinates(53.092621, 8.831895);

        System.out.println(gc.calculateGeodeticCurve(reference,p1,p2).
                getEllipsoidalDistance());


        CSV_Loader loader = new CSV_Loader();

        requestList = new ArrayList();


        loader.addRequests(requestList);
    }
}
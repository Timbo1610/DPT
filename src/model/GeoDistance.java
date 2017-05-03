package model;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.ArrayList;

/**
 * Created by timme on 03.05.2017.
 */
public class GeoDistance {
    private static void calcDistace()
    {
        Ellipsoid reference = Ellipsoid.WGS84;
        GeodeticCalculator gc = new GeodeticCalculator();

        GlobalCoordinates p1 = new GlobalCoordinates(53.095391, 8.822539);
        GlobalCoordinates p2 = new GlobalCoordinates(53.092621, 8.831895);

        System.out.println(gc.calculateGeodeticCurve(reference,p1,p2).
                getEllipsoidalDistance());



        //requestList = new ArrayList();



    }
}

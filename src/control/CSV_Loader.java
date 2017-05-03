package control;

import model.Request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Tim on 17.04.2017.
 */
public class CSV_Loader {
    String csvFile = "res/requests.csv";
    BufferedReader br ;
    String line;
    String cvsSplitBy = ";";



    public void addRequests(ArrayList request_List)
    {
        try {
            br = new BufferedReader(new FileReader(csvFile));

            //skip first line
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] splittedLine = line.split(cvsSplitBy);

                Request temp = new Request();
                temp.setTime(splittedLine[0]);
                temp.setPassenger(splittedLine[1]);
                temp.setOrigin(splittedLine[2]);
                temp.setDestination(splittedLine[3]);
                temp.setTime_range(splittedLine[4]);

                request_List.add(temp);

                temp.setActive(true);

            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


}

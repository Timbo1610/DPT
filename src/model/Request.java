package model;

import java.time.LocalDateTime;

/**
 * Created by Tim on 17.04.2017.
 */
public class Request {
    private LocalDateTime time;
    private String passenger;
    private String origin;
    private String destination;
    private int time_range;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private String transportation_type;

    private boolean active;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = LocalDateTime.parse(time);
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTime_range() {
        return time_range;
    }

    public void setTime_range(String time_range) {
        this.time_range = Integer.parseInt(time_range);
    }

    public String getTransportation_type() {
        return transportation_type;
    }

    public void setTransportation_type(String transportation_type) {
        this.transportation_type = transportation_type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if(active)
            System.out.print("active ");
        else
            System.out.print("deactivated");

        System.out.println(time.toString() + " : " + passenger + " origin:" + origin + " destination: " + destination + " : " + time_range + " : " + transportation_type );


    }
}

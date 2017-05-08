package model;

import java.time.LocalDateTime;

/**
 * Created by Tim on 17.04.2017.
 */
public class Request {
    private LocalDateTime time;
    private String passenger;
    private double originX;
    private double originY;
    private double destinationX;
    private double destinationY;
    private int time_range;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public double getOriginX() {
        return originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public double getOriginY() {
        return originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }

    public int getTime_range() {
        return time_range;
    }

    public void setTime_range(int time_range) {
        this.time_range = time_range;
    }
}

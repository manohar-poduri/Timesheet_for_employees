package com.example.timesheetforemployees;

import java.util.Date;

public class LocationHelper2 {

    private Date date;

    private double latitude;
    private double longitude;

    public LocationHelper2(double latitude, double longitude, Date date) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
package com.example.supermarket;

public class Floorplan {
    private String FloorplanId;
    private String Location;

    public Floorplan(String floorplanId,String location) {
        FloorplanId = floorplanId;
        Location=location;
    }
    public Floorplan()
    {}

    public String getFloorplanId() {
        return FloorplanId;
    }

    public void setFloorplanId(String floorplanId) {
        FloorplanId = floorplanId;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}

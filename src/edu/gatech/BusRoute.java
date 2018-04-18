package edu.gatech;

import java.util.HashMap;

public class BusRoute {
    private Integer ID;
    private Integer routeNumber;
    private String routeName;
    private HashMap<Integer, Integer> stopsOnRoute;
    private HashMap<MiniPair, RoadCondition> roadConditions;

    public BusRoute() {
        this.ID = -1;
    }

    public BusRoute(int uniqueValue) {
        this.ID = uniqueValue;
        this.routeNumber = -1;
        this.routeName = "";
        this.stopsOnRoute = new HashMap<Integer, Integer>();
        this.roadConditions = new HashMap<MiniPair, RoadCondition>();
    }

    public BusRoute(int uniqueValue, int inputNumber, String inputName) {
        this.ID = uniqueValue;
        this.routeNumber = inputNumber;
        this.routeName = inputName;
        this.stopsOnRoute = new HashMap<Integer, Integer>();
        this.roadConditions = new HashMap<MiniPair, RoadCondition>();
   }

    public void setNumber(int inputNumber) { this.routeNumber = inputNumber; }

    public void setName(String inputName) { this.routeName = inputName; }

    public void addNewStop(int stopID) { this.stopsOnRoute.put(stopsOnRoute.size(), stopID); }

    public Integer getID() { return this.ID; }

    public Integer getNumber() { return this.routeNumber; }

    public String getName() { return this.routeName; }

    public void displayEvent() {
        System.out.println(" bus route: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("provide next stop on route along with the distance");
    }

    public Integer getNextLocation(int routeLocation) {
        int routeSize = this.stopsOnRoute.size();
        if (routeSize > 0) { return (routeLocation + 1) % routeSize; }
        return -1;
    }

    public Integer getStopID(int routeLocation) { return this.stopsOnRoute.get(routeLocation); }

    public Integer getLength() { return this.stopsOnRoute.size(); }
    
    public void addRoadCondition(RoadCondition roadCondition, int stopID1, int stopID2) {
        MiniPair stopPair = new MiniPair(stopID1, stopID2);
        this.roadConditions.put(stopPair, roadCondition);        
    }
    
    public RoadCondition getRoadCondition(int stopID1, int stopID2) {
        MiniPair stopPair = new MiniPair(stopID1, stopID2);
        RoadCondition roadCondition = this.roadConditions.get(stopPair);
        return roadCondition;
    }

    public void displayInternalStatus() {
        System.out.print("> bus route - ID: " + Integer.toString(ID));
        System.out.print(" number: " + Integer.toString(routeNumber) + " name: " + routeName);
        System.out.print(" stops: [ ");
        for (int i = 0; i < stopsOnRoute.size(); i++) {
            System.out.print(Integer.toString(i) + ":" + Integer.toString(stopsOnRoute.get(i)) + " ");
        }
        System.out.println("]");
    }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            BusRoute me = (BusRoute) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}

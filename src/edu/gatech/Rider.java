package edu.gatech;
import java.util.ArrayList;
import java.util.HashMap;

public class Rider {
    private HashMap<Integer, ArrayList<Integer>> stopList;
    private Integer startTime;
    private Integer arriveStopTime;
    private Integer onBoardTime;
    private Integer accumulatedOnBusTime;
    private Integer waitTime;
    private Integer currentRouteID;
    
    
    public Rider() {
        this.stopList = new HashMap<Integer, ArrayList<Integer>>();
        this.startTime = 0;
        this.arriveStopTime = 0;
        this.onBoardTime = 0;
        this.accumulatedOnBusTime = 0;
        this.waitTime = 0;
        this.currentRouteID = -1;
    }

    public Rider(HashMap<Integer, ArrayList<Integer>> stops, int eventStartTime) {
        this.stopList = stops;
        this.startTime = eventStartTime;
        this.arriveStopTime = 0;
        this.onBoardTime = eventStartTime;
        this.accumulatedOnBusTime = 0;
        this.waitTime = 0;
        this.currentRouteID = -1;
   }
    
   public HashMap<Integer, ArrayList<Integer>> getStopList() {
       return this.stopList;
   }
   
   public void boardingVehicle(int routeID, int eventTime) {
       currentRouteID = routeID;
       stopList.get(routeID).remove(0);
       onBoardTime = eventTime;
       waitTime = eventTime - startTime;
   }
    
   public void arriveAtStop(int stopID, int eventTime) {
           arriveStopTime = eventTime;
           accumulatedOnBusTime += arriveStopTime - onBoardTime; 
           startTime = eventTime;
   }
   
   public boolean arriveAtDestination() {
       if (stopList.get(currentRouteID).size() == 1) {
           return true;
       } else {
           return false;
       }
   }

}

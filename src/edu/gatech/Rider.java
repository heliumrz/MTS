package edu.gatech;
import java.util.ArrayList;
import java.util.HashMap;

public class Rider {
    private ArrayList<HashMap<Integer, ArrayList<Integer>>> destinationList;
    private Integer startTime;
    private Integer arriveStopTime;
    private Integer onBoardTime;
    private Integer accumulatedOnBusTime;
    private Integer waitTime;
    private Integer currentRouteID;
    
    
    public Rider() {
        this.destinationList = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
        this.startTime = 0;
        this.arriveStopTime = 0;
        this.onBoardTime = 0;
        this.accumulatedOnBusTime = 0;
        this.waitTime = 0;
        this.currentRouteID = -1;
    }

    public Rider(ArrayList<HashMap<Integer, ArrayList<Integer>>> destinationList, int eventStartTime) {
        this.destinationList = destinationList;
        this.startTime = eventStartTime;
        this.arriveStopTime = 0;
        this.onBoardTime = eventStartTime;
        this.accumulatedOnBusTime = 0;
        this.waitTime = 0;
        this.currentRouteID = -1;
   }
    
   public ArrayList<HashMap<Integer, ArrayList<Integer>>> getDestinationList() {
       return this.destinationList;
   }
   
   public void boardingVehicle(int routeID, int eventTime) {
       currentRouteID = routeID;
       destinationList.remove(0);
       onBoardTime = eventTime;
       waitTime = eventTime - startTime;
   }
    
   public void arriveAtStop(int stopID, int eventTime) {
           arriveStopTime = eventTime;
           accumulatedOnBusTime += arriveStopTime - onBoardTime; 
           startTime = eventTime;
   }
   
   public boolean arriveAtDestination() {
       if (destinationList.size() == 1) {
           return true;
       } else {
           return false;
       }
   }
   
   @Override
   public String toString() {
       StringBuilder str = new StringBuilder("(");
       
       for(HashMap<Integer, ArrayList<Integer>> x : destinationList) {
           int stop = x.keySet().iterator().next();
           str.append("Stop: " + stop + ", routes=" + x.get(stop) + "|");
       }
       str.append(")\n");
       return str.toString();
   }

}

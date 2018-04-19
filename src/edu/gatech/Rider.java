package edu.gatech;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rider {
    private List<HashMap<Integer, ArrayList<Integer>>> destinationList;
    private Integer startTime;
    private Integer initialTime;
    private Integer arriveStopTime;
    private Integer onBoardTime;
    private Integer accumulatedOnBusTime;
    private Integer waitTime;
    private Integer exchangeStopNumber;
    
    
    public Rider() {
        this.destinationList = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
        this.startTime = 0;
        this.arriveStopTime = 0;
        this.onBoardTime = 0;
        this.accumulatedOnBusTime = 0;
        this.waitTime = 0;
        this.exchangeStopNumber = 0;
        this.initialTime = 0;
    }

    public Rider(ArrayList<HashMap<Integer, ArrayList<Integer>>> destinationList, int eventStartTime) {
        this.destinationList = destinationList;
        this.startTime = eventStartTime;
        this.arriveStopTime = 0;
        this.onBoardTime = eventStartTime;
        this.accumulatedOnBusTime = 0;
        this.waitTime = 0;
        this.exchangeStopNumber = destinationList.size() - 1;
        this.initialTime = eventStartTime;
   }
    
   public List<HashMap<Integer, ArrayList<Integer>>> getDestinationList() {
       return this.destinationList;
   }
   
   public void setDestinationList(List<HashMap<Integer, ArrayList<Integer>>> input) {
       this.destinationList = input;
   }
   
   public void boardingVehicle(int routeID, int eventTime) {
       destinationList = destinationList.subList(1, destinationList.size());
       onBoardTime = eventTime;
       waitTime += eventTime - startTime;
   }
    
   public void arriveAtStop(int stopID, int eventTime) {
           arriveStopTime = eventTime;
           accumulatedOnBusTime += arriveStopTime - onBoardTime; 
           startTime = eventTime;
   }
   
   public Integer getInitialTime() {
       return initialTime;
   }
   
   public Integer getWaitingTime() {
       return this.waitTime/this.exchangeStopNumber;
   }
   
   public Integer getAccumulatedOnBusTime() {
       return this.accumulatedOnBusTime;
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
           str.append("StopID: " + stop + ", routeList: " + x.get(stop) + " | ");
       }
       int index = str.length();
       str.deleteCharAt(index-1);
       str.deleteCharAt(index-2);
       str.deleteCharAt(index-3);
       str.append(")");
       return str.toString();
   }

}

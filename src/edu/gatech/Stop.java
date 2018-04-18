package edu.gatech;
import java.util.ArrayList;
import java.util.HashMap;

public class Stop {
    private Integer ID;
    private String stopName;
    private Double xCoord;
    private Double yCoord;
    private Integer averageWaitingTime;
    private ArrayList<Rider> waiting;
    private Integer riderRandomNumber;
    private HashMap<Integer, int[]> eventTimeStatus;
    private Integer lastEventTime;

    public Stop() {
        this.ID = -1;
    }

    public Stop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.xCoord = 0.0;
        this.yCoord = 0.0;
        this.averageWaitingTime = 0;
        this.waiting = new ArrayList<Rider>();
        this.averageWaitingTime = 0;
        this.riderRandomNumber = 0;
        this.eventTimeStatus = new HashMap<>();
        this.lastEventTime = 0;
    }

    public Stop(int uniqueValue, String inputName, double inputXCoord, double inputYCoord, int riderRandomNumber) {
        this.ID = uniqueValue;
        this.stopName = inputName;
        this.xCoord = inputXCoord;
        this.yCoord = inputYCoord;
        this.waiting = new ArrayList<Rider>();
        this.riderRandomNumber = riderRandomNumber;
        this.eventTimeStatus = new HashMap<>();
        this.lastEventTime = 0;
   }

    public void setName(String inputName) { this.stopName = inputName; }

    public void setRiders(ArrayList<Rider> inputRiderList) { this.waiting = inputRiderList; }

    public void setXCoord(double inputXCoord) { this.xCoord = inputXCoord; }

    public void setYCoord(double inputYCoord) { this.yCoord = inputYCoord; }

    public Integer getID() { return this.ID; }

    public String getName() { return this.stopName; }

    public ArrayList<Rider> getWaiting() { return this.waiting; }

    public Double getXCoord() { return this.xCoord; }

    public Double getYCoord() { return this.yCoord; }

    public void displayEvent() {
        System.out.println(" bus stop: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("get new people - exchange with bus when it passes by");
    }


    public ArrayList<Rider> exchangeRiders(int eventTime, int availableSeats, int routeID, ArrayList<Rider> exchangePassengers) {     
        // exchange passengers
        ArrayList<Rider> boardingList = new ArrayList<Rider>();
        int seats = availableSeats;
        int waitingTime = 0;
        for (Rider rider : this.waiting) {
            int key = rider.getDestinationList().get(0).keySet().iterator().next();
            ArrayList<Integer> routeList = rider.getDestinationList().get(0).get(key);
            if (routeList.contains(routeID)) {               
                if (seats > 0) {
                    rider.boardingVehicle(routeID, eventTime);
                    waitingTime += rider.getWaitingTime();
                    boardingList.add(rider);                    
                    seats--;
                } else {
                    break;
                }
            }
        }
        if (!boardingList.isEmpty()) {
            this.averageWaitingTime = waitingTime / boardingList.size();
        }
        // save waiting time
        int [] status = new int[2];
        status[0] = waitingTime;
        status[1] = boardingList.size();
        this.eventTimeStatus.put(eventTime, status);
        this.waiting.removeAll(boardingList);
        this.waiting.addAll(exchangePassengers);
        return boardingList;        
    }
        
    public Integer getAverageWaitingTime() {
        return this.averageWaitingTime;
    }
    
    public HashMap<Integer, int[]> getEventTimeStatus() {
        return this.eventTimeStatus;
    }
    
    public void addNewRiders(ArrayList<Rider> moreRiders) { waiting.addAll(moreRiders); }
    
    public Integer getNewRiderNumber(int eventTime) {        
        int newRiderNumber = 0;
        for (int time = lastEventTime; time < eventTime; time++) {
            if (((eventTime % 120) >= 40 && (eventTime % 120) <= 80)) {
                newRiderNumber += riderRandomNumber*5;
            } else {
                newRiderNumber += riderRandomNumber;
            }
        }
        lastEventTime = eventTime;
        return newRiderNumber;
    }

    public void displayInternalStatus() {
        System.out.print("> stop - ID: " + Integer.toString(ID));
        System.out.print(" name: " + stopName + " waiting: " + Integer.toString(waiting.size()));
    }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Stop me = (Stop) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}

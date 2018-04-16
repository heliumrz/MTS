package edu.gatech;
import java.util.ArrayList;

import java.util.Random;
import java.util.HashMap;

public class Stop {
    private Integer ID;
    private String stopName;
    private Double xCoord;
    private Double yCoord;
    private Random randGenerator;
    private HashMap<Integer, int[]> rateWaiting;
    private ArrayList<Rider> waiting;

    public Stop() {
        this.ID = -1;
    }

    public Stop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.xCoord = 0.0;
        this.yCoord = 0.0;
        this.randGenerator = new Random();
        this.waiting = new ArrayList<Rider>();
        this.rateWaiting = new HashMap<Integer, int[]>();
    }

    public Stop(int uniqueValue, String inputName, double inputXCoord, double inputYCoord) {
        this.ID = uniqueValue;
        this.stopName = inputName;
        this.xCoord = inputXCoord;
        this.yCoord = inputYCoord;
        this.randGenerator = new Random();
        this.waiting = new ArrayList<Rider>();
        this.rateWaiting = new HashMap<Integer, int[]>();
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


    public ArrayList<Rider> exchangeRiders(int rank, int availableSeats, int routeID, ArrayList<Rider> arrivingPassengers) {
        ArrayList<Rider> boardingList = new ArrayList<Rider>();
        int seats = availableSeats;
        for (Rider rider : this.waiting) {
            int key = rider.getDestinationList().get(0).keySet().iterator().next();
            ArrayList<Integer> routeList = rider.getDestinationList().get(0).get(key);
            if (routeList.contains(routeID)) {               
                if (seats > 0) {
                    rider.boardingVehicle(routeID, rank);
                    boardingList.add(rider);
                    //this.waiting.remove(rider);
                    seats--;
                } else {
                    break;
                }
            }
        }
        this.waiting.removeAll(boardingList);
        this.waiting.addAll(arrivingPassengers);
        return boardingList;        
    }
        
    public void addNewRiders(ArrayList<Rider> moreRiders) { waiting.addAll(moreRiders); }
    
    public Integer getIntendedNewRiders(int eventTime) {
        int[] waitingNumber = rateWaiting.get(eventTime); 
        int newRiderNumber = 0;
        if (waitingNumber != null) {
            newRiderNumber = randomBiasedValue(waitingNumber[0], waitingNumber[1], waitingNumber[2]);
        } else {
            newRiderNumber = (int) (Math.random() * 5);
        }
        return newRiderNumber;
    }

    public void displayInternalStatus() {
        System.out.print("> stop - ID: " + Integer.toString(ID));
        System.out.print(" name: " + stopName + " waiting: " + Integer.toString(waiting.size()));
    }

    public void addArrivalInfo(int timeSlot, int minWaiting, int avgWaiting, int maxWaiting) {
        rateWaiting.put(timeSlot, new int[]{minWaiting, avgWaiting, maxWaiting});
    }

    private int randomBiasedValue(int lower, int middle, int upper) {
        int lowerRange = randGenerator.nextInt(middle - lower + 1) + lower;
        int upperRange = randGenerator.nextInt(upper - middle + 1) + middle;
        return (lowerRange + upperRange) /2;
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

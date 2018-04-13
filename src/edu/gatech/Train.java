package edu.gatech;
import java.util.ArrayList;

public class Train {
    private Integer ID;
    private Integer route;
    private Integer nextLocation;
    private Integer prevLocation;
    private ArrayList<Rider> passengers;
    private Integer capacity;
    private Integer speed; // given in statute miles per hour

    public Train() {
        this.ID = -1;
    }

    public Train(int uniqueValue) {
        this.ID = uniqueValue;
        this.route = -1;
        this.nextLocation = -1;
        this.prevLocation = -1;
        this.passengers = new ArrayList<Rider>();
        this.capacity = -1;
        this.speed = -1;
    }

    public Train(int uniqueValue, int inputRoute, int inputLocation, ArrayList<Rider> inputPassengerList, int inputCapacity, int inputSpeed) {
        this.ID = uniqueValue;
        this.route = inputRoute;
        this.nextLocation = inputLocation;
        this.prevLocation = inputLocation;
        this.passengers = inputPassengerList;
        this.capacity = inputCapacity;
        this.speed = inputSpeed;
   }

    public void setRoute(int inputRoute) { this.route = inputRoute; }

    public void setLocation(int inputLocation) {
        this.prevLocation = this.nextLocation;
        this.nextLocation = inputLocation;
    }

    public void setPassengers(ArrayList<Rider> inputPassengerList) { this.passengers = inputPassengerList; }

    public void setCapacity(int inputCapacity) { this.capacity = inputCapacity; }

    public void setSpeed(int inputSpeed) { this.speed = inputSpeed; }

    public Integer getID() { return this.ID; }

    public Integer getRouteID() { return this.route; }

    public Integer getLocation() { return this.nextLocation; }

    public Integer getPastLocation() { return this.prevLocation; }

    public ArrayList<Rider> getPassengers() { return this.passengers; }

    public Integer getCapacity() { return this.capacity; }

    public Integer getSpeed() { return this.speed; }

    public void displayEvent() {
        System.out.println(" Train: " + Integer.toString(this.ID));
    }

    public void displayInternalStatus() {
        System.out.print("> Train - ID: " + Integer.toString(ID) + " route: " + Integer.toString(route));
        System.out.print(" location from: " + Integer.toString(prevLocation) + " to: " + Integer.toString(nextLocation));
        System.out.print(" passengers: " + Integer.toString(passengers.size()) + " capacity: " + Integer.toString(capacity));
        System.out.println(" speed: " + Integer.toString(speed));
    }

    public void takeTurn() {
        System.out.println("drop off passengers - pickup passengers to capacity - move to next stop");
    }

    public ArrayList<Rider> passengersLeave(int stopID, int eventTime) { 
        ArrayList<Rider> exchangeList = new ArrayList<Rider>();
        for (Rider passenger : passengers) {
            
            if (passenger.getStopList().get(this.route).get(0) == stopID) {
                passenger.arriveAtStop(stopID, eventTime);
                if (passenger.arriveAtDestination()) {
                    passengers.remove(passenger);
                } else {
                    exchangeList.add(passenger);
                    passengers.remove(passenger);                   
                }
            }
        }
        return exchangeList;
    }
    
    public void addNewPassengers(ArrayList<Rider> newRiderList) {
        this.passengers.addAll(newRiderList);
    }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Train me = (Train) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}

package edu.gatech;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class SimQueue {
    private static PriorityQueue<SimEvent> eventQueue;
    private Comparator<SimEvent> simComparator;
    final static Integer passengerFrequency = 3;

    public SimQueue() {
        simComparator = new SimEventComparator();
        eventQueue = new PriorityQueue<SimEvent>(100, simComparator);
    }

    public void triggerNextEvent(TransitionSystem busModel) {
        if (eventQueue.size() > 0) {
            SimEvent activeEvent = eventQueue.poll();
            activeEvent.displayEvent();
            switch (activeEvent.getType()) {
                case "move_bus":
                    // identify the bus that will move
                    Bus activeBus = busModel.getBus(activeEvent.getID());
                    System.out.println(" the bus being observed is: " + Integer.toString(activeBus.getID()));

                    // identify the current stop
                    BusRoute activeRoute = busModel.getBusRoute(activeBus.getRouteID());
                    System.out.println(" the bus is driving on route: " + Integer.toString(activeRoute.getID()));

                    int activeLocation = activeBus.getLocation();
                    int activeStopID = activeRoute.getStopID(activeLocation);
                    Stop activeStop = busModel.getStop(activeStopID);
                    System.out.println(" the bus is currently at stop: " + Integer.toString(activeStop.getID()) + " - " + activeStop.getName());
                    
                    // generate and add new riders to active stop
                    int newRiderNumber = activeStop.getIntendedNewRiders(activeEvent.getRank());
                    ArrayList<Rider> newRiderList = busModel.generateRiders(activeStopID, newRiderNumber, activeEvent.getRank());
                    System.out.println(" New riders:\n " + newRiderList);
                    activeStop.addNewRiders(newRiderList);
                    System.out.println(" Waiting riders:\n " + activeStop.getWaiting());

                    // drop off passengers at current stop
                    int preStopPassengers = activeBus.getPassengers().size();
                    ArrayList<Rider> exchangePassengerList = activeBus.passengersLeave(activeStopID, activeEvent.getRank());
                    System.out.println(" Exchange riders:\n " + exchangePassengerList);
                    
                    // exchange riders in active stop: riders leave stop for boarding and passengers add to stop waiting list
                    int availableSeats = activeBus.getCapacity() - activeBus.getPassengers().size();
                    ArrayList<Rider> boardingPassengerList = activeStop.exchangeRiders(activeEvent.getRank(), availableSeats, activeBus.getRouteID(), exchangePassengerList);
                    System.out.println(" Boarding riders:\n " + boardingPassengerList);
                    System.out.println(" The average waiting time is: " + activeStop.getAverageWaitingTime() + "\n");
                    
                    // add new passengers to active bus
                    activeBus.addNewPassengers(boardingPassengerList);
                    int postStopPassengers = activeBus.getPassengers().size();
                 
                    System.out.println(" Passengers pre-stop: " + Integer.toString(preStopPassengers) + " post-stop: " + Integer.toString(postStopPassengers));

                    // determine next stop
                    int nextLocation = activeRoute.getNextLocation(activeLocation);
                    int nextStopID = activeRoute.getStopID(nextLocation);
                    Stop nextStop = busModel.getStop(nextStopID);
                    System.out.println(" The bus is heading to stop: " + Integer.toString(nextStopID) + " - " + nextStop.getName() + "\n");
                    
                   // get road condition 
                    RoadCondition roadCondition = activeRoute.getRoadCondition(activeStopID, nextStopID); 
                    if (roadCondition == null) {
                        System.out.println(String.format(" Error: no road condition found! %d, %d", activeStopID, nextStopID));
                    }
                    
                    // get speed and distance between two stops
                    
                    // User current time to calculate rushhour
                    
                    // rushhour is defined in RushHourChecker: 08:00-10:00 and 17:00-19:00
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                	Date date = new Date();
                    double roadSpeed = roadCondition.getSpeed(date);
                    
                    // use date str
                 	// String dateStr = "08:00";
                    //  double roadSpeed = roadCondition.getSpeed(dateStr);
                  
                    // Without considering of rushhour                    
                    //double roadSpeed = roadCondition.getSpeed();
                    
                    double distance = roadCondition.getDistance();
                    double travelSpeed;
                    if (activeBus.getSpeed() < roadSpeed) {
                        travelSpeed = activeBus.getSpeed();
                    } else {
                        travelSpeed = roadSpeed;
                    }
                    
                    // determine next event time and set bus location as next location
                    int travelTime = 1 + (int)(distance * 60 / travelSpeed);             
                    activeBus.setLocation(nextLocation);

                    // generate next event for this bus
                    eventQueue.add(new SimEvent(activeEvent.getRank() + travelTime, "move_bus", activeEvent.getID()));
                    break;
                    
                case "move_train":
                 // identify the bus that will move
                    Train activeTrain = busModel.getTrain(activeEvent.getID());
                    System.out.println(" the train being observed is: " + Integer.toString(activeTrain.getID()));

                    // identify the current stop
                    RailRoute activeRailRoute = busModel.getRailRoute(activeTrain.getRouteID());
                    System.out.println(" the train is driving on route: " + Integer.toString(activeRailRoute.getID()));

                    int activeTrainLocation = activeTrain.getLocation();
                    int activeRailStopID = activeRailRoute.getStopID(activeTrainLocation);
                    Stop activeRailStop = busModel.getStop(activeRailStopID);
                    System.out.println(" the train is currently at stop: " + Integer.toString(activeRailStop.getID()) + " - " + activeRailStop.getName());
                    
                    // generate and add new riders to active stop
                    int newStopRiderNumber = activeRailStop.getIntendedNewRiders(activeEvent.getRank());
                    ArrayList<Rider> newStopRiderList = busModel.generateRiders(activeRailStopID, newStopRiderNumber, activeEvent.getRank());
                    System.out.println(" New riders:\n " + newStopRiderList);
                    activeRailStop.addNewRiders(newStopRiderList);
                    System.out.println(" Waiting riders:\n " + activeRailStop.getWaiting());
                    

                    // drop off passengers at current stop
                    int preRailStopPassengers = activeTrain.getPassengers().size();
                    ArrayList<Rider> exchangeStopPassengerList = activeTrain.passengersLeave(activeRailStopID, activeEvent.getRank());
                    System.out.println(" Exchange riders:\n " + exchangeStopPassengerList);
                    
                    // exchange riders in active stop: riders leave stop for boarding and passengers add to stop waiting list
                    int availableTrainSeats = activeTrain.getCapacity() - activeTrain.getPassengers().size();
                    ArrayList<Rider> boardingTrainPassengerList = activeRailStop.exchangeRiders(activeEvent.getRank(), availableTrainSeats, activeTrain.getRouteID(), exchangeStopPassengerList);
                    System.out.println(" Boarding riders:\n " + boardingTrainPassengerList);
                    System.out.println(" The average waiting time is: " + activeRailStop.getAverageWaitingTime() + "\n");
                    
                    // add new passengers to active bus
                    activeTrain.addNewPassengers(boardingTrainPassengerList);
                    int postRailStopPassengers = activeTrain.getPassengers().size();
                 
                    System.out.println(" Passengers pre-stop: " + Integer.toString(preRailStopPassengers) + " post-stop: " + Integer.toString(postRailStopPassengers));

                    // determine next stop
                    int nextTrainLocation = activeRailRoute.getNextLocation(activeTrainLocation);
                    int nextRailStopID = activeRailRoute.getStopID(nextTrainLocation);
                    Stop nextRailStop = busModel.getStop(nextRailStopID);
                    System.out.println(" the train is heading to stop: " + Integer.toString(nextRailStopID) + " - " + nextRailStop.getName() + "\n");
                    
                    // get distance between two stops and train speed
                    Double railStopDistance = activeRailRoute.getStopDistance(activeRailStopID, nextRailStopID);
                    double trainSpeed = activeTrain.getSpeed();
                    
                    // determine next event time and set train location as next location
                    int trainTravelTime = 1 + (int)(railStopDistance * 60 / trainSpeed);             
                    activeTrain.setLocation(nextTrainLocation);

                    // generate next event for this bus
                    eventQueue.add(new SimEvent(activeEvent.getRank() + trainTravelTime, "move_train", activeEvent.getID()));
                    break;
                    
                default:
                    System.out.println(" event not recognized");
                    break;
            }
        } else {
            System.out.println(" event queue empty");
        }
    }

    public void addNewEvent(Integer eventRank, String eventType, Integer eventID) {
        eventQueue.add(new SimEvent(eventRank, eventType, eventID));
    }

}
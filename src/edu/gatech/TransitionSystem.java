package edu.gatech;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TransitionSystem {
    private HashMap<Integer, Stop> stops;
    private HashMap<Integer, BusRoute> busRoutes;
    private HashMap<Integer, RailRoute> railRoutes;
    private HashMap<Integer, Train> trains;
    private HashMap<Integer, Bus> buses;
    private HashMap<Integer, ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>>> shortestPaths;
    private HashMap<MiniPair, ArrayList<Integer>> routesOnStop;

    public TransitionSystem() {
        stops = new HashMap<Integer, Stop>();
        busRoutes = new HashMap<Integer, BusRoute>();
        railRoutes = new HashMap<Integer, RailRoute>();
        trains = new HashMap<Integer, Train>();
        buses = new HashMap<Integer, Bus>();
        shortestPaths = new HashMap<Integer, ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>>>();
        routesOnStop = new HashMap<MiniPair, ArrayList<Integer>>();
    }

    public Stop getStop(int stopID) {
        if (stops.containsKey(stopID)) { return stops.get(stopID); }
        return null;
    }

    public BusRoute getBusRoute(int routeID) {
        if (busRoutes.containsKey(routeID)) { return busRoutes.get(routeID); }
        return null;
    }
    
    public RailRoute getRailRoute(int routeID) {
        if (railRoutes.containsKey(routeID)) { return railRoutes.get(routeID); }
        return null;
    }

    public Bus getBus(int busID) {
        if (buses.containsKey(busID)) { return buses.get(busID); }
        return null;
    }
    
    public Train getTrain(int trainID) {
        if (trains.containsKey(trainID)) { return trains.get(trainID); }
        return null;
    }

    public int makeStop(int uniqueID, String inputName, double inputXCoord, double inputYCoord, int riderRandomNumber) {
        // int uniqueID = stops.size();
        //ArrayList<Rider> inputRiderList = generateRiders(uniqueID, inputRiders, 0);
        stops.put(uniqueID, new Stop(uniqueID, inputName, inputXCoord, inputYCoord, riderRandomNumber));
        return uniqueID;
    }

    public int makeBusRoute(int uniqueID, int inputNumber, String inputName) {
        // int uniqueID = routes.size();
        busRoutes.put(uniqueID, new BusRoute(uniqueID, inputNumber, inputName));
        return uniqueID;
    }
    
    public int makeRailRoute(int uniqueID, int inputNumber, String inputName) {
        // int uniqueID = routes.size();
        railRoutes.put(uniqueID, new RailRoute(uniqueID, inputNumber, inputName));
        return uniqueID;
    }

    public int makeBus(int uniqueID, int inputRoute, int inputLocation, int inputCapacity, double inputSpeed) {
        // int uniqueID = buses.size();
        //ArrayList<Rider> inputPassengerList = generateRiders(uniqueID, inputPassengers, 0);
        buses.put(uniqueID, new Bus(uniqueID, inputRoute, inputLocation, inputCapacity, inputSpeed));
        return uniqueID;
    }
    
    public int makeTrain(int uniqueID, int inputRoute, int inputLocation, int inputCapacity, double inputSpeed) {
        // int uniqueID = buses.size();
        //ArrayList<Rider> inputPassengerList = generateRiders(uniqueID, inputPassengers, 0);
        trains.put(uniqueID, new Train(uniqueID, inputRoute, inputLocation, inputCapacity, inputSpeed));
        return uniqueID;
    }
    
    public void setRoadCondition(int busRouteID, int stopID1, int stopID2, double distance, double lowestSpeed, double averageSpeed, double maxSpeed) {
        BusRoute busRoute = getBusRoute(busRouteID);
        RoadCondition roadCondition = new RoadCondition(distance, lowestSpeed, averageSpeed, maxSpeed);
        busRoute.addRoadCondition(roadCondition, stopID1, stopID2);
    }
    
    public void setRailStopDistance(int railRouteID, int stopID1, int stopID2, Double distance) {
        RailRoute railRoute = getRailRoute(railRouteID);     
        railRoute.addStopDistance(stopID1, stopID2, distance);
    }

    
    public void appendStopToBusRoute(int routeID, int nextStopID) { 
        busRoutes.get(routeID).addNewStop(nextStopID); 
    }
    
    public void appendStopToRailRoute(int routeID, int nextStopID) { 
        railRoutes.get(routeID).addNewStop(nextStopID);                
    }
    
    public void addRiderToBus(int busID, int riderNumber) {
        Bus bus = buses.get(busID);
        BusRoute busRoute = busRoutes.get(bus.getRouteID());
        int stopID = busRoute.getStopID(bus.getLocation());
        ArrayList<Rider> inputPassengerList = generateRiders(stopID, riderNumber, 0);
     // Remove the start point
        for(Rider rider : inputPassengerList) {
            rider.setDestinationList(rider.getDestinationList().subList(1, rider.getDestinationList().size()));
        }
        bus.addNewPassengers(inputPassengerList);
    }
    
    public void addRiderToTrain(int trainID, int riderNumber) {
        Train train = trains.get(trainID);
        RailRoute railRoute = railRoutes.get(train.getRouteID());
        int stopID = railRoute.getStopID(train.getLocation());
        ArrayList<Rider> inputPassengerList = generateRiders(stopID, riderNumber, 0);
        // Remove the start point
        for(Rider rider : inputPassengerList) {       
            rider.setDestinationList(rider.getDestinationList().subList(1, rider.getDestinationList().size()));
        }
        train.addNewPassengers(inputPassengerList);
    }
    
    public void addRiderToStop(int stopID, int riderNumber) {
        Stop stop = stops.get(stopID);
        ArrayList<Rider> inputPassengerList = generateRiders(stopID, riderNumber, 0);
        stop.addNewRiders(inputPassengerList);
    }
    
    public HashMap<Integer, Stop> getStops() { return stops; }

    public HashMap<Integer, BusRoute> getBusRoutes() { return busRoutes; }

    public HashMap<Integer, Bus> getBuses() { return buses; }
    
    public HashMap<Integer, RailRoute> getRailRoutes() { return railRoutes; }

    public HashMap<Integer, Train> getTrains() { return trains; }
        
    private void addRouteToRoutsOnStop(MiniPair stopPair, int routeId) {
        if (routesOnStop.containsKey(stopPair)) {
            ArrayList<Integer> routesList = routesOnStop.get(stopPair);
            if (!routesList.contains(routeId)) {
                routesList.add(routeId);
            }
        } else {
            ArrayList<Integer> routesList = new ArrayList<Integer>();
            routesList.add(routeId);
            routesOnStop.put(stopPair, routesList);
        }
    }
    
    public void generateMap() {
        int stopNumber = stops.size();
        Digraph digraph = new Digraph(stopNumber);
        
        for (Integer busRouteID : busRoutes.keySet()) {
            BusRoute busRoute = busRoutes.get(busRouteID);
            int routeLength = busRoute.getLength();
            for (int j = 0; j < routeLength - 1; j++) {
                int stopID1 = busRoute.getStopID(j);
                int stopID2 = busRoute.getStopID(j+1);
                digraph.addEdge(stopID1, stopID2);    
                MiniPair stopPair = new MiniPair(stopID1, stopID2);
                addRouteToRoutsOnStop(stopPair, busRouteID);
            }
            addRouteToRoutsOnStop(
                    new MiniPair(busRoute.getStopID(routeLength-1), busRoute.getStopID(0)),
                    busRouteID);
            digraph.addEdge(busRoute.getStopID(routeLength-1), busRoute.getStopID(0));
        }       
        for (Integer railRouteID : railRoutes.keySet()) {
            RailRoute railRoute = railRoutes.get(railRouteID);
            int routeLength = railRoute.getLength();
            for (int j = 0; j < routeLength - 1; j++) {
                int stopID1 = railRoute.getStopID(j);
                int stopID2 = railRoute.getStopID(j+1);
                digraph.addEdge(stopID1, stopID2); 
                MiniPair stopPair = new MiniPair(stopID1, stopID2);
                addRouteToRoutsOnStop(stopPair, railRouteID);
            }
            addRouteToRoutsOnStop(
                    new MiniPair(railRoute.getStopID(routeLength-1), railRoute.getStopID(0)),
                    railRouteID);
            digraph.addEdge(railRoute.getStopID(routeLength-1), railRoute.getStopID(0));
        }
        
        digraph.BreadthFirstDirectedPaths();
        
        for (Integer stopID : stops.keySet()) {
            ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> paths = new ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>>();            
            shortestPaths.put(stopID, paths);
            for (Integer otherStopID : stops.keySet()) {
                if (stopID == otherStopID) {
                    continue;
                } else {
                    if (digraph.hasPathTo(stopID, otherStopID)) {
                        Stack<Integer> shortestPath = (Stack<Integer>)digraph.pathTo(stopID, otherStopID);  
                        ArrayList<HashMap<Integer, ArrayList<Integer>>> destinationList = generateDestinationList(shortestPath);
                        paths.add(destinationList);                       
                    }
                }
            }
        }
    }
    
    private ArrayList<HashMap<Integer, ArrayList<Integer>>> generateDestinationList(Stack<Integer> shortestPath) {
        ArrayList<HashMap<Integer, ArrayList<Integer>>> destinationList = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();    
        int exchangeStopID = shortestPath.get(shortestPath.size() - 1);
        int exchangeStopNextID = shortestPath.get(shortestPath.size() - 2);
        MiniPair exchangeStopPair = new MiniPair(exchangeStopID, exchangeStopNextID);
        //System.out.println(String.format("Looking for %d,%d, %d", exchangeStopPair.getID(), exchangeStopPair.getValue(), exchangeStopPair.hashCode()));
        //System.out.println(routesOnStop.containsKey(exchangeStopPair));
        ArrayList<Integer> exchangeStopRouteList = routesOnStop.get(exchangeStopPair);

        
        for (int i = shortestPath.size() - 2; i > 0; i--) {
            int currentStopID = shortestPath.get(i);
            int currentStopNextID = shortestPath.get(i - 1);
            MiniPair currentStopPair = new MiniPair(currentStopID, currentStopNextID);
            ArrayList<Integer> currentStopRouteList = routesOnStop.get(currentStopPair);
            ArrayList<Integer> routeList = new ArrayList<Integer>();
            // use set to do the intersection            
            for (Integer routeID : exchangeStopRouteList) {                  
                if (currentStopRouteList.contains(routeID)) {                      
                    routeList.add(routeID);
                } 
            }
            if (routeList.isEmpty()) {
                HashMap<Integer, ArrayList<Integer>> stopRouteList = new HashMap<Integer, ArrayList<Integer>>();   
                stopRouteList.put(exchangeStopID, exchangeStopRouteList);
                destinationList.add(stopRouteList);
                exchangeStopID = currentStopID;
                exchangeStopRouteList = currentStopRouteList;               
            } else {
                exchangeStopRouteList = routeList;
            }            
        }
        HashMap<Integer, ArrayList<Integer>> stopRouteList1 = new HashMap<Integer, ArrayList<Integer>>();   
        stopRouteList1.put(exchangeStopID, exchangeStopRouteList);
        destinationList.add(stopRouteList1);
        
        HashMap<Integer, ArrayList<Integer>> stopRouteList2 = new HashMap<Integer, ArrayList<Integer>>();           
        ArrayList<Integer> finalStopRoute = new ArrayList<Integer>();
        int finalStopID = shortestPath.get(0);
        stopRouteList2.put(finalStopID, finalStopRoute);
        destinationList.add(stopRouteList2);
        
        return destinationList;
    }
         
    public ArrayList<Rider> generateRiders(int stopID, int inputNumbers, int eventTime) {
        ArrayList<Rider> riderList = new ArrayList<Rider>(); 
        ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> paths = shortestPaths.get(stopID);
        int pathsNumber = paths.size();
        for (int i = 0; i < inputNumbers; i++) {
            int randomNumber = (int) (Math.random()*pathsNumber);
            ArrayList<HashMap<Integer, ArrayList<Integer>>> destinationList = paths.get(randomNumber);    
            /*
            System.out.println("New rider generated");
            for(HashMap<Integer, ArrayList<Integer>> x : destinationList) {
                int stop = x.keySet().iterator().next();
                System.out.println("Stop: " + stop + ", routes=" + x.get(stop));
            }
            */
            Rider rider = new Rider(destinationList, eventTime);
            riderList.add(rider);
        }        
        return riderList;
    }
  
    public String finalReport() {
    	String finalText = "";
        int passengerSizeBusNormal = 0;
        int passengerSizeTrainNormal = 0;
        int passengerSizeBusRushHour = 0;
        int passengerSizeTrainRushHour = 0;
        int busCapacityNormal = 0;
        int busCapacityRushHour = 0;
        int trainCapacityNormal = 0;
        int trainCapacityRushHour = 0;
        int waitTimeNormalRider = 0;
        int waitRiderNumberNormalRider = 0;
        int waitTimeRushHourRider = 0;
        int waitRiderNumberRushHourRider = 0;
        int attendanceRateBusNormal=0;
        int attendanceRateBusRushHour = 0;
        int attendanceRateTrainNormal = 0;
        int attendanceRateTrainRushHour = 0;
        int waitingTimeNormalRider = 0;
        int waitingTimeRushHourRider = 0;
        int attendanceRateTrainAverage = 0;
        int attendanceRateRushHour =0;
        int waitingTimeAverageRider = 0;

        for (Bus bus: buses.values()) {
            HashMap<Integer, Integer> eventTimePassenger = bus.getEventTimeStatus();
            for (int time : eventTimePassenger.keySet()) {
                if (((time % 120) >= 40 && (time % 120) <= 80)) {
                    passengerSizeBusRushHour += eventTimePassenger.get(time);
                    busCapacityRushHour += bus.getCapacity();
                } else {
                    passengerSizeBusNormal += eventTimePassenger.get(time);
                    busCapacityNormal += bus.getCapacity();
                }
            }
            ArrayList<Rider> arrivingList = bus.getArrivingList();
            for (Rider rider : arrivingList) {
                if (((rider.getInitialTime() % 120) >= 40 && (rider.getInitialTime() % 120) <= 80)) {
                    waitTimeRushHourRider += rider.getWaitingTime();
                    waitRiderNumberRushHourRider++;                   
                } else {
                    waitTimeNormalRider += rider.getWaitingTime();
                    waitRiderNumberNormalRider++;
                }
            }
        }
        
        for (Train train: trains.values()) {
            HashMap<Integer, Integer> eventTimePassenger = train.getEventTimeStatus();
            for (int time : eventTimePassenger.keySet()) {
                if (((time % 120) >= 40 && (time % 120) <= 80)) {
                    passengerSizeTrainRushHour += eventTimePassenger.get(time);
                    trainCapacityRushHour += train.getCapacity();
                } else {
                    passengerSizeTrainNormal += eventTimePassenger.get(time);
                    trainCapacityNormal += train.getCapacity();
                }
            }
            ArrayList<Rider> arrivingList = train.getArrivingList();
            for (Rider rider : arrivingList) {
                if (((rider.getInitialTime() % 120) >= 40 && (rider.getInitialTime() % 120) <= 80)) {
                    waitTimeRushHourRider += rider.getWaitingTime();
                    waitRiderNumberRushHourRider++;                   
                } else {
                    waitTimeNormalRider += rider.getWaitingTime();
                    waitRiderNumberNormalRider++;
                }
            }
        }
        
        attendanceRateBusNormal = passengerSizeBusNormal*100/busCapacityNormal;
        if(busCapacityRushHour!=0) { 
        	attendanceRateBusRushHour = passengerSizeBusRushHour*100/busCapacityRushHour;}
       
        int attendanceRateBusAverage = (passengerSizeBusRushHour + passengerSizeBusNormal)*100/(busCapacityRushHour + busCapacityNormal);
        if(trainCapacityNormal!=0) {
            attendanceRateTrainNormal = passengerSizeTrainNormal*100/trainCapacityNormal;}
        if(trainCapacityRushHour!=0) {
            attendanceRateTrainRushHour = passengerSizeTrainRushHour*100/trainCapacityRushHour;}
        if((trainCapacityRushHour + trainCapacityNormal)!=0) {
        attendanceRateTrainAverage = (passengerSizeTrainRushHour + passengerSizeTrainNormal)*100/(trainCapacityRushHour + trainCapacityNormal);}
        int attendanceRateNormal = (passengerSizeBusNormal + passengerSizeTrainNormal)*100/(busCapacityNormal + trainCapacityNormal);
        if((busCapacityRushHour + trainCapacityRushHour)!=0) {
            attendanceRateRushHour = (passengerSizeBusRushHour + passengerSizeTrainRushHour)*100/(busCapacityRushHour + trainCapacityRushHour);}
        int attendanceRateAverage = (passengerSizeBusRushHour + passengerSizeBusNormal + passengerSizeTrainRushHour + passengerSizeTrainNormal)*100/(busCapacityRushHour + busCapacityNormal + trainCapacityRushHour + trainCapacityNormal);        
        if(waitRiderNumberNormalRider!=0) {
            waitingTimeNormalRider = waitTimeNormalRider/waitRiderNumberNormalRider;}
        if(waitRiderNumberRushHourRider!=0) {
            waitingTimeRushHourRider = waitTimeRushHourRider/waitRiderNumberRushHourRider;}
        if((waitRiderNumberNormalRider + waitRiderNumberRushHourRider)!=0) {
            waitingTimeAverageRider = (waitTimeNormalRider + waitTimeRushHourRider)/(waitRiderNumberNormalRider + waitRiderNumberRushHourRider);}
      
        finalText = " The bus attendance rate during ordinary time is: " + String.valueOf(attendanceRateBusNormal) + "%.\n" +
        		    " The bus attendance rate during rush hour is: " + String.valueOf(attendanceRateBusRushHour) + "%.\n" +
        		    " The average bus attendance rate is: " + String.valueOf(attendanceRateBusAverage) + "%.\n" +
        		    " The train attendance rate during ordinary time is: " + String.valueOf(attendanceRateTrainNormal) + "%.\n" +
        		    " The train attendance rate during rush hour is: " +  String.valueOf(attendanceRateTrainRushHour) + "%.\n" +
        		    " The average train attendance rate is: " + String.valueOf(attendanceRateTrainAverage) + "%.\n" +
        		    " The total vehicle attendance rate during ordinary time is: " + String.valueOf(attendanceRateNormal) + "%.\n" +
        		    " The total vehicle attendance rate during rush hour is: " + String.valueOf(attendanceRateRushHour) + "%.\n" +
        		    " The average vehicle attendance rate is: " + String.valueOf(attendanceRateAverage) + "%.\n" + 
        		    " The rider average waiting time during ordinary time is: " + String.valueOf(waitingTimeNormalRider) + " min.\n" +
        		    " The rider average waiting time during rush hour is: " + String.valueOf(waitingTimeRushHourRider) + " min.\n" +
        		    " The rider average waiting time is: " + String.valueOf(waitingTimeAverageRider) + " min.\n" +
        		    " The total transffered rider number is: " + String.valueOf(waitRiderNumberNormalRider + waitRiderNumberRushHourRider) + ".\n";
        /*System.out.println("\n The bus attendance rate during ordinary time is: " + attendanceRateBusNormal + "%.\n");
        System.out.println(" The bus attendance rate during rush hour is: " + String.valueOf(attendanceRateBusRushHour) + "%.\n");
        
        System.out.println(" The average bus attendance rate is: " + attendanceRateBusAverage + "%.\n");
        System.out.println(" The train attendance rate during ordinary time is: " + String.valueOf(attendanceRateTrainNormal) + "%.\n");
        System.out.println(" The train attendance rate during rush hour is: " +  String.valueOf(attendanceRateTrainRushHour) + "%.\n");
        System.out.println(" The average train attendance rate is: " + String.valueOf(attendanceRateTrainAverage) + "%.\n");
        System.out.println(" The total vehicle attendance rate during ordinary time is: " + String.valueOf(attendanceRateNormal) + "%.\n");
        System.out.println(" The total vehicle attendance rate during rush hour is: " + String.valueOf(attendanceRateRushHour) + "%.\n");
        System.out.println(" The average vehicle attendance rate is: " + String.valueOf(attendanceRateAverage) + "%.\n");  
        System.out.println(" The rider average waiting time during ordinary time is: " + String.valueOf(waitingTimeNormalRider) + " min.\n");
        System.out.println(" The rider average waiting time during rush hour is: " + String.valueOf(waitingTimeRushHourRider) + " min.\n");
        System.out.println(" The rider average waiting time is: " + String.valueOf(waitingTimeAverageRider) + " min.\n");
        System.out.println(" The total transffered rider number is: " + String.valueOf(waitRiderNumberNormalRider + waitRiderNumberRushHourRider) + ".\n");*/
		return finalText;       
    }
    
    public void displayModel() {
    	ArrayList<MiniPair> busNodes, trainNodes, stopNodes;
    	MiniPairComparator compareEngine = new MiniPairComparator();
    	
    	int[] colorScale = new int[] {9, 29, 69, 89, 101};
    	Integer colorSelector, colorCount, colorTotal;
    	
    	try{
            // create new file access path
            String path="./mts_digraph.dot";
            File file = new File(path);

            // create the file if it doesn't exist
            if (!file.exists()) { file.createNewFile();}

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            //System.out.println("File opened!");
            bw.write("digraph G\n");
            bw.write("{\n");
            //System.out.println("Start writing!");
            busNodes = new ArrayList<MiniPair>();
            trainNodes = new ArrayList<MiniPair>();
            for (Bus b: buses.values()) { 
                busNodes.add(new MiniPair(b.getID(), b.getPassengers().size()));
            }
            for (Train b: trains.values()) { 
                trainNodes.add(new MiniPair(b.getID(), b.getPassengers().size())); 
            }
            Collections.sort(busNodes, compareEngine);
            Collections.sort(trainNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = busNodes.size() + trainNodes.size();
            for (MiniPair c: busNodes) {
            	    if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { 
            	        colorSelector++; 
            	    }
            	    Bus bus = buses.get(c.getID());
            	    int capacity = bus.getCapacity();
            	    bw.write("  bus" + c.getID() + " [ label=\"bus#" + c.getID() + " | " + c.getValue() + "/" + capacity + " riding\", shape=\"box\", color=\"" + "#0000cc" + "\"];\n");
            }
            bw.newLine();
            //System.out.println("Write bus!");
            for (MiniPair c: trainNodes) {
                if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { 
                    colorSelector++; 
                }
                Train train = trains.get(c.getID());
                int capacity = train.getCapacity();
                bw.write("  train" + c.getID() + " [ label=\"train#" + c.getID() + " | " + c.getValue() + "/" + capacity + " riding\", shape=\"box\", color=\"" + "#ff0000" + "\"];\n");
            }
            bw.newLine();
            //System.out.println("Write train!");
            stopNodes = new ArrayList<MiniPair>();
            for (Stop s: stops.values()) { stopNodes.add(new MiniPair(s.getID(), s.getWaiting().size())); }
            Collections.sort(stopNodes, compareEngine);
            
            colorSelector = 0;
            colorCount = 0;
            colorTotal = stopNodes.size();    	
            for (MiniPair t: stopNodes) {
            	
            	if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
            	Stop stop = stops.get(t.getID());
            	//System.out.println(stop.getName());
            	int waiting = stop.getAverageWaitingTime();
            	//System.out.println("There!");
            	bw.write("  stop" + t.getID() + " [ label=\"stop#" + t.getID() + " | " + t.getValue() + " waiting\n" + " Avg.waiting " + waiting+"min\", color=\"" +  "#000000"  + "\"];\n");
            }
            bw.newLine();
            
            for (Bus m: buses.values()) {
            	Integer prevStop = busRoutes.get(m.getRouteID()).getStopID(m.getPastLocation());
            	Integer nextStop = busRoutes.get(m.getRouteID()).getStopID(m.getLocation());
            	bw.write("  stop" + Integer.toString(prevStop) + " -> bus" + Integer.toString(m.getID()) + " [ label=\" dep\" ];\n");
            	bw.write("  bus" + Integer.toString(m.getID()) + " -> stop" + Integer.toString(nextStop) + " [ label=\" arr\" ];\n");
            }
            
            for (Train m: trains.values()) {
                Integer prevStop = railRoutes.get(m.getRouteID()).getStopID(m.getPastLocation());
                Integer nextStop = railRoutes.get(m.getRouteID()).getStopID(m.getLocation());
                bw.write("  stop" + Integer.toString(prevStop) + " -> train" + Integer.toString(m.getID()) + " [ label=\" dep\" ];\n");
                bw.write("  train" + Integer.toString(m.getID()) + " -> stop" + Integer.toString(nextStop) + " [ label=\" arr\" ];\n");
            }
            
            for (MiniPair stopPair: routesOnStop.keySet()) {
                Integer preStop = stopPair.getID();
                Integer nextStop = stopPair.getValue();
                bw.write("  stop" + Integer.toString(preStop) + " -> stop" + Integer.toString(nextStop) + " [ style=dotted];\n");
            }
        
            bw.write("}\n");
            bw.close();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
    
package edu.gatech;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TransitionSystem {
    private HashMap<Integer, Stop> stops;
    private HashMap<Integer, BusRoute> busRoutes;
    private HashMap<Integer, RailRoute> railRoutes;
    private HashMap<Integer, Train> trains;
    private HashMap<Integer, Bus> buses;

    public TransitionSystem() {
        stops = new HashMap<Integer, Stop>();
        busRoutes = new HashMap<Integer, BusRoute>();
        railRoutes = new HashMap<Integer, RailRoute>();
        trains = new HashMap<Integer, Train>();
        buses = new HashMap<Integer, Bus>();
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

    public int makeStop(int uniqueID, String inputName, int inputRiders, double inputXCoord, double inputYCoord) {
        // int uniqueID = stops.size();
        ArrayList<Rider> inputRiderList = generateRiders(uniqueID, inputRiders);
        stops.put(uniqueID, new Stop(uniqueID, inputName, inputRiderList, inputXCoord, inputYCoord));
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

    public int makeBus(int uniqueID, int inputRoute, int inputLocation, int inputPassengers, int inputCapacity, int inputSpeed) {
        // int uniqueID = buses.size();
        ArrayList<Rider> inputPassengerList = generateRiders(uniqueID, inputPassengers);
        buses.put(uniqueID, new Bus(uniqueID, inputRoute, inputLocation, inputPassengerList, inputCapacity, inputSpeed));
        return uniqueID;
    }
    
    public int makeTrain(int uniqueID, int inputRoute, int inputLocation, int inputPassengers, int inputCapacity, int inputSpeed) {
        // int uniqueID = buses.size();
        ArrayList<Rider> inputPassengerList = generateRiders(uniqueID, inputPassengers);
        trains.put(uniqueID, new Train(uniqueID, inputRoute, inputLocation, inputPassengerList, inputCapacity, inputSpeed));
        return uniqueID;
    }
    
    public void setRoadCondition(int busRouteID, int stopID1, int stopID2, Double distance, int lowestSpeed, int averageSpeed, int maxSpeed) {
        BusRoute busRoute = getBusRoute(busRouteID);
        RoadCondtion roadCondition = new RoadCondtion(distance, lowestSpeed, averageSpeed, maxSpeed);
        busRoute.addRoadCondition(roadCondition, stopID1, stopID2);
    }
    
    public void setRailStopDistance(int railRouteID, int stopID1, int stopID2, Double distance) {
        RailRoute railRoute = getRailRoute(railRouteID);     
        railRoute.addStopDistance(stopID1, stopID2, distance);
    }

    public void appendStopToBusRoute(int routeID, int nextStopID) { busRoutes.get(routeID).addNewStop(nextStopID); }
    
    public void appendStopToRailRoute(int routeID, int nextStopID) { railRoutes.get(routeID).addNewStop(nextStopID); }


    public HashMap<Integer, Stop> getStops() { return stops; }

    public HashMap<Integer, BusRoute> getBusRoutes() { return busRoutes; }

    public HashMap<Integer, Bus> getBuses() { return buses; }
    
    public HashMap<Integer, RailRoute> getRailRoutes() { return railRoutes; }

    public HashMap<Integer, Train> getTrains() { return trains; }
    
    public ArrayList<Rider> generateRiders(int stopID, int inputNumbers) {
        ArrayList<Rider> riderList = new ArrayList<Rider>();
        
        
        return riders;
    }
  
    
    public void displayModel() {
        ArrayList<MiniPair> busNodes, stopNodes;
        MiniPairComparator compareEngine = new MiniPairComparator();
        
        int[] colorScale = new int[] {9, 29, 69, 89, 101};
        String[] colorName = new String[] {"#000077", "#0000FF", "#000000", "#770000", "#FF0000"};
        Integer colorSelector, colorCount, colorTotal;
        
        try{
            // create new file access path
            String path="./mts_digraph.dot";
            File file = new File(path);

            // create the file if it doesn't exist
            if (!file.exists()) { file.createNewFile();}

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("digraph G\n");
            bw.write("{\n");
        
            busNodes = new ArrayList<MiniPair>();
            for (Bus b: buses.values()) { busNodes.add(new MiniPair(b.getID(), b.getPassengers())); }
            Collections.sort(busNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = busNodes.size();
            for (MiniPair c: busNodes) {
                if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
                bw.write("  bus" + c.getID() + " [ label=\"bus#" + c.getID() + " | " + c.getValue() + " riding\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            
            stopNodes = new ArrayList<MiniPair>();
            for (BusStop s: stops.values()) { stopNodes.add(new MiniPair(s.getID(), s.getWaiting())); }
            Collections.sort(stopNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = stopNodes.size();      
            for (MiniPair t: stopNodes) {
                if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
                bw.write("  stop" + t.getID() + " [ label=\"stop#" + t.getID() + " | " + t.getValue() + " waiting\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            
            for (Bus m: buses.values()) {
                Integer prevStop = routes.get(m.getRouteID()).getStopID(m.getPastLocation());
                Integer nextStop = routes.get(m.getRouteID()).getStopID(m.getLocation());
                bw.write("  stop" + Integer.toString(prevStop) + " -> bus" + Integer.toString(m.getID()) + " [ label=\" dep\" ];\n");
                bw.write("  bus" + Integer.toString(m.getID()) + " -> stop" + Integer.toString(nextStop) + " [ label=\" arr\" ];\n");
            }
        
            bw.write("}\n");
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}

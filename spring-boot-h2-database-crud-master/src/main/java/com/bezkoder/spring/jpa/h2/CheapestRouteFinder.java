package com.bezkoder.spring.jpa.h2;

import java.util.*;

class Accelerator {
    private final String id;
    private final Map<String, Integer> connections;

    public Accelerator(String id) {
        this.id = id;
        this.connections = new HashMap<>();
    }

    public void addConnection(String acceleratorID, int distance) {
        connections.put(acceleratorID, distance);
    }

    public String getID() {
        return id;
    }

    public Map<String, Integer> getConnections() {
        return connections;
    }
}

class Graph {
    private final Map<String, Accelerator> accelerators;

    public Graph() {
        this.accelerators = new HashMap<>();
    }

    public void addAccelerator(Accelerator accelerator) {
        accelerators.put(accelerator.getID(), accelerator);
    }

    public List<String> findCheapestRoute(String startID, String targetID) {
        if (!accelerators.containsKey(startID) || !accelerators.containsKey(targetID)) {
            return Collections.emptyList(); // No valid route exists
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String acceleratorID : accelerators.keySet()) {
            if (acceleratorID.equals(startID)) {
                distances.put(acceleratorID, 0);
            } else {
                distances.put(acceleratorID, Integer.MAX_VALUE);
            }
            previous.put(acceleratorID, null);
            queue.offer(acceleratorID);
        }

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(targetID)) {
                break; // Found the target accelerator, exit the loop
            }

            Accelerator currentAccelerator = accelerators.get(current);
            for (Map.Entry<String, Integer> entry : currentAccelerator.getConnections().entrySet()) {
                String neighborID = entry.getKey();
                int distanceToNeighbor = entry.getValue();
                int totalDistance = distances.get(current) + distanceToNeighbor;

                if (totalDistance < distances.get(neighborID)) {
                    distances.put(neighborID, totalDistance);
                    previous.put(neighborID, current);
                    queue.remove(neighborID); // Remove and re-add to update the priority
                    queue.offer(neighborID);
                }
            }
        }

        if (previous.get(targetID) == null) {
            return Collections.emptyList(); // No valid route exists
        }

        List<String> route = new ArrayList<>();
        String currentID = targetID;

        while (currentID != null) {
            route.add(currentID);
            currentID = previous.get(currentID);
        }

        Collections.reverse(route);
        return route;
    }
}

public class CheapestRouteFinder {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Accelerator sol = new Accelerator("SOL");
        sol.addConnection("RAN", 100);
        sol.addConnection("PRX", 90);
        sol.addConnection("SIR", 100);
        sol.addConnection("ARC", 200);
        sol.addConnection("ALD", 250);
        graph.addAccelerator(sol);

        Accelerator prx = new Accelerator("PRX");
        prx.addConnection("SOL", 90);
        prx.addConnection("SIR", 100);
        prx.addConnection("ALT", 150);
        graph.addAccelerator(prx);

        Accelerator sir = new Accelerator("SIR");
        sir.addConnection("SOL", 80);
        sir.addConnection("PRX", 10);
        sir.addConnection("CAS", 200);
        graph.addAccelerator(sir);

        // Add other accelerators and their connections here...

        String startID = "SOL";
        String targetID = "ALT";
        List<String> route = graph.findCheapestRoute(startID, targetID);

        if (route.isEmpty()) {
            System.out.println("No valid route exists from " + startID + " to " + targetID);
        } else {
            System.out.println("Cheapest route from " + startID + " to " + targetID + ":");
            for (String acceleratorID : route) {
                System.out.println(acceleratorID);
            }
        }
    }
}

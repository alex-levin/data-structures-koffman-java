package KW.CH04.AirlineSim;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Scanner;

/** AirlineCheckinSim.java
 *  Simulate the check-in process of an airline.
 *  @author Koffman and Wolfgang
 */
public class AirlineCheckinSim {

    // Data fields
    /** Queue of frequent flyers */
    private PassengerQueue frequentFlyerQueue =
            new PassengerQueue("Frequent Flyer");
    /** Queue of regular passengers */
    private PassengerQueue regularPassengerQueue =
            new PassengerQueue("Regular Passenger");
    /** Maximum number of frequent flyers to be served 
     *  before a regular passenger gets served */
    private int frequentFlyerMax;
    /** The maximum time to service a passenger */
    private int maxPocessingTime;
    /** Total simulated time */
    private int totalTime;
    /** If set true, print additional output */
    private boolean showAll;
    /** Simulated clock */
    private int clock = 0;
    /** Time that the agent is done with the current passenger */
    private int timeDone;
    /** Number of frequent flyers served since the
     *  last regular passenger was served */
    private int frequentFlyersSinceRP;

    /**
     * Main method
     * @param args optional file name for simulation parameters */
    public static void main(String args[]) {
        AirlineCheckinSim sim = new AirlineCheckinSim();
        if (args.length > 0) {
            sim.enterData(args[0]);
        } else {
            sim.enterData();
        }
        sim.runSimulation();
        sim.showStats();
        System.exit(0);
    }

// Insert solution to programming exercise 3, section 5, chapter 4 here
// Insert solution to programming exercise 2, section 5, chapter 4 here

    /** Method to start serving a customer */
    private void startServe() {
        if (!frequentFlyerQueue.isEmpty()
                && ((frequentFlyersSinceRP <= frequentFlyerMax)
                || regularPassengerQueue.isEmpty())) {
            // Serve the next frequent flyer.
            frequentFlyersSinceRP++;
            timeDone = frequentFlyerQueue.update(clock, showAll);
        } else if (!regularPassengerQueue.isEmpty()) {
            // Serve the next regular passenger.
            frequentFlyersSinceRP = 0;
            timeDone = regularPassengerQueue.update(clock, showAll);
        } else if (showAll) {
            System.out.println("Time is " + clock + ":  Server is idle");
        }
    }

    /** Method to show the statistics. */
    private void showStats() {
        System.out.println("\nThe number of regular passengers served was "
                + regularPassengerQueue.getNumServed());
        double averageWaitingTime =
                (double) regularPassengerQueue.getTotalWait()
                / (double) regularPassengerQueue.getNumServed();
        System.out.println(" with an average waiting time of "
                + averageWaitingTime);
        System.out.println("The number of frequent flyers served was "
                + frequentFlyerQueue.getNumServed());
        averageWaitingTime =
                (double) frequentFlyerQueue.getTotalWait()
                / (double) frequentFlyerQueue.getNumServed();
        System.out.println(" with an average waiting time of "
                + averageWaitingTime);
        System.out.println("Passengers in frequent flyer queue: "
                + frequentFlyerQueue.size());
        System.out.println("Passengers in regular queue: "
                + regularPassengerQueue.size());

    }

    /** Method to run the simulation */
    private void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
            frequentFlyerQueue.checkNewArrival(clock, showAll);
            regularPassengerQueue.checkNewArrival(clock, showAll);
            if (clock >= timeDone) {
                startServe();
            }
        }
    }

    Queue<int[]> parseArrivalTimes(String arrivalTimesString) {
        Queue<int[]> result = new ArrayDeque<>();
        String[] arrivalTimes = arrivalTimesString.trim().split("[\\[\\]]+");
        for (String s : arrivalTimes) {
            if (!s.isEmpty()) {
                String[] pair = s.split("\\s+");
                int first = Integer.parseInt(pair[0]);
                int second = Integer.parseInt(pair[1]);
                result.add(new int[]{first, second});
            }
        }
        return result;
    }
}

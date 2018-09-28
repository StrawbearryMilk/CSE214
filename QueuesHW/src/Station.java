//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

import java.util.ArrayList;

/**
 * This class simulates the train station
 * @author Jeffrey Rodriguez
 */
public class Station {
    private PassengerQueue firstClass;
    private PassengerQueue secondClass;
    private int totalServed = 0;
    private BooleanSource firstArrival;
    private BooleanSource secondArrival;
    private Passenger p1;
    private Passenger p2;
    private String name;
    private ArrayList<Integer> pickupTimesFirst;
    private ArrayList<Integer> pickupTimesSecond;


    
    /**
     * Constructs a station
     * @param firstArrival Probability of arrival occurance for first class passengers
     * @param secondArrival Probability of arrival occurance for second class passengers
     * @param name Name of the station
     */
    public Station(BooleanSource firstArrival, BooleanSource secondArrival, String name) {
        this.firstArrival = firstArrival;
        this.secondArrival = secondArrival;
        this.name = name;
        firstClass = new PassengerQueue();
        secondClass = new PassengerQueue();
        pickupTimesFirst = new ArrayList();
        pickupTimesSecond = new ArrayList();
    }
    

    /**
     * Tests to see if a first and second class passenger may be enqueued at some station
     */
    public void simulateTimeStep(){
        if (firstArrival.occurs()){
            p1 = new Passenger(LIRRSimulator.id++, LIRRSimulator.currTime, true);
            System.out.println("First class passenger ID " + p1.getId() + " arrives.");
            firstClass.enqueue(p1);
        }
        else System.out.println("No first class passenger arrives.");
        
        if (secondArrival.occurs()){
            p2 = new Passenger(LIRRSimulator.id++, LIRRSimulator.currTime, true);
            System.out.println("Second class passenger ID " + p2.getId() + " arrives.");
            secondClass.enqueue(p2);
        }
        else System.out.println("No second class passenger arrives.");
        System.out.println("Queues:");
        System.out.println(firstClass.toString());
        System.out.println(secondClass.toString());
    }

    
    /**
     * Returns first class passenger queue
     * @return The first class passenger queue
     */
    public PassengerQueue getFirstClass() {
        return firstClass;
    }

    /**
     * Sets the first class passenger queue
     * @param firstClass Passenger queue for first class riders
     */
    public void setFirstClass(PassengerQueue firstClass) {
        this.firstClass = firstClass;
    }

    /**
     * Returns the second class passenger queue
     * @return The second class passenger queue
     */
    public PassengerQueue getSecondClass() {
        return secondClass;
    }

    /**
     * Sets the second class passenger queue
     * @param secondClass The second class passenger queue
     */
    public void setSecondClass(PassengerQueue secondClass) {
        this.secondClass = secondClass;
    }

    /**
     * Returns the name of the train station
     * @return Name of the train station
     */
    public String getName(){
        return this.name;
    }

    /**
     * Print method for the summarizing amount of passengers served, left seatless, and pick up times.
     */
    public void summarize() {

        int sumFirst = 0, sumSecond = 0;
        for (int i = 0; i < pickupTimesFirst.size(); i++) {
            sumFirst += pickupTimesFirst.get(i);
        }
        for (int i = 0; i < pickupTimesSecond.size(); i++) { 
            sumSecond += pickupTimesSecond.get(i);
        }
        int avgFirst = 0, avgSecond = 0, avgTotal = 0;
        if (sumFirst != 0)
            avgFirst = Math.round(sumFirst/pickupTimesFirst.size());
        if (sumSecond != 0)
            avgSecond = Math.round(sumSecond/pickupTimesSecond.size());

        System.out.println("At " + name + " " + totalServed + " passangers were served, "
                + firstClass.size() + " first class passangers were left without a seat, "
                + secondClass.size() + " second class passangers were left without a seat. "
                + "The average first class waiting time was " + avgFirst + " min. "
                + "The average second class waiting time was " + avgSecond + " min."
        );
    }

    /**
     * Increments the number of total passengers served
     */
    public void incrServed() {
        totalServed++;
    }

    /**
     * ArrayList of first class pickup times
     * @return ArrayList of first class pickup times
     */
    public ArrayList getPickupFirst() {
        return pickupTimesFirst;
    }

    /**
     * ArrayList of second class pickup times
     * @return ArrayList of second class pickup times
     */
     public ArrayList getPickupSecond() {
        return pickupTimesSecond;
    }

    /**
     * Returns total amount of passengers served
     * @return Total amount of passengers served
     */
    public int getTotalServed() {
        return totalServed;
    }
}

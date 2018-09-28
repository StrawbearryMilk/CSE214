//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

/**
 * This class simulates the train
 * @author Jeffrey Rodriguez
 */
public class Train {
    private boolean done;
    private int number;
    private int firstCapacity;
    private int secondCapacity;
    private int timeUntilNextArrival; //time until next Arrival
    private int currentStation = 0;
    private PassengerQueue fQueue;
    private PassengerQueue sQueue;

    /**
     * Constructor for train object
     * @param firstCapacity Capacity for first class passengers
     * @param secondCapacity Capacity for second class passengers
     * @param timeUntilNextArrival Time until train arrives at next station
     * @param number Number representing the train
     */
    public Train(int firstCapacity, int secondCapacity, int timeUntilNextArrival, int number){
        done = false;
        this.firstCapacity = firstCapacity;
        this.secondCapacity = secondCapacity;
        this.timeUntilNextArrival = timeUntilNextArrival;
        this.number = number;
        fQueue = new PassengerQueue();        
        sQueue = new PassengerQueue();
    }

    /**
     * Represents the time step for  the train
     */
    public void simulateTimeStep(){
        if (!done) {
            if (currentStation > 3) {
                    System.out.println("Train " + number + " stopped picking passangers.");
                    done = true;
            }
            else if (timeUntilNextArrival == 0) {
                Station tempSt = LIRRSimulator.stations[currentStation];
                PassengerQueue firstQueue = tempSt.getFirstClass();
                PassengerQueue secondQueue = tempSt.getSecondClass();
                PassengerQueue temp1 = new PassengerQueue();
                PassengerQueue temp2 = new PassengerQueue();
                while (firstQueue.size() > 0) {
                    if (firstCapacity > 0) {
                        Passenger p = firstQueue.dequeue();
                        fQueue.enqueue(p);
                        temp1.enqueue(p);
                        tempSt.getPickupFirst().add(LIRRSimulator.currTime - p.getArrivalTime());
                        tempSt.incrServed();
                        firstCapacity--;
                    } else if (secondCapacity > 0) {
                        Passenger p = firstQueue.dequeue();
                        tempSt.getPickupFirst().add(LIRRSimulator.currTime - p.getArrivalTime());
                        tempSt.incrServed();
                        sQueue.enqueue(p);
                        temp2.enqueue(p);
                        secondCapacity--;
                    } else {
                        break;
                    }
                }
                while (secondQueue.size() > 0) {
                    if (secondCapacity > 0) {
                        Passenger p = secondQueue.dequeue();
                        fQueue.enqueue(p);
                        temp2.enqueue(p);
                        tempSt.getPickupSecond().add(LIRRSimulator.currTime - p.getArrivalTime());
                        tempSt.incrServed();
                        secondCapacity--;
                    } else {
                        break;
                    }
                }
                arrivalInfo(temp1, temp2);
                timeUntilNextArrival = 5;
                currentStation++;
            } else {
                awaitingInfo();
            }
        }
        else System.out.println("Train " + number + " stopped picking passangers.");
    }

    /**
     * Print method stating which train will arrive at some station in x minutes.
     */
    public void awaitingInfo(){
        System.out.println("Train " + number
                + " will arrive in " + timeUntilNextArrival
                + " at " + LIRRSimulator.stNames[currentStation]
                + (timeUntilNextArrival == 1 ? " minute.": " minutes."));
    }

    /**
     * Print method describing which passengers are embarking on the train
     * @param a First class passenger queue
     * @param b Second class passenger queue
     */
    public void arrivalInfo(PassengerQueue a, PassengerQueue b ){
        System.out.println("Train " + number
                + " arrives at " + LIRRSimulator.stNames[currentStation]
                + ", there are  " + getNumFirst() + " in the first class and "
                + getNumSecond() + " in second class.");
        System.out.print("Passengers embarking in first class:");
        System.out.println(a.toString());
        System.out.print("Passengers embarking in second class:");
        System.out.println(b.toString());
         
    }
    

    /**
     * Obtains the number of first class passengers on this train
     * @return Number of first class passengers on train
     */
    
    public int getNumFirst(){
        return LIRRSimulator.firstCapacity - this.firstCapacity;
    }
    
    /**
     * Obtains the number of second class passengers on this train
     * @return Number of second class passengers on train
     */
    public int getNumSecond(){
        return LIRRSimulator.secondCapacity - this.secondCapacity;
    }

    /**
     * Decrements time until the train arrives at a station
     */
    public void decTimeUntilNextArrival() {
        timeUntilNextArrival--;
    }

    /**
     * This method sees if the train has reached its destination or not
     * @return True if the train has arrived at destination, false otherwise
     */
    public boolean isDone() {
        return done;
    }
}

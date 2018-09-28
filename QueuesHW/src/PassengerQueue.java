//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class extends Array List and contains the passenger queue methods
 * @author Jeffrey Rodriguez
 */
public class PassengerQueue extends ArrayList<Passenger>{

    /**
     * Adds passenger to queue
     * @param p Passenger object
     */
    public void enqueue(Passenger p){
        this.add(p);
    }

    /**
     * Removes passenger from queue
     * @return Dequeued passenger
     */
    public Passenger dequeue(){
        if (this.size() > 0) return this.remove(0);
        else return null;
    }

    /**
     * Returns a string representation of the queue
     * @return A string representation of the queue
     */
    public String toString() {
        
        if (isEmpty()) return "[Empty]";
        String passengers = "";
        for (int i = 0; i < size(); i++) {
            Passenger p;
                p = this.get(i);
                if (i < size() - 1) {
                    passengers += "P" + p.getId() + "@" + "T" + p.getArrivalTime() + ", ";
                }
                else passengers += "P" + p.getId() + "@" + "T" + p.getArrivalTime();
        }
        return passengers;
    }

    /**
     * Exception thrown when the queue is empty
     */
    public class EmptyQueueException extends Exception{}

    /**
     * Returns Passenger at the front of the queue
     * @return Passenger at the front of the queue
     * @throws EmptyQueueException Thrown if the queue is empty
     */
    public Passenger peek() throws EmptyQueueException{
        if (this.isEmpty())
            throw new EmptyQueueException();
        return this.get(0);
    }

    /**
     * Checks to see if queue is empty
     * @return True if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
        return this.size() > 0 ? false : true;
    }
}
//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

/**
 * This class creates the passenger object
 * @author Jeffrey Rodriguez
 */
public class Passenger {
    private int id;
    private int arrivalTime;
    private boolean isFirstClass;

    /**
     * Default constructor for Passenger
     */
    public Passenger(){
        id = 0;
        arrivalTime = 0;
        isFirstClass = false;
    }

    /**
     * Constructor for passenger
     * @param id ID of passenger
     * @param arrivalTime Arrival time of passenger
     * @param isFirstClass Checks to see if the passenger first class or not
     */
    public Passenger(int id, int arrivalTime, boolean isFirstClass){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.isFirstClass = isFirstClass;
    }

    /**
     * Obtains ID of passenger
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets ID of passenger
     * @param id ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtains arrival time of train
     * @return The arrival time of train
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets arrival time for train
     * @param arrivalTime Arrival time for train
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Tests to see if passenger is first class
     * @return True if passenger is first class, false otherwise
     */
    public boolean isFirstClass() {
        return isFirstClass;
    }

    /**
     * Sets passenger to first class or not
     * @param firstClass Use to set passenger to first class, or not first class
     */
    public void setFirstClass(boolean firstClass) {
        isFirstClass = firstClass;
    }
}
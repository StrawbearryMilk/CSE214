//Jeffrey Rodriguez 110733867
//CSE214.R14 HW2

/**
 * This class represents a delivery system with a source, destination, and instructions.
 * @author Jeffrey Rodriguez
 */
public class Delivery {
    private String source;
    private String dest;
    private String instruction;

    /**
     * This creates a default constructor for Delivery.
     */
    public Delivery(){
        this.source = "";
        this.dest = "";
        this.instruction = "";
    }

    /**
     * This creates a constructor for Delivery.
     */
    public Delivery(String source, String dest, String instruction){
        this.source = source;
        this.dest = dest;
        this.instruction = instruction;
    }

    /**
     * This method obtains the delivery source.
     * @return
     * The source for the delivery.
     */
    public String getSource() {
        return source;
    }

    /**
     * The method sets the delivery source.
     * @param source
     * Source of delivery.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method obtains the delivery destination.
     * @return
     * Destination of delivery.
     */
    public String getDest() {
        return dest;
    }

    /**
     * This method sets the delivery destination.
     * @param dest
     * Destination of the delivery.
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

    /**
     * This method obtains instructions for the delivery.
     * @return
     * Instructions for the delivery.
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * This method sets the instructions for delivery.
     * @param instruction
     * Instructions for delivery.
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }


    /**
     * This method returns a string representation of this object.
     * @return
     * A string representation of the object.
     */
    public String toString() {
        return "To: " + dest + " | From: " + source +
                "\n Instruction: " + instruction;
    }
}
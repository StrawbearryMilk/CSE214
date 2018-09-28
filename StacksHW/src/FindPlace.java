//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the button F: Find a Place button in Maps app
 * @author Jeffrey Rodriguez
 */
public class FindPlace implements Command{

    private String destination;

    /**
     * FindPlace constructor
     * @param scanner User input to set the destination
     */
    public FindPlace(Scanner scanner){
        System.out.print("Please enter a location: ");
        this.destination = scanner.nextLine();
    }

    /**
     * Checks to see if command can be pushed onto stack
     * @param stack Stack to test if the command can be pushed onto it or not
     * @return
     * true if the FindPlace command can be pushed onto this stack
     */
    public boolean validCommand(CommandStack stack){
        return true;
    }

    /**
     * Returns the String representation of this Command in long form (for current screen display)
     * @return
     * String representation of this Command in long form (for current screen display)
     */
    public String toString(){
        return "Showing results for: " + destination;
    }

    /**
     * Returns the String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString(){
        return "F:" + destination;
    }
}
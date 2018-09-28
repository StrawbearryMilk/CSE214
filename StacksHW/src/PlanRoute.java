//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the “P: Plan a route” command for the Maps app.
 */
public class PlanRoute implements Command{
    private String source;
    private String destination;

    /**
     * PlanRoute constructor
     * @param scanner User input to construct PlanRoute instance.
     */
    public PlanRoute(Scanner scanner){
        System.out.print("Enter a source: ");
        this.source = scanner.nextLine();
        System.out.print("Enter a destination: ");
        this.destination = scanner.nextLine();
    }
    /**
     * Checks to see if command can be pushed onto stack
     * @param stack Stack to test if the command can be pushed onto it or not
     * @return
     * true if the PlanRoute command can be pushed onto this stack
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
        return "Planning route from " + source + " to " + destination;
    }

    /**
     * Returns the String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString(){
        return "P:" + source + "-" + destination;
    }
}

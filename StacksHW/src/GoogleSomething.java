//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the “G: Google something" command for the Safari app.
 */
public class GoogleSomething implements Command {
    private String query;

    /**
     * GoogleSomething constructor
     * @param scanner User input to construct GoogleSomething instance.
     */
    public GoogleSomething(Scanner scanner){
        System.out.print("Enter your query: ");
        this.query = scanner.nextLine();
    }

    /**
     * Checks to see if command can be pushed onto stack
     * @param stack Stack to test if the command can be pushed onto it or not
     * @return
     * true if the GoogleSomething command can be pushed onto this stack
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
        return "Google: " + query;
    }

    /**
     * Returns the String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString(){
        return "G:" + query;
    }
}

//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the “N: Start Navigation” command for the Maps app.
 */
public class StartNavigation implements Command{
    private Command command;
    private String string;
    /**
     * StartNavigation constructor
     * @param stack Sets item at the top of the stack as the command for this stack
     */
    public StartNavigation(CommandStack stack){
        try{this.command = stack.peek();}
        catch (CommandStack.EmptyStackException ex){}
    }

    /**
     * Checks to see if command can be pushed onto stack
     * @param stack Stack to test if the command can be pushed onto it or not
     * @return
     * true if the StartNavigation command can be pushed onto this stack
     */
    public boolean validCommand(CommandStack stack){
        Command peeked;
        try {
            peeked = stack.peek();
        }
        catch (CommandStack.EmptyStackException ex){
            return false;
        }
        if (peeked instanceof StartNavigation)
            return false;
        return true;
    }

    /**
     * Returns the String representation of this Command in long form (for current screen display)
     * @return
     * String representation of this Command in long form (for current screen display)
     */
    public String toString(){
        return "Navigating to " + command;
    }

    /**
     * Returns the String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString(){
        return "N:" + command;
    }
}
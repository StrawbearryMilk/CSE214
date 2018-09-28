//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.ArrayList;

/**
 * This creates the stack data structure used for the phone app.
 * @author Jeffrey Rodriguez
 */
public class CommandStack{
    private ArrayList<Command> stack;
    protected String string;

    /**
     * Default constructor for CommandStack.
     */
    public CommandStack(){
        this.stack = new ArrayList<>();
        string = "[Home";
    }
    /**
     * Exception to be thrown if command is invalid.
     */
    public class InvalidCommandException extends Exception{}
    /**
     * This method pushes the command on top of the stack
     * @param command Command to be added to stack
     * @throws InvalidCommandException Thrown if an invalid command is input
     */
    public void push(Command command) throws InvalidCommandException{
        if (!command.validCommand(this))
            throw new InvalidCommandException();
        string+="->" + command.toShortString();
        stack.add(command);
    }

    /**
     * Exception to be thrown when the stack is empty
     */
    public class EmptyStackException extends Exception{}

    /**
     * This method pops the command at the top of the stack
     * @return
     * Command popped from the stack
     * @throws EmptyStackException
     * Thrown if the stack is empty
     */
    public Command pop() throws EmptyStackException{
        if (this.isEmpty())
            throw new EmptyStackException();
        string = string.substring(0,string.lastIndexOf("->"));
        return stack.remove(stack.size() - 1);
    }

    /**
     * This method displays the command at the top of the stack
     * @return
     * Command at the top of the stack
     * @throws EmptyStackException
     * Thrown if the stack is empty
     */
    public Command peek() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();
        return stack.get((stack.size() - 1));
    }

    /**
     * This method checks to see if the stack is empty
     * @return
     * True if the stack is empty, false otherwise.
     */
    public boolean isEmpty(){
        if (stack.size() == 0)
            return true;
        return false;
    }

    /**
     * Returns a String representation of the command that will be displayed on the screen.
     * @return
     * a String representation of the command that will be displayed on the screen.
     */
    public String getScreenCommand(){
        return stack.get(stack.size() - 1).toString();
    }

    /**
     * Creates a string representation for the string instance variable
     * @return
     * A string representation for the string instance variable
     */
    public String toString(){
        return string;
    }
}
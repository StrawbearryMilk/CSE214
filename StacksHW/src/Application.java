//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the Application for the app.
 * @author Jeffrey Rodriguez
 */
public abstract class Application{
    private CommandStack stack;
    private Command home;

    /**
     * Reads in user input to construct command and add it to the stack
     * @param scanner User input to construct new command
     * @exception CommandStack.InvalidCommandException thrown if command is invalid
     */
    protected void readCommand(Scanner scanner) throws CommandStack.InvalidCommandException{
    }

    /**
     * Returns stack to its state before previous command was put in
     * @exception CommandStack.EmptyStackException thrown if stack is empty
     */
    protected void goBack() {}
}

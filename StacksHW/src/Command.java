//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

/**
 * This interface represents the commands to be entered on a phone app
 * @author Jeffrey Rodriguez
 */
public interface Command {
    /**
     * Checks to see if command is valid and can be added to stack
     * @param stack Command to be tested for validity
     * @return
     * True if the command is valid, false otherwise
     */
    public boolean validCommand(CommandStack stack);

    /**
     * Creates String representation of this Command in long form (for current screen display)
     * @return
     * String representation of this Command in long form (for current screen display)
     */
    public String toString();

    /**
     * Creates String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString();
}

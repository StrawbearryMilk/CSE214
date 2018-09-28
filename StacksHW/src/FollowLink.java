//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the “L: FollowLink" command for the Safari app.
 */
public class FollowLink implements Command{
    private String link;

    /**
     * Constructor for FollowLink command
     * @param scanner User input to construct FollowLink instance
     */
    public FollowLink(Scanner scanner){
        System.out.print("Please enter a link: ");
        this.link = scanner.nextLine();
    }

    /**
     * Checks to see if command can be pushed onto stack
     * @param stack Stack to test if the command can be pushed onto it or not
     * @return
     * true if the GoToBookmark command can be pushed onto this stack
     */
    public boolean validCommand(CommandStack stack){
        if (stack.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Returns the String representation of this Command in long form (for current screen display)
     * @return
     * String representation of this Command in long form (for current screen display)
     */
    public String toString(){
        return link;
    }

    /**
     * Returns the String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString(){
        return "L:" + link;
    }

}

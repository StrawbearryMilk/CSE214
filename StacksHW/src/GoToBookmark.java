//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Represents the “F: Go to favourite/bookmark" command for the Safari app.
 */
public class GoToBookmark implements Command{
    private String bookmark;

    /**
     * GoToBookmark constructor
     * @param scanner User input to construct GoToBookmark instance.
     */
    public GoToBookmark(Scanner scanner){
        System.out.print("Enter the bookmark: ");
        this.bookmark = scanner.nextLine();
    }

    /**
     * Checks to see if command can be pushed onto stack
     * @param stack Stack to test if the command can be pushed onto it or not
     * @return
     * true if the GoToBookmark command can be pushed onto this stack
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
        return "Favorite: " + bookmark;
    }

    /**
     * Returns the String representation of this Command in short form (for stack display)
     * @return
     * String representation of this Command in short form (for stack display)
     */
    public String toShortString(){
        return "F:" + bookmark;
    }

}

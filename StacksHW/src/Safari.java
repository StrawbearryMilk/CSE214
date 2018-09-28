//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Parent class for the Safari commands
 * @author Jeffrey Rodriguez
 */
public class Safari extends Application{
    private CommandStack stack;

    /**
     * Default constructor for the Safari class
     */
    public Safari(){
        this.stack = new CommandStack();
        this.stack.string += "->SafariHome";
    }

    /**
     * This method is used to print the Safari menu options
     */
    private void printMenu() {
        System.out.print("\nCurrent Screen: Safari Home\n" +
                "Options:\n" +
                "\tG) Google Something\n" +
                "\tF) Go to a favorite bookmark\n" +
                "\tL) Follow a link\n" +
                "\tH) Home Screen\n" +
                "\tS) Switch to Maps\n" +
                "\tB) Back\n" +
                "Please select an option: ");
    }

    /**
     * Method used to read and construct Safari based commands for the stack
     * @param scanner User input to construct new command
     */
    protected void readCommand(Scanner scanner) {
        Command cmd = null;
        String sc;
        
        while (iCatchUp.menu == Menu.SAFARI) {
            debugStack();
            printMenu();
            sc = scanner.nextLine();
            switch (sc.toUpperCase().charAt(0)) {
                case 'G':
                    cmd = new GoogleSomething(scanner);
                    try {
                        stack.push(cmd);
                    } catch (CommandStack.InvalidCommandException ex) {
                        System.out.println("Command invalid!");
                    }
                    break;
                case 'F':
                    cmd = new GoToBookmark(scanner);
                    try {
                        stack.push(cmd);
                    } catch (CommandStack.InvalidCommandException ex) {
                        System.out.println("Command invalid!");
                    }
                    break;
                case 'L':
                    cmd = new FollowLink(scanner);
                    try {
                        stack.push(cmd);
                    } catch (CommandStack.InvalidCommandException ex) {
                        System.out.println("Command invalid!");
                    }
                    break;
                case 'B':
                    goBack();
                    break;
                case 'H':
                    System.out.println("Returning to Home Screen");
                    iCatchUp.menu = Menu.HOME;
                    iCatchUp.choice = 0;
                    break;
                case 'S':
                    System.out.println("Switching to Maps Menu");
                    iCatchUp.menu = Menu.MAPS;
                    iCatchUp.choice = 'M';
                    break;
                default:
                    System.out.println("Not an option!");
                    
            }
        }
        
    }

    /**
     * Returns stack to its state before previous command was put in
     * @exception CommandStack.EmptyStackException thrown if stack is empty
     */
    protected void goBack(){
        try {
            stack.pop();
        }
        catch (CommandStack.EmptyStackException ex) {
            System.out.println("Nothing to go back to.");
        }
    }

    /**
     * Contains short string returns for the Safari commands.
     */
    public void debugStack() {
        System.out.print("Stack Debug:\n" + stack.toString());
    }
}

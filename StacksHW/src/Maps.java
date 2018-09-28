//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Parent class for the Map commands
 * @author Jeffrey Rodriguez
 */
public class Maps extends Application {
    private CommandStack stack;

    /**
     * Default constructor for the Maps class
     */
    public Maps() {
        this.stack = new CommandStack();
        this.stack.string += "->MapsHome";
    }

    /**
     * This method is used to print the Maps menu options
     */
    private void printMenu() {
        System.out.print("\nCurrent Screen: Maps Home\n" +
                "Maps Options:\n" +
                "\tF) Find a place\n" +
                "\tP) Plan a route\n" +
                "\tN) Start Navigation\n" +
                "\tH) Home Screen\n" +
                "\tS) Switch to Safari\n" +
                "\tB) Back\n" +
                "Please select an option: ");
    }
    /**
     * Method used to read and construct Maps based commands for the stack
     * @param scanner User input to construct new command
     */
    protected void readCommand(Scanner scanner) {
        Command cmd;
        while (iCatchUp.menu == Menu.MAPS) {
            printMenu();
            String sc = scanner.nextLine();
            switch (sc.toUpperCase().charAt(0)) {
                case 'F':
                    cmd = new FindPlace(scanner);
                    try {
                        stack.push(cmd);
                    } catch (CommandStack.InvalidCommandException ex) {
                        System.out.println("Command invalid!");
                    }
                    break;
                case 'P':
                    cmd = new PlanRoute(scanner);
                    try {
                        stack.push(cmd);
                    } catch (CommandStack.InvalidCommandException ex) {
                        System.out.println("Command invalid!");
                    }
                    break;
                case 'N':
                    cmd = new StartNavigation(stack);
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
                    System.out.println("Switching to Safari Menu");
                    iCatchUp.menu = Menu.SAFARI;
                    iCatchUp.choice = 'S';
                    break;  
                default:
                    System.out.println("Not an option!");
            }
            debugStack();
        }
    }
    /**
     * Returns stack to its state before previous command was put in
     * @exception CommandStack.EmptyStackException thrown if stack is empty
     */
    protected void goBack() {
        try {
            stack.pop();
        } catch (CommandStack.EmptyStackException ex) {
            System.out.println("Nothing to go back to.");
        }
    }

    /**
     * Contains short string returns for the Safari commands.
     */
    public void debugStack() {
        System.out.println("Stack Debug:\n" + stack.toString());
    }
}
//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Main class for the Tree Application
 * @author Jeffrey Rodriguez
 */
public class NintendoNetwork {
    public static Scanner sc = new Scanner(System.in);
    public static NetworkTree tree;
    /**
     * Prints list of menu options
     */
    private static void printMenu() {
        System.out.println("MENU: ");
        System.out.println("\tL) Load from file");
        System.out.println("\tP) Print tree");
        System.out.println("\tC) Move cursor to a child node");
        System.out.println("\tR) Move cursor to root");
        System.out.println("\tU) Move cursor up to parent");
        System.out.println("\tA) Add a child");
        System.out.println("\tX) Remove/Cut Cursor and its subtree");
        System.out.println("\tV) Paste Cursor and its subtree");
        System.out.println("\tS) Save to file");
        System.out.println("\tQ) Quit");
    }

    public static void main(String[] args) {

        System.out.println("Welcome to the Nintendo Network Manager.");
        printMenu();
        String input;
        char choice;
        boolean stop = false;
        int index;

        while (!stop) {
            System.out.print("Please select an option: ");
            input = sc.nextLine();
            choice = input.charAt(0);
            choice = Character.toLowerCase(choice);
            String name;
            String s;
            switch (choice) {
                case 'l': //load file
                     {
                    System.out.print("Enter file name: ");
                    try {
                        tree = NetworkTree.readFromFile(sc.nextLine());
                    } catch (FileNotFoundException ex) {
                        System.out.println("Not a valid file");
                        break;
                    }
                    break;
            }
                case 'p':
                    tree.printTree(tree.getRoot());
                    break;

                case 'c': //cursor to child
                    if (tree.getCursor().numChildren() != 0) {
                        System.out.print("Please enter an index: ");
                        index = sc.nextInt();
                        sc.nextLine();
                        if (index >= tree.getCursor().maxChildren)
                            throw new IllegalArgumentException();
                        else if (tree.getCursor().getChildren()[index] == null)
                            System.out.println("There's no where to go...");
                        else {
                            tree.cursorToChild(index - 1);
                            System.out.println("Cursor moved to " + tree.getCursor().getName());
                        }
                    }
                    else
                        System.out.println("This node has no children!");
                    break;

                case 'a': //add child
                    System.out.print("Please enter an index: ");
                    index = sc.nextInt();
                    sc.nextLine();
                    index -= 1;
                    System.out.print("Please enter a device name: ");
                    name = sc.next();
                    System.out.print("Is this a Nintendo device (y/n)");
                    char isDevice = Character.toLowerCase(sc.next().charAt(0));
                    sc.nextLine();
                    if (!(isDevice == 'y' || isDevice == 'n') ){
                        System.out.println("Invalid input!");
                    }
                    else {
                        Boolean isNintendo = (isDevice == 'y') ? true : false;
                        NetworkNode newNode = new NetworkNode(name);
                        newNode.setNintendo(isNintendo);
                        try{
                            tree.addChild(index, newNode);
                        }
                        catch (NetworkTree.MaxChildrenException ex){
                            System.out.println("The Stony Brook cops are coming for you and your bad inputs!");
                        }
                        System.out.println(isDevice == 'y' ? "Nintendo device added!" : "Raspberry device added!");
                    }
                    break;

                case 'u': //cursor to parent
                    if (tree.getCursor() == null)
                        System.out.println("Nowhere to move to... Try making a new tree or reading one in.");
                    else if (tree.getCursor().getParent() != null) {
                        tree.cursorToParent();
                        System.out.println("Cursor is moved to " + tree.getCursor().getName());
                    }
                    else
                        System.out.println("Already at the root");
                    break;

                case 'x': //cut/delete
                    if (tree.getCursor() != null){
                        tree.cutCursor();
                        System.out.println(tree.copied.getName() + "has been cut. Cursor is now at " + tree.getCursor().getName());
                    }
                    else
                        System.out.println("Nothing to cut...");
                    break;

                case 'v': //paste subtree
                    if (tree.copied != null) {
                        System.out.println("Select an index to paste to: ");
                        index = sc.nextInt();
                        sc.nextLine();
                        tree.pasteNode(index - 1);
                    }
                    break;

                case 'r': //cursor to root
                    if (tree.getRoot() == null)
                        System.out.println("You might want to make a tree first.");
                    if (tree.getRoot() == tree.getCursor())
                        System.out.println("Cursor is already at the root...");
                    if (tree.getCursor().getParent() != null) {
                        while (tree.getCursor().getParent() != null) //move cursor to parent, until at root
                            tree.cursorToParent();
                        System.out.println("Cursor has been moved to " + tree.getCursor().getName());
                    }
                    break;

                case 's': //save as txt
                    if (tree != null){
                        System.out.print("Please enter a filename: ");
                        String filename = sc.next();
                        sc.nextLine();
                        tree.writeToFile(tree, filename);
                        System.out.println("File saved!");
                    }
                    else
                        System.out.println("Nothing to save.");
                    break;

                case 'q': //quit
                    stop = true;
                    break;

                default:
                    System.out.println("Unrecognized input.");
            }
        }
    }
}
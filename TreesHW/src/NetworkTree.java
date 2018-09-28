//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class serves as a manager for the network tree. It will hold references to the root node and cursor position.
 * It will also be able to generate and save the tree to and from a file.
 * @author Jeffrey Rodriguez
 */
public class NetworkTree {
    private static NetworkNode root;
    private static NetworkNode cursor;
    NetworkNode copied; //Used for cut method

    /**
     * Default constructor for NetworkTree
     */
    public NetworkTree() {
        root = null;
        cursor = null;
    }

    /**
     * NetworkTree constructor
     *
     * @param root   Root of the tree
     * @param cursor NetworkNode denoted as cursor
     */
    public NetworkTree(NetworkNode root, NetworkNode cursor) {
        this.root = root;
        this.cursor = cursor;
    }

    /**
     * Obtains root of the tree
     *
     * @return root of the tree
     */
    public static NetworkNode getRoot() {
        return root;
    }

    /**
     * Obtains the current cursor location
     *
     * @return Cursor location
     */
    public NetworkNode getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor to the root of the NetworkTree
     */
    public void cursorToRoot() {
        cursor = root;
    }

    /**
     * Removes the cursor and stores it to be pasted later
     *
     * @return NetworkNode removed from the tree
     */
    public NetworkNode cutCursor() {
        int position = 0; //Position of cursor in the array
        for (int i = 0; i < cursor.getParent().numChildren(); i++) {
            if (cursor == cursor.getParent().getChildren()[i]) {
                position = i;
                break;
            }
        }
        copied = getCursor();
        cursor.getParent().decCount();
        cursorToParent();
        cursor.getChildren()[position] = null;
        for (int i = position; i < cursor.getParent().numChildren(); i++){
            cursor.getParent().getChildren()[i] = cursor.getParent().getChildren()[i+1];
        }
        return copied;
    }

    /**
     * Used to paste node that was cut from
     *
     * @param index
     */
    public void pasteNode(int index) {
        if (copied == null) {
            System.out.println("Cut something first!");
        } else {
            NetworkNode pasted = new NetworkNode(copied.getName(), copied.isNintendo(), copied.getChildren(), copied.numChildren());
            try {
                addChild(index, pasted);
                cursorToChild(index);
            } catch (MaxChildrenException ex) {
                System.out.println("This node cannot have anymore kids.");
            }

        }
    }

    /**
     * Exception to be thrown if a node has the maximum number of children
     */
    public class MaxChildrenException extends Exception {
    }

    /**
     * Adds node as a child of the cursor at a given index
     *
     * @param index Position to place the node
     * @param node  NetworkNode object to be added as child
     * @throws MaxChildrenException Thrown when number of children is at maximum
     */
    public void addChild(int index, NetworkNode node) throws MaxChildrenException {
        if (index > cursor.numChildren())
            throw new IllegalArgumentException();

        if (cursor.numChildren() < cursor.maxChildren)
            if (index == cursor.numChildren()) {
                cursor.getChildren()[index] = node;
                cursor.incCount();
            } else if (cursor.getChildren()[index] != null) {
                for (int i = cursor.numChildren(); i >= index; i--) {
                    cursor.getChildren()[i] = cursor.getChildren()[i - 1];
                }
                cursor.getChildren()[index] = node;
                cursor.incCount();
            } else
                throw new MaxChildrenException();
    }

    /**
     * Sets cursor to the child at position index
     *
     * @param index index of child to assign cursor to
     */
    public void cursorToChild(int index) {
        try {
            cursor = cursor.getChildren()[index];
        } catch (NullPointerException ex) {
            System.out.println("Nowhere to go.");
        }
    }

    /**
     * Sets cursor to it's parent NetworkNode
     */
    public void cursorToParent() {
        cursor = cursor.getParent();
    }

    /**
     * Reads in a .txt file and creates a tree from it
     * @param filename Name of filename to be read in
     */
    public static NetworkTree readFromFile(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        boolean badFile = false;
        while(true) {
            File file = new File(filename);
            try {
                input = new Scanner(file);
                break;
            }
            catch (FileNotFoundException e) {
                System.out.println("Not a valid file");
            }
        }
        root = new NetworkNode(input.nextLine());
        while (input.hasNextLine()) {
            String line = input.nextLine();
            int numbs = 0;
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    numbs++;
                }
            }
            String pos = line.substring(0, numbs);

            String name = line.substring(numbs, line.length());

            NetworkNode newNode = new NetworkNode(name.replaceAll("-", ""));

            if (name.contains("-"))
                newNode.setNintendo(true);

            if (pos.length() == 1) {
                newNode.setParent(root);
                root.getChildren()[Integer.parseInt(pos) - 1] = newNode;
                root.incCount();
                newNode.setLevel(1);
            } else if (pos.length() == 2) {
                root.getChildren()[Character.getNumericValue(pos.charAt(0)) - 1].
                        getChildren()[Character.getNumericValue(pos.charAt(1)) - 1] = newNode;
                newNode.setLevel(2);
                newNode.setParent(root.getChildren()[Character.getNumericValue(pos.charAt(0)) - 1]);
                newNode.getParent().incCount();
            } else if (pos.length() == 3) {
                root.getChildren()[Character.getNumericValue(pos.charAt(0)) - 1].
                        getChildren()[Character.getNumericValue(pos.charAt(1)) - 1].
                        getChildren()[Character.getNumericValue(pos.charAt(2)) - 1] = newNode;
                newNode.setLevel(pos.length());
                newNode.setParent(root.getChildren()[Character.getNumericValue(pos.charAt(0)) - 1].
                        getChildren()[Character.getNumericValue(pos.charAt(1)) - 1]);
                newNode.getParent().incCount();
            }
        }
        return new NetworkTree(root, root);
    }


    /**
     * Used to print the NetworkTree
     *
     * @param root Root of the NetworkTree
     */
    public static void printTree(NetworkNode root) {
        if (root == null) {
            System.out.println("Make a tree first!");
        } else {
            System.out.print(root == cursor ? "->" : "+");
            ;
            System.out.println(root.getName());
            for (NetworkNode i : root.getChildren()) {
                if (i != null) {
                    if (i.isNintendo())
                        if (!(i.getName().contains("-")))
                            i.setName("-" + i.getName());

                    System.out.println(i != cursor ? "\t" + i.getName() : "\t->" + i.getName());
                    for (NetworkNode j : i.getChildren()) {
                        if (j != null) {
                            if (j.isNintendo())
                                if (!(j.getName().contains("-")))
                                    j.setName("-" + j.getName());
                            ;
                            System.out.println(j != cursor ? "\t\t" + j.getName() : "\t\t->" + j.getName());
                            for (NetworkNode k : j.getChildren()) {
                                if (k != null) {
                                    if (k.isNintendo())
                                        if (!(k.getName().contains("-")))
                                            k.setName("-" + k.getName());
                                    ;
                                    System.out.println(k != cursor ? "\t\t" + k.getName() : "\t\t->" + k.getName());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Writes the tree to a text file
     *
     * @param tree     NetworkTree to be converted into a text file
     * @param filename Name of file for tree to be saved as
     */
    public static void writeToFile(NetworkTree tree, String filename) {
        PrintWriter pw = null;
        int i = 1;
        int k = 1;
        try {
            pw = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ;
        pw.println(root.getName());
        for (NetworkNode n : root.getChildren()) {
            if (n != null) {
                pw.println(i + n.getName());
                int j = 1;
                for (NetworkNode m : n.getChildren()) {
                    if (m != null) {
                        pw.println(i + "" + j + m.getName());
                        j++;
                        k = 1;
                        for (NetworkNode o : m.getChildren()) {
                            if (o != null) {
                                pw.println(i + "" + j + "" + k + o.getName());
                                k++;
                            }
                        }
                    }
                }
            }
            i++;
        }
        pw.close();
    }
}
import java.util.Scanner;

/**
 * This class implements the linked list based classes to represent a delivery driver
 * @author Jeffrey Rodriguez
 */
public class DeliveryDriverCL {

    public static void main(String[] args){
        DeliveryList list[] = new DeliveryList[2];
        list[0] = new DeliveryList();
        list[1] = new DeliveryList();
        int i; //used to determine which list we are working with
        boolean whichList = true; //If true, we use list[0], Otherwise, we use list[1]
        Delivery copied = new Delivery(); //Used for cut and paste after methods.
        Scanner ipt = new Scanner(System.in);

        System.out.println("Welcome to J's Delivery Service! Here's our menu.");
        System.out.println("Menu: \n" +
                "\t A) Add a Delivery After Cursor \n" +
                "\t R) Remove Delivery At Cursor \n" +
                "\t X) Cut Cursor \n" +
                "\t V) Paste After Cursor\n" +
                "\t H) Cursor to Head\n" +
                "\t T) Cursor to Tail\n" +
                "\t F) Cursor Forward\n" +
                "\t B) Cursor Backward\n" +
                "\t S) Switch Delivery Lists\n" +
                "\t P) Print current list\n" +
                "\t Q) Quit");
        boolean stop = false;
        char choice;
        while (!stop){
            System.out.print("Please select an option: ");
            choice =  ipt.nextLine().toLowerCase().charAt(0); //Read in menu choice and extract the first character converted to lowercase
            i = whichList ? 0 : 1;
            switch (choice){
                case 'a': //add
                    System.out.print("Please enter a source: ");
                    String source = ipt.nextLine();

                    System.out.print("Please enter a destination: ");
                    String dest = ipt.nextLine();

                    System.out.print("Please enter any special instructions: ");
                    String instruction = ipt.nextLine();

                    Delivery data = new Delivery(source, dest, instruction);
                    if (list[i].getCursor() == null || list[i].getCursor() == list[i].getHead().getData()) {
                        list[i].insertAfterCursor(data);
                        /*try {
                            list[i].cursorForward();
                        } catch (DeliveryList.EndOfListException ex) {
                            System.out.print("");
                        }*/
                    }
                    else{
                        list[i].appendToTail(data);
                    }

                    System.out.println("\nOrder inserted.\n");
                    break;

                case 'r': //remove
                    try {
                        Delivery removed = list[i].getCursor();
                        list[i].removeCursor();
                        System.out.println("Delivery to " + removed.getDest() + " has been removed.");
                        }
                        catch (DeliveryList.EndOfListException ex)
                        {
                            System.out.println("Cursor is null.");
                        }
                    break;

                case 'x': //cut
                    try{
                        copied = list[i].getCursor();
                        list[i].removeCursor();
                        System.out.println("Cursor has been cut.");
                    }
                    catch (DeliveryList.EndOfListException ex)
                    {
                        System.out.println("Cursor is null.");
                    }
                    break;

                case 'v': //paste after cursor
                    list[i].insertAfterCursor(copied);
                    System.out.println("->\n" + copied.toString());
                    break;

                case 'h': //move cursor to head
                    list[i].resetCursorToHead();
                    System.out.println("Cursor is at head.");
                    break;

                case 't': //move cursor to tail
                    if (list[i].getTail() != null){
                        list[i].setCursor(list[i].getTail());
                    }
                    System.out.println("Cursor is at tail.");
                    break;

                case 'f': //move cursor forward
                    try {
                        list[i].cursorForward();
                        System.out.println("\nCursor has moved forward.\n");
                    }
                    catch (DeliveryList.EndOfListException ex){
                        System.out.println("Cursor is at the tail. Cannot move further forward.");
                    }
                    break;

                case 'b': //move cursor backward
                    try {
                        list[i].cursorBackward();
                        System.out.println("\nCursor has moved backwards.\n");
                    }
                    catch (DeliveryList.EndOfListException ex){
                        System.out.println("Cursor is at the head. Cannot move further back.");
                    }
                    break;

                case 's': //switch lists
                    whichList = !whichList;
                    System.out.println(whichList ? "J's Delivery Service has been selected" : "J's Hidden Discounted Service has been selected");
                    break;

                case 'p': //print list
                    DeliveryListNode c = list[i].getHead();
                    if (c == null){
                        System.out.println("Nothing to see here!");
                    }
                    else {
                        System.out.println(whichList ? "J's Delivery Service: " : "J's Hidden Discounted Service: ");
                        System.out.println("----------------------------------------------------------");
                        while (c != null){
                            System.out.print(c.getData() == list[i].getCursor() ? "->\n" : c == list[i].getHead() ? "" : "~\n"); //See if c is equal to the cursor. If it is, print ->, otherwise see if it's the
                                                                                                                                //head. If so, do nothing. Otherwise print a ~.
                            System.out.println(c.getData().toString());
                            c = c.getNext();
                        }
                        System.out.println("----------------------------------------------------------");
                    }
                    break;

                case 'q':
                    System.out.println("Goodbye, we hope to see you again soon!");
                    stop = true;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
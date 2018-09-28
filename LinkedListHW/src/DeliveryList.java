//Jeffrey Rodriguez 110733867
//CSE214.R14 Homework 2

/**
 * This class implements a doubly linked list data structure and maintains a list of Delivery objects.
 * @author Jeffrey Rodriguez
 */
public class DeliveryList {
    private DeliveryListNode head;
    private DeliveryListNode tail;
    private DeliveryListNode cursor;
    private int count;

    /**
     * The default constructor for DeliveryList.
     * <dt><b>Postconditions:</b><dd>
     * New DeliveryList has been initialized with head, tail, and cursor set to null.
     */
    public DeliveryList() {
        head = null;
        tail = null;
        cursor = null;
        count = 0;
    }

    /**
     * Constructor for DeliveryList.
     * @param head   head of the doubly linked list
     * @param tail   tail of the doubly linked list
     * @param cursor position of the doubly linked list
     * @param count  number of deliveries made
     * <dt><b>Postconditions:</b><dd>
     * New DeliveryList has been initialized with head, tail, and cursor set to param values.
     */
    public DeliveryList(DeliveryListNode head, DeliveryListNode tail, DeliveryListNode cursor, int count) {
        this.head = head;
        this.tail = tail;
        this.cursor = cursor;
        this.count = count;
    }

    /**
     * Obtains the head of the linked list.
     * @return
     * Head of the linked list.
     */
    public DeliveryListNode getHead() {
        return head;
    }

    /**
     * Sets the head of the linked list.
     * @param head
     * head of the linked list.
     */
    public void setHead(DeliveryListNode head) {
        this.head = head;
    }

    /**
     * Obtains the tail of the linked list.
     * @return
     * tail of the linked list.
     */
    public DeliveryListNode getTail() {
        return tail;
    }

    /**
     * Sets the tail of the linked list.
     * @param tail
     * tail of the linked list.
     */
    public void setTail(DeliveryListNode tail) {
        this.tail = tail;
    }

    /**
     * Sets the cursor of the linked list.
     * @param cursor
     * Cursor of the linked list
     */
    public void setCursor(DeliveryListNode cursor) {
        this.cursor = cursor;
    }

    /**
     * This method keeps track of the number of deliveries made.
     * @return
     * The number of deliveries made.
     */
    public int numDeliveries() {
        return count;
    }

    /**
     * Obtains the reference to the Delivery data which the cursor points at.
     * @return
     * Reference by cursor to the delivery.
     */
    public Delivery getCursor() {
        if (cursor == null) {
            return null;
        }
        return cursor.getData();
    }

    /**
     * Resets the location of the cursor to the head.
     * <dt><b>Postconditions:</b><dd>
     *     If head is null, the cursor is also set to null. Otherwise, the cursor now represents represents the first DeliveryListNode.
     */
    public void resetCursorToHead() {
        if (head == null)
            cursor = null;
        else
            cursor = head;
    }

    /**
     * Defines exception to be thrown when action cannot be performed because cursor is at head or tail of the list.
     */
    public class EndOfListException extends Exception {
    }

    /**
     * Sets the cursor to the next element of the list.
     * @throws EndOfListException
     * Thrown if cursor is at the tail.
     */
    public void cursorForward() throws EndOfListException {
        if (cursor == tail)
            throw new EndOfListException();
        cursor = cursor.getNext();
    }

    /**
     * Sets the cursor to the previous element of the list.
     * @throws EndOfListException
     * Thrown if cursor is at the head.
     */
    public void cursorBackward() throws EndOfListException {
        if (cursor == head)
            throw new EndOfListException();
        cursor = cursor.getPrev();
    }

    /**
     * This method inserts a new Delivery node after the cursor.
     * @param newDelivery
     * Delivery object to be inserted
     * @exception IllegalArgumentException
     * Thrown if delivery is null.
     * <dt><b>Preconditions:</b><dd>
     *     newDelivery is not null.
     * <dt><b>Postconditions:</b><dd>
     *     Wraps newDelivery in a new DeliveryListNode object.
     *     <p>If cursor was null, the new DeliveryListNode has been set as the set as the head and tail of the list.</p>
     *     <p>Otherwise, the new DeliveryListNode has been inserted into the list after the cursor.</p>
     *     <p>The cursor remains unchanged.</p>
     */
    public void insertAfterCursor(Delivery newDelivery) {
        if (newDelivery == null)
            throw new IllegalArgumentException("New Delivery is null");

        DeliveryListNode newDel = new DeliveryListNode(newDelivery);

        if (cursor != null) {
            if (cursor == tail) {
                cursor.setNext(newDel);
                tail = newDel;
                tail.setPrev(cursor);
            } else {
                cursor.getNext().setPrev(newDel);
                newDel.setNext(cursor.getNext());
                cursor.setNext(newDel);
                newDel.setPrev(cursor);
            }
        } else {
            head = newDel;
            tail = newDel;
            cursor = newDel;
        }
        count++;
    }

    /**
     * This method adds a new Delivery object after the tail
     * @param newDelivery
     * Delivery objected appended to the tail
     * @exception IllegalArgumentException
     * Thrown if parameter is null
     * <dt><b>Preconditions:</b><dd>
     * newDelivery is not null.
     * <dt><b>Postconditions:</b><dd>
     *     newDelivery has been wrapped in a new DeliveryListNode object.
     *     <p>If tail was previously not null, the newly created DeliveryListNode has been inserted into the list after the tail.</p>
     *     <p>If tail was previously null, the newly created DeliveryListNode has been set as the new head of the list (as well as the tail and the cursor).</p>
     *     <p>The tail now references the newly created DeliveryListNode.</p>
     */
    public void appendToTail(Delivery newDelivery) {
        if (newDelivery == null) {
            throw new IllegalArgumentException("New Delivery is null");
        }

        if (tail != null) {
            DeliveryListNode temp = cursor;
            cursor = tail;
            insertAfterCursor(newDelivery);
            cursor = temp;
        } else {
            DeliveryListNode newDel = new DeliveryListNode(newDelivery);
            head = newDel;
            tail = newDel;
        }
        count++;
    }

    /**
     * This method removes cursor and sets it to the successive node.
     * @return
     * The data inside the cursor.
     * @throws EndOfListException
     * Thrown if cursor is null.
     * <dt><b>Preconditions:</b><dd>
     *     cursor is not null.
     * <dt><b>Postconditions:</b><dd>
     *     The DeliveryListNode referenced by cursor has been removed from the list.
     *     <p>All other DeliveryListNodes in the list exist in the same Delivery as before.</p>
     *     <p>The cursor now references the next DeliveryListNode (or the tail, if the cursor previously referenced the tail of the list).</p>
     */
    public Delivery removeCursor() throws EndOfListException {
        if (cursor == null) {
            throw new EndOfListException();
        }
        Delivery data = cursor.getData();

        if (cursor == head && cursor == tail) {
            cursor = null;
            head = null;
            tail = null;
        } else if (cursor == head) {
            head = head.getNext();
            head.setPrev(null);
            cursor = head;
        } else if (cursor == tail) {
            tail = cursor.getPrev();
            tail.setNext(null);
            cursor = tail;
        } else {
            DeliveryListNode temp = cursor;
            cursor = temp.getNext();
            cursor.setPrev(temp.getPrev());
            temp.getPrev().setNext(cursor);
        }
        count--;
        return data;
    }
}
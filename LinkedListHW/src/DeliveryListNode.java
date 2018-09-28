//Jeffrey Rodriguez 110733867
//CSE214.R14 HW2

/**
 * This class represents a node for a doubly linked list data structure implementing Delivery class as data.
 * @author Jeffrey Rodriguez
 */
public class DeliveryListNode {
    private Delivery data;
    private DeliveryListNode next;
    private DeliveryListNode prev;

    /**
     * Default constructor for DeliveryListNode.
     * @param initData
     * Initial data
     * <dt><b>Preconditions:</b><dd>
     *     Initial Data is not null.
     * <dt><b>Postconditions:</b><dd>
     *   Initialized DeliveryListNode wraps the initial data, and sets prev and next to null.
     * @exception IllegalArgumentException
     * Thrown if initial data is null.
     */
    public DeliveryListNode(Delivery initData){
        if (initData == null){
            throw new IllegalArgumentException("Initial data cannot be null");
        }
        this.data = initData;
        this.next = null;
        this.prev = null;

    }

    /**
     * Constructor for DeliveryListNode.
     * @param data
     * data from delivery.
     * @param next
     * next element in doubly linked list.
     * @param prev
     * previous element in doubly linked list.
     */
    public DeliveryListNode(Delivery data, DeliveryListNode next, DeliveryListNode prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    /**
     * This method obtains data from the list node.
     * @return
     * Data from the list node.
     */
    public Delivery getData() {
        return data;
    }

    /**
     * This method sets the delivery data.
     * @param data
     * Delivery data to be set.
     */
    public void setData(Delivery data) {
        this.data = data;
    }

    /**
     * This method obtains the reference to the next node of the linked list.
     * @return
     * Reference to next node in the list.
     */
    public DeliveryListNode getNext() {
        return next;
    }

    /**
     * This method sets the next reference in the linked list.
     * @param next
     * Next reference to be set.
     */
    public void setNext(DeliveryListNode next) {
        this.next = next;
    }

    /**
     * This method obtains the reference to the previous node of the linked list.
     * @return
     * Reference to previous node in the list.
     */
    public DeliveryListNode getPrev() {
        return prev;
    }

    /**
     * This method sets the previous reference in the linked list.
     * @param prev
     * Previous reference to be set.
     */
    public void setPrev(DeliveryListNode prev) {
        this.prev = prev;
    }
}

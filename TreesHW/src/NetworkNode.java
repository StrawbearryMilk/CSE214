//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

/**
 * This class represents the NetworkNode object
 * @author Jeffrey Rodriguez
 */
public class NetworkNode {
    private String name;
    private boolean isNintendo;
    private boolean isBroken = false;
    private NetworkNode parent;
    private NetworkNode[] children;
    private int level;
    private int count;
    final int maxChildren = 9;

    /**
     * Constructor for NetworkNode
     * @param name Name of the NetworkNode
     */
    public NetworkNode(String name) {
        this.children = new NetworkNode[maxChildren];
        this.name = name;
    }

    /**
     * Constructor for the NetworkNode class
     * @param name Name of the NetworkNode
     * @param isNintendo Identifies the type of NetworkNode
     * @param children Children of the NetworkNode
     * @param count Amount of children the NetworkNode has
     */
    public NetworkNode(String name, boolean isNintendo, NetworkNode[] children, int count){
        this.name = name;
        this.isNintendo = isNintendo;
        this.isBroken = isBroken;
        this.parent = null;
        this.children = children;
        this.count = count;
    }

    /**
     * Obtains name of the network node
     * @return name of the network node
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the network node
     * @param name String to be assigned as the name for the network node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks to see if the node is a Nintendo network
     * @return true if the node is a Nintendo network, false otherwise
     */
    public boolean isNintendo() {
        return isNintendo;
    }

    /**
     * Used to turn node into Nintendo node or other type
     * @param nintendo if true, the node is a Nintendo network node. If false, it is a different type of node
     */
    public void setNintendo(boolean nintendo) {
        isNintendo = nintendo;
    }

    /**
     * Obtains the distance from the node to the root
     * @return Level of the NetworkNode
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the NetworkNode
     * @param level Index to denote the level of the node
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Checks to see if node is broken
     * @return True if node is broken, false otherwise
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Converts node into broken or fixed type
     * @param broken If true, the node is set to broken. If false, it is set to a working node
     */
    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    /**
     * Obtains the parent of a node
     * @return Parent node
     */
    public NetworkNode getParent() {
        return parent;
    }

    /**
     * Sets parent node for the cursor node
     * @param parent Node to be set as parent
     */
    public void setParent(NetworkNode parent) {
        this.parent = parent;
    }

    /**
     * An array of children nodes of the parent
     * @return An array of children nodes of the parent
     */
    public NetworkNode[] getChildren() {
        return children;
    }

    /**
     * Sets the array of children node for the parent
     * @param children Array of children node for a parent
     */
    public void setChildren(NetworkNode[] children) {
        this.children = children;
    }

    /**
     * Counts the amount of children the node has
     * @return Amount of children the node has
     */
    public int numChildren(){
        return count;
    }

    /**
     * Increments the child counter
     */
    public void incCount(){
        count += 1;
    }

    /**
     * Decrements the child counter
     */
    public void decCount(){
        count -= 1;
    }
}
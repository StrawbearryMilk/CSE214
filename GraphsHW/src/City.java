//Jeffrey Rodriguez 110733867
//CSE214.R14

import java.util.HashMap;

/**
 * This class creates the City object which is represented by a node of the Island graph.
 * Each city leads to some subset of roads, with a defined capacity.
 * @author Jeffrey Rodriguez
 */
public class City implements Comparable<City>{
    private String name;
    private String neighbor[];
    private HashMap<String, Integer> neighbors; //City the road is connecting to is String, capacity cost is Integer
    private boolean visited;
    private boolean discovered;
    private int position; //Position in adjacency matrix

    /**
     * Constructor for City class
     * @param name Name of the city
     */
    public City(String name, int position){
        this.name = name;
        neighbors = new HashMap();
        neighbor = null;
        visited = false;
        discovered = false;
        this.position = position;
    }

    /**
     * Compare name of two cities
     * @param o City to compare with
     * @return 1 if this city comes first alphabetically, -1 if after, 0 if they are the same name
     */
    @Override
    public int compareTo(City o){
        int value = this.name.compareTo(o.name);
        return (value == 0 ? 0 : value > 1 ? 1 : -1); //value = 0, return 0. Else check to see if this.name is greater or less
    }

    /**
     * Obtain name of the City
     * @return name of the city
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the City
     * @param name name of the city
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Obtains HashMap collection of neighbors for a City
     * @return HashMap of neighbors to this city
     */
    public HashMap<String, Integer> getNeighbors() {
        return neighbors;
    }

    /**
     * Checks to see if City has neighbors
     * @return True if City has at least one neighbor, false otherwise
     */
    public boolean hasNeighbor(){
        if (this.neighbors.isEmpty())
            return false;
        return true;
    }

    /**
     * Prints a list for the city and the road(s) it connects to, along with their capacity
     */
    public void printNeighbors() {//used for first printing in IslandDesigner class
        for (String n : neighbors.keySet()){
            System.out.printf("%s to %s %10d\n", this.getName(), n, neighbors.get(n));
        }
    }

    /**
     * Add a neighbor to the City hashmap
     * @param neighbor Name of neihgboring City to be added
     * @param cost Capacity of the road between this City and its neighbor
     */
    public void addNeighbors(String neighbor, int cost) {
        neighbors.put(neighbor, cost);
    }

    /**
     * Checks to see if city has been visited during DFS
     * @return
     */
    public boolean Visited(){
        return visited;
    }

    /**
     * Sets the visit status of City
     * @param visited Boolean true if City has been visited during DFS, false otherwise
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    /**
     * Checks to see if city has been discoered during DFS
     * @return true if City has been discovered, false otherwise
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * Sets the Discovered status of City
     * @param discovered Boolean true if City has been discoered during DFS, false otherwise
     */
    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    /**
     * Obtains position of City in the Adjacency matrix
     * @return position of City in the Adjaceny Matrix
     */
    public int getPosition(){
        return position;
    }

    /**
     * Sets position of City in the Adjacency Matrix
     * @param position position of City in the Adjacency Matrix
     */
    public void setPosition(int position){
        this.position = position;
    }
}

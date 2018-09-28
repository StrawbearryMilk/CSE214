//Jeffrey Rodriguez 110733867
//CSE214.R14

import big.data.DataSource;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * This class maintains the IslandNetwork graph for City objects
 * @author Jeffrey Rodriguez
 */
public class IslandNetwork {
    private static HashMap<String, City> graph;
    private static HashMap<Integer, City> cityReference = new HashMap(); //matches city with its integer position in the adjacency matrix
    private static HashMap<City, Integer> roadReference = new HashMap<>(); //inverse of cityReference
    private static int[][] adjMatrix;
    private List DFStree = new ArrayList();
//    private int capacity = Integer.MAX_VALUE;
//    private int totalFlow = 0;

    /**
     * Default Constructor for IslandNetwork
     */
    public IslandNetwork(){
        graph = new HashMap<>();
    }

    /**
     * Obtains graph of the IslandNetwork
     * @return graph of the IslandNetwork
     */
    public HashMap<String, City> getGraph(){
        return graph;
    }

    /**
     * Constructs a new IslandNetwork with a given graph
     * @param graph
     */
    public IslandNetwork(HashMap<String, City> graph){
        this.graph.putAll(graph);
    }

    public static class InvalidURLException extends Exception{};

    /**
     * This method reads in a URL and uses its data to construct a graph representation of the city as well as an adjacency matrix
     * @param url URL of an XML file to be read in and used to construct the IslandNetwork
     * @return A constructed IslandNetwork based on the data in the .xml file
     * @throws InvalidURLException thrown if the URL is not valid
     */
    public static IslandNetwork loadFromFile(String url) throws InvalidURLException{
        if (!url.endsWith(".xml"))
            throw new InvalidURLException();
        graph = new HashMap<>();
        DataSource ds = DataSource.connectXML(url);
        ds.load();
        String cityNames = ds.fetchString("cities");
        String[] cityList = cityNames.substring(1, cityNames.length() - 1).replace("\"", "").split(",");
        adjMatrix = new int[cityList.length][cityList.length];
        String roadNames = ds.fetchString("roads");
        String[] roadList = roadNames.substring(2, roadNames.length() - 2).split("\",\"");
        int pos = 0;
        for (String c : cityList) { //construct Graph
            City city = new City(c, pos);
            cityReference.put(pos, city);
            graph.put(c, city);
            for (int i = 0; i < roadList.length; i++) {
                String temp = roadList[i];
                int from = temp.indexOf(",");
                int to = temp.indexOf(",", from + 1);
                int cost = Integer.parseInt(temp.substring(to + 1));
                if (c.equals(temp.substring(0,from))) {
                    city.addNeighbors(temp.substring(from + 1, to), cost);
                }
            }
            pos++;
        }
        return new IslandNetwork(graph);
    }


    /**
     * Used to construct the Adjacency Matrix of the graph
     */
    public void setAdjMatrix(){
        for (City c : graph.values()){
            int i = c.getPosition();
            for (City n : graph.values()){
                int j = n.getPosition();
                if (c.getNeighbors().containsKey(n.getName()))
                    adjMatrix[i][j] = c.getNeighbors().get(n.getName()); //Assign capacity cost from City C to neighbor to i, j entry
                else
                    adjMatrix[i][j] = 0;
            }
        }
    }

    /**
     * Obtains Adjacency Matrix of the graph
     * @return Adjacency Matrix of the graph
     */
    public int[][] getAdjMatrix(){
        return adjMatrix;
    }

    /**
     * This method sorts the City nodes in the graph in alphabetical order and prints them.
     */
    public void sortGraph(){
        ArrayList<City> cityList = new ArrayList<>(graph.values());
        Collections.sort(cityList, City::compareTo);
        for (City c : cityList)
            System.out.println(c.getName());

    }

    /**
     * This method prints the roads and their respective capacities
     */
    public void printRoads(){
        System.out.printf("%1s %40s\n", "Cities", "Capacity");
        for (City c : graph.values()){
            if (c.hasNeighbor() == true)
                c.printNeighbors();
        }
        System.out.println("");
    }

    /**
     * This method sets the Discovered and Visited status of each City in the IslandNetwork to false
     */
    public void resetCity(){
        for (City c : graph.values()){
            c.setVisited(false);
            c.setDiscovered(false);
        }
    }

    public class FalseCityException extends Exception{}

    /**
     * Obtains the depth for search tree of a given City
     * @param from City we which to find the DFS from
     * @return A list of cities contained in the origin city's DFS Tree
     * @throws FalseCityException Thrown if City from is not in the IslandNetwork
     */
    public List<String> dfs(String from) throws FalseCityException{//int position taken from String from's position characteristic
        if (!graph.containsKey(from))
            throw new FalseCityException();

        City c = graph.get(from);
        if (!(c.isDiscovered() && c.Visited())) {
            c.setDiscovered(true);
            c.setVisited(true);
        }
        if (!c.hasNeighbor())
            return DFStree;

        int pos = c.getPosition();

        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[pos][i] != 0 && !cityReference.get(i).isDiscovered()) {
                City n = cityReference.get(i);
                n.setDiscovered(true);
                DFStree.add(n.getName());

                if (!n.Visited()) {
                    dfs(n.getName());
                }
            }
        }
        return DFStree;
    }

//    public static boolean existingPath(String from, String to){
//        if (to.equals(from))
//            return true;
//        else {
//            try {
//                if (dfs(from).contains(to))
//                    return true;
//            } catch (FalseCityException fc) {
//                System.out.println("this city isn't in graph dumdum");
//                return false;
//            }
//        }
//
//        return false;
//    }
//
//    public static int[][] residMatrix(int[][] array) {
//        int[][] resid = new int[array.length][array.length];
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array.length; j++) {
//                resid[i][j] = array[i][j];
//            }
//        }
//        return resid;
//    }
//
//    /**
//     * Obtains the maximal network flow between two Cities
//     * @param from Name of the starting City
//     * @param to Name of the ending City
//     */
//    public void maxFlow(String from, String to){
//        residGraph = residMatrix(adjMatrix);
//        capacity = Integer.MAX_VALUE;
//        totalFlow = 0;
//        totalFlow = maxFlowHelper(from, from, to);
//        System.out.println("Maximum flow: " + totalFlow);
//    }
//
//    private int maxFlowHelper(String from, String current, String to){
//        City source = graph.get(from); City dest = graph.get(to); City curr =graph.get(current);
//        //capacity = Integer.min((residGraph[], capacity);
//        if (current.equals(to)){
//            LinkedList<String> pathList = new LinkedList<>();
//            totalFlow += capacity;
//            while (!current.equals(from)){
//                pathList.add(current);
//                curr = graph.get(from);
//                residGraph[roadReference.get(from)][roadReference.get(curr)] = adjMatrix[roadReference.get(from)][roadReference.get(curr)] - capacity;
//            }
//            System.out.println(pathList + ": " + capacity);
//        }
//        else{
//            for (String n : curr.getNeighbors().keySet()){
//                maxFlowHelper(from, n, to);
//            }
//        }
//        return totalFlow;
//    }
//
//    public static void main(String[] args) {
//        loadFromFile("http://www3.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml");
//        setAdjMatrix();
//        try {
//            System.out.println(dfs("South Spoon"));
//        }
//        catch (FalseCityException ex){
//            System.out.println("bad pick yaya");
//        }
//
//        int[][] a = {{2,2},{37,1}};
//        int[][] b = a;
//        b[1][0] = 3;
//        System.out.println(b.toString());
//        System.out.println(a.toString());
//
//    }
}

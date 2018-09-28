//Jeffrey Rodriguez 110733867
//CSE214.R14

import java.util.List;
import java.util.Scanner;

/**
 * Main class for the app allowing the user to find the DFS tree from one city to another.
 * One will also be able to find the maximum network flow from one city to another.
 * @author Jeffrey Rodriguez
 */
public class IslandDesigner{
    public static IslandNetwork G  = new IslandNetwork();
    public static void printMenu(){
        System.out.println("Menu: \n" +
        "\tD) Destinations Reachable\n" +
        "\tF) Maximum Flow\n" +
        "\tQ) Quit");
    }

    public static void printCities(IslandNetwork G){
        System.out.println("Cities:\n" +
        "------------------");
        G.sortGraph();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        System.out.println("Welcome to the item Designer!");
        System.out.print("Please enter a URL: ");
        try{String url = sc.next();
            G.loadFromFile(url);
            System.out.println("Map loaded!\n");}

        catch(IslandNetwork.InvalidURLException ex){
            System.out.println("Not a valid URL");
            return;
        }
        printCities(G);
        G.printRoads();
        printMenu();
        G.setAdjMatrix();
        while (!stop){
            System.out.print("Please select an option: ");
            char choice = sc.nextLine().toUpperCase().charAt(0);
            switch (choice){
                case 'D': //DFT
                    System.out.print("Please enter a starting city: ");
                    String source = sc.nextLine();
                    try {
                        List dfsList = G.dfs(source);
                        if (dfsList.isEmpty())
                            System.out.println("No where to go from here...");
                        else
                            System.out.println("DFS starting from " + source + ":\n" +
                                String.join(", ", G.dfs(source)));
                    }
                    catch (IslandNetwork.FalseCityException ex){
                        System.out.println("This city is not in the graph!");
                        break;
                    }
                    G.resetCity();
                    break;

                case 'F': //print max flow
//                    System.out.print("Please select a starting city: ");
//                    source = sc.nextLine();
//                    System.out.print("Please select a destination: ");
//                    String dest = sc.nextLine();
//                    System.out.println("Routing: ");
//                    System.out.println("Maximum flow: ");
                    System.out.println("I gave up on this method.");
                    break;

                case 'Q':
                    stop = true;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Bad input! Try again.");
            }
        }
    }
}
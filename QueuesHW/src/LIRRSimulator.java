//Jeffrey Rodriguez 110733867
//CSE214.R14 HW4

import java.util.Scanner;

/**
 * Main class for this app
 * @author Jeffrey Rodriguez
 */
public class LIRRSimulator {
    public static String[] stNames = {"Huntington","Syosset","Mineola","Hicksville",};
    public static double p1;
    public static double p2;
    public static Station[] stations;
    public static Scanner ipt = new Scanner(System.in);
    public static Train[] trains;
    public static int lastArrival;
    public static int currTime = 0;
    public static int id = 1;
    public static int numTrains;
    public static int firstCapacity;
    public static int secondCapacity;

    /**
     * Used to loop through and assign station settings
     * @param name
     */
    public static void setProb(String name){
        System.out.print(name + "\n" +
                "Please enter first class arrival probability: ");
        p1 = ipt.nextDouble();
        System.out.print("Please enter second class arrival probability: ");
        p2 = ipt.nextDouble();
        System.out.println("");
    }

    /**
     * Used to create an array of constructed trains
     */
    public static void setTrains(){
        System.out.print("Please enter first class capacity: ");
        firstCapacity = ipt.nextInt();
        System.out.print("\nPlease enter second class capacity: ");
        secondCapacity = ipt.nextInt();

        System.out.print("\nPlease enter number of trains: ");
        numTrains = ipt.nextInt();
        trains = new Train[numTrains];
        for (int i = 0; i < numTrains; i++){
            trains[i] = new Train(firstCapacity, secondCapacity, i*5+1, i+1);
        }
        System.out.print("\nPlease enter last arrival time of passengers: ");
        lastArrival = 100; //ipt.nextInt();
    }
    

    public static void main(String[] arg){
        
        stations = new Station[4];
        System.out.println("Welcome to the magical new LIRR Simulator\n");
        int i = 0;
        for (String s : stNames){
            setProb(s);
            while ((p1 < 0 || p1 > 1) || (p2 < 0 || p2 > 1)){
                System.out.println("Invalid input! Let's try that again...");
                System.out.print(s + "\n" +
                        "Please enter first class arrival probability: ");
                p1 = ipt.nextDouble();
                System.out.print("Please enter second class arrival probability: ");
                p2 = ipt.nextDouble();
                System.out.println("");
            }
            BooleanSource firstArrival = new BooleanSource(p1);
            BooleanSource secondArrival = new BooleanSource(p2);
          
            stations[i] = new Station(firstArrival,secondArrival,s);
            i++;
        }

        setTrains();

        System.out.println("Begin Simulation:"+
                "\n/*******************************************************************/");
        // while the last train has not arrived at destination
        while (!trains[numTrains - 1].isDone()) { 
            
            System.out.println("\nTime " + currTime + ": ");
            System.out.println("Station Overview: ");
            
            for (int j = 0; j < stations.length; j++){
                System.out.println(stations[j].getName() + ":");
                stations[j].simulateTimeStep();
            }
            
            for (int t = 0; t < trains.length; t++) {
                trains[t].decTimeUntilNextArrival();
                trains[t].simulateTimeStep();
            }
            currTime++;
        }

        /// summarize

        int numFirst = 0, numSecond = 0, numTotal = 0;
        for (int j = 0; j < stations.length; j++) {
            numFirst += stations[j].getFirstClass().size();
            numSecond += stations[j].getSecondClass().size();
            numTotal += stations[j].getTotalServed();
        }
        System.out.println("A total of " +  numTotal + " passangers were served, "
                + numFirst + " first class passangers were left without a seat, "
                + numSecond + " second class passangers were left without a seat."
        );
        for (int j = 0; j < stations.length; j++) {
            stations[j].summarize();
        }
        System.out.println();
        System.out.println(currTime);
    }

}

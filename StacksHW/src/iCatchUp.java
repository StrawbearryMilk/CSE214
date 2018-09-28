//Jeffrey Rodriguez 110733867
//CSE214.R14 HW3

import java.util.Scanner;

/**
 * Main class for this app
 * @author Jeffrey Rodriguez
 */
public class iCatchUp {
    
    public static Menu menu;
    public static char choice;
    
    private static void printMenu() {
        System.out.print("You are on the home screen.\n" +
                "\nHome Options:\n\tS) Safari" + 
                "\n\tM) Maps" + 
                "\n\tQ) Quit" +
                "\nPlease select an option: ");
    }
    
    
    public static void main(String[] arg){
        menu = Menu.HOME;
        Maps map = new Maps();
        Safari safari = new Safari();

        Scanner scanner = new Scanner(System.in);
        Boolean stop = false;
        System.out.println("Welcome to the smart phone with only two apps. ");
        choice = 0;
        while (!stop){
            if (menu == Menu.HOME) {
                printMenu();
                String sc = scanner.nextLine();
                choice = sc.toUpperCase().charAt(0);  
            }
            switch (choice){
                case 'M':
                    menu = Menu.MAPS;
                    while (menu == Menu.MAPS) {
                        map.readCommand(scanner);
                    }
                    break;
                case 'S':
                    menu = Menu.SAFARI;
                    while (menu == Menu.SAFARI) {
                        safari.readCommand(scanner);
                    }
                    break;
                case 'Q':
                    System.out.println("Goodbye!");
                    stop = true;
                    break;
                default:
                    System.out.println("Unrecognizable input, try again.");
            }
        }
    }
}
package cruiseship_v2;

import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class main {
    
    private static Scanner input = new Scanner(System.in);
    private static Cabin[] cabin = new Cabin[13];
    
    private static Queue queue = new Queue(); /* For queue */
    
    public static void main(String[] args) {
        initialise();
        menu();
    }
    
    private static void initialise() { /* Adapted from the study notes PDF in Blackboard */
        String e = "e"; /* Placeholder for empty occupant slot */
        for (int x = 0; x < 13; x++) {
            cabin[x] = new Cabin(x);
        }
        
        for (int x = 0; x < 4; x++) {
            Cabin.occupant[x] = new Passenger(e, e, 0);
        }
        
        for (int x = 0; x < 13; x++) {
            /* Setting all occupants to "e" */
            cabin[x].occupant[1].setForename("e", x);
            cabin[x].occupant[1].setSurname("e", x);
            
            cabin[x].occupant[2].setForename("e", x);
            cabin[x].occupant[2].setSurname("e", x);
            
            cabin[x].occupant[3].setForename("e", x);
            cabin[x].occupant[3].setSurname("e", x);
        }
        System.out.println(cabin[1].occupant[1].getForename(1));
        
        System.out.println("Initialise\n");
    }
    
    private static void menu() {
        Scanner input = new Scanner(System.in);
        Boolean valid = false;
        
        String choice;
        System.out.println("Menu: \n");
        System.out.println("""
                           A: Add a customer to a cabin
                           V: View all cabins
                           
                           E: Display Empty cabins
                           D: Delete customer from cabin
                           F: Find cabin from customer name
                           T: Show expenses
                           S: Store program data into file
                           L: Load program data from file
                           
                           Q: Exit
                           """);
        System.out.print("Enter choice: ");
        choice = input.next();
        while (valid.equals(false))
        {
            if (choice.toUpperCase().equals("A"))
            {
                /* Add passengers to cabins */
                addPassenger();
            } else if (choice.toUpperCase().equals("V"))
            {
                /* View all cabins with passengers */
                viewCabins();
            }
            else if (choice.toUpperCase().equals("E"))
            {
                /* Display empty cabins */
                displayEmpty();
            }            
            else if (choice.toUpperCase().equals("D"))
            {
                /* Removes passenger from cabin */
                deleteCustomer();
            }
            else if (choice.toUpperCase().equals("F"))
            {
                /* Enter full name of passenger to find their cabin number */
                findCabin();
            }
            else if (choice.toUpperCase().equals("T"))
            {
                /* Show individual expenses and sum of expenses for cabin */
                showExpenses();
            }
            else if (choice.toUpperCase().equals("S"))
            {
                /* Stores details into "cruiseShip2.txt" */
                store();
            }
            else if (choice.toUpperCase().equals("L"))
            {
                /* Load data from "cruiseShip2.txt" */
                load();
            }
            else if (choice.toUpperCase().equals("Q"))
            {
                /* Terminates program */
                System.out.println("Exiting \n");
                System.exit(0);
            }
            else {
                /* Displayed if user enters invalid input */
                System.out.print("Please enter a valid option: ");
                choice = input.next();
            }
        }
    }
    
    private static void option() {
        /* User will be given the option to 
        exit the program or return to the menu */
        Boolean valid = false;
        System.out.println("");
        System.out.println("""
                           M: Menu
                           Q: Quit
                           """);
        System.out.print("Enter choice: ");
        String choice = input.next();
        while (valid.equals(false))
        {
            if (choice.toUpperCase().equals("M"))
            {
                menu();
            }
            else if (choice.toUpperCase().equals("Q"))
            {
                System.exit(0);
            }
            else
            {
                System.out.print("Enter either Q to quit or M to return to menu: ");
                choice = input.next();
            }
        }
    }
    
    private static void addPassenger() {
        String forename; /* input for forename */
        String surname; /* input for surname */
        Boolean valid = false;
        
        String choice;
        String choice2 = "";
        int cabinNum;
        System.out.print("\nEnter cabin number (1-12) or 13 to quit: ");
        choice = input.next();
        try {
            cabinNum = Integer.parseInt(choice);
            if (cabinNum == 13) {
                option();
            } else if (cabinNum < 1 || cabinNum > 13) {
                System.out.println("Out of range (1-13)");
                addPassenger();
            }
            while (cabinNum >= 1 && cabinNum <= 12) {
                if (    /* Checks if cabin is fully occupied */
                        (cabin[cabinNum].occupant[1].getForename(cabinNum).toLowerCase().equals("e")
                        && cabin[cabinNum].occupant[1].getSurname(cabinNum).toLowerCase().equals("e"))
                        || (cabin[cabinNum].occupant[2].getForename(cabinNum).toLowerCase().equals("e")
                        && cabin[cabinNum].occupant[2].getSurname(cabinNum).toLowerCase().equals("e"))
                        || (cabin[cabinNum].occupant[3].getForename(cabinNum).toLowerCase().equals("e")
                        && cabin[cabinNum].occupant[3].getSurname(cabinNum).toLowerCase().equals("e"))) {
                    /* Passenger 1 forename */
                    System.out.print("\nEnter forename for passenger 1: ");
                    forename = input.next().toUpperCase();
                    cabin[cabinNum].occupant[1].setForename(forename, cabinNum);
                    
                    /* Passenger 1 surname */
                    System.out.print("Enter surname for passenger 1: ");
                    surname = input.next().toUpperCase();
                    cabin[cabinNum].occupant[1].setSurname(surname, cabinNum);
                    cabin[cabinNum].occupant[1].setExpenses(forename, surname, cabinNum);
                    
                    /* Option to add passenger 2 */
                    while (valid == false) {
                        System.out.print("\nEnter another passenger? y/n: ");
                        choice2 = input.next().toUpperCase();
                        if (choice2.equals("N")) {
                            addPassenger();
                        } else if (choice2.equals("Y")) {
                            /* Passenger 2 forename */
                            System.out.print("\nEnter forename for passenger 2: ");
                            forename = input.next().toUpperCase();
                            cabin[cabinNum].occupant[2].setForename(forename, cabinNum);
                            
                            /* Passenger 2 surname */
                            System.out.print("Enter surname for passenger 2: ");
                            surname = input.next().toUpperCase();
                            cabin[cabinNum].occupant[2].setSurname(surname, cabinNum);
                            cabin[cabinNum].occupant[2].setExpenses(forename, surname, cabinNum);
                            
                            /* Option to add passenger 3 */
                            while (valid == false) {
                                System.out.print("\nEnter another passenger? y/n: ");
                                choice2 = input.next().toUpperCase();
                                if (choice2.equals("Y")) {
                                    /* Passenger 3 forename */
                                    System.out.print("\nEnter forename for passenger 3: ");
                                    forename = input.next().toUpperCase();
                                    cabin[cabinNum].occupant[3].setForename(forename, cabinNum);
                                    
                                    /* Passenger 3 surname */
                                    System.out.print("Enter surname for passenger 3: ");
                                    surname = input.next().toUpperCase();
                                    cabin[cabinNum].occupant[3].setSurname(surname, cabinNum);
                                    cabin[cabinNum].occupant[3].setExpenses(forename, surname, cabinNum);
                                    addPassenger();
                                }
                                else if (choice2.equals("N")) {
                                    addPassenger();
                                }
                                else {
                                    System.out.println("\nEnter either 'Y' for yes or 'N' for no");
                                }
                            }
                        }
                        else {
                            System.out.println("\nEnter either 'Y' for yes or 'N' for no");
                        }
                    }
                } else {
                    Boolean found = false;
                    /* Queue */
                    while (found == false) {
                        for (int x = 1; x < 14; x++){
                            for(int i = 1; i < 4; i++){
                                if (x == 13) {
                                    queue.addQueue();
                                    option();
                                }
                                else if (cabin[x].occupant[i].getForename(x).equals("e")
                                        && cabin[x].occupant[i].getSurname(x).equals("e")) {
                                    System.out.println("Cabin full. Try other cabin.");
                                    System.out.println("Try Searching for empty cabins in menu\n");
                                    menu();
                                }
                            }
                        }
                    }
                }
            }
        } catch(NumberFormatException nfe) {
            System.out.println("\nEnter a valid cabin number (integer) or 13 to quit");
            addPassenger();
        }
    }
    
    private static void viewCabins() {
        /* Displays cabin number with occupants */
        for (int x = 1; x < 13; x++)
        {
            System.out.println("\nCabin "+x+":\n");
            for (int i = 1; i < 4; i++) {
                if (cabin[x].occupant[i].getForename(x).equals("e")) {
                    System.out.println("Passenger "+i+": Empty\n");
                } else {
                    System.out.println("Passenger "+i+": "+cabin[x].occupant[i].getForename(x)
                            +" "+cabin[x].occupant[i].getSurname(x));
                    System.out.println("Expenses: "+cabin[x].occupant[i].getExpenses(x)+"\n");
                }
            }
        }
        option();
    }
    
    private static void displayEmpty() {
        /* Displays only empty slots in each cabin */
        for (int x = 1; x < 13; x++)
        {
            for (int i = 1; i < 4; i++) {
                if (cabin[x].occupant[i].getForename(x).equals("e")
                        && cabin[x].occupant[i].getSurname(x).equals("e"))
                    System.out.println("\nPassenger "+i+" in cabin "+x+" is empty");
            }
        }
        option();
    }
    
    private static void deleteCustomer() {
        String choice;
        int cabinNum;
        int passengerNum;
        System.out.print("Enter cabin number or Q to quit: ");
        choice = input.next();
        if (choice.toUpperCase().equals("Q")) menu();
        try {
            cabinNum = Integer.parseInt(choice);
            if (cabinNum >= 1 && cabinNum <= 12)
            {
                System.out.print("Enter passenger number to delete or Q to quit: ");
                choice = input.next();
                if (choice.toUpperCase().equals("Q")) menu();
                passengerNum = Integer.parseInt(choice);
                if (passengerNum >= 1 && passengerNum <= 3)
                {
                    /* Sets same value as when first initialised */
                    cabin[cabinNum].occupant[passengerNum].setForename("e", cabinNum);
                    cabin[cabinNum].occupant[passengerNum].setSurname("e", cabinNum);
                    cabin[cabinNum].occupant[passengerNum].setExpenses("e", "e", cabinNum);
                    System.out.println("Customer deleted\n");
                    
                    /* Replace from queue */
                    queue.takeQueue(); /* Takes forename out of queue */
                    String forename = queue.getFromQueue();
                    queue.takeQueue(); /* Takes surname from queue */
                    String surname = queue.getFromQueue();
                    System.out.println("Adding "+forename+" "+surname+" to cabin "+cabinNum+" as passenger "+passengerNum);
                    cabin[cabinNum].occupant[passengerNum].setForename(forename, cabinNum);
                    cabin[cabinNum].occupant[passengerNum].setSurname(surname,cabinNum);
                    cabin[cabinNum].occupant[passengerNum].setExpenses(forename, surname, cabinNum);
                    
                    
                    option();
                } else {
                    /* Shown if input out of range */
                    System.out.println("Out of range");
                    deleteCustomer();
                }
            } else {
                /* Shown if input out of range */
                System.out.println("Out of range");
                deleteCustomer();
            }
        } catch(NumberFormatException nfe) {
            /* Shown on invalid input (String input) */
            System.out.println("Enter a valid cabin number and passenger number (integer) to Q to quit");
            deleteCustomer();
        }
    }
    
    private static void findCabin() {
        String forename;
        String surname;
        System.out.print("Enter forename: ");
        forename = input.next().toUpperCase();
        System.out.print("Enter surname: ");
        surname = input.next().toUpperCase();
        for (int x = 1; x < 13; x++)
        {
            /* Checks if input is the same as occupant name in specific cabin number */
            for (int i = 1; i < 4; i++){
                if (cabin[x].occupant[i].getForename(x).equals(forename.toUpperCase())
                        && cabin[x].occupant[i].getSurname(x).equals(surname.toUpperCase()))
                    /* Displays what cabin the occupant is located in */
                    System.out.println(forename+" "+surname+" is passenger "+i+" in cabin "+x);
                option();
            }
        }
        System.out.println("Customer not found.");
        option();
    }
    
    private static void store() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("cruiseShip2.txt"));
            
            /* Storing passengers to cabins */
            writer.write("Cruise Ship:\n");
            for (int x = 1; x < 13 ; x++){
                writer.write("\nCabin "+x+":\n\n");
                for (int i = 1; i < 4; i++) {
                    if (cabin[x].occupant[i].getForename(x).equals("e")) {
                        writer.write("Passenger "+i+": Empty\n\n");
                    } else {
                        writer.write("Passenger "+i+": "+cabin[x].occupant[i].getForename(x)
                        +" "+cabin[x].occupant[i].getSurname(x)+"\n");
                        
                        writer.write("Expenses: "+cabin[x].occupant[i].getExpenses(x)+"\n\n");
                    }
                }
            }
            
            /* Store empty cabins in file */
            writer.write("\nEmpty cabins:\n");
            for (int x = 1; x < 13; x++) {
                for (int i = 1; i < 4; i++) {
                    if (cabin[x].occupant[i].getForename(x).equals("e")
                            && cabin[x].occupant[i].getSurname(x).equals("e"))
                        writer.write("\nPassenger "+i+" in cabin "+x+" is empty\n");
                }
            }
            
            /* Store expenses */
            writer.write("\n\nExpenses:\n");
            for (int x = 1; x < 13; x++) {
                int total = 0;
                writer.write("\nCabin " +x+" expenses:\n");
                for (int i = 1; i < 4; i++) {
                    if (cabin[x].occupant[i].getForename(x).equals("e")){

                    } else {
                        writer.write("\nPassenger "+i+": "+cabin[x].occupant[i].getExpenses(x));
                        total = total + cabin[x].occupant[i].getExpenses(x);
                    }
                }
                writer.write("\nTotal = "+total+"\n\n");
            }
            
            writer.close();
            System.out.println("\nData stored");
            option();
        } catch (IOException e) {
            /* File error */
            System.out.println("Error IOExcpetion is "+e);
            option();
        }
    }
    
    private static void load() {
        try {
            /* Reader for file */
            BufferedReader reader = new BufferedReader(new FileReader("cruiseShip2.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            option();
        } catch (IOException e) {
            /* File error */
            System.out.println("Error IOException is " + e);
            menu();
        }
    }
    
    private static void showExpenses() {
        for (int x = 1; x < 13; x++) {
            int total = 0;
            System.out.println("\nCabin " +x+" expenses:\n");
            for (int i = 1; i < 4; i++) {
                if (cabin[x].occupant[i].getForename(x).equals("e")){
                    
                } else {
                    System.out.println("Passenger "+i+": "+cabin[x].occupant[i].getExpenses(x));
                    /* Add to total */
                    total = total + cabin[x].occupant[i].getExpenses(x);
                }
            }
            System.out.println("Total = "+total+"\n");
        }
        option();
    }

}
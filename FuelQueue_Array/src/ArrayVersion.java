import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ArrayVersion {
    public static Scanner input = new Scanner(System.in);    //creating scanner class.
    public static String[][] queue = new String[3][6];  //declaring 2D array.
    public static int fuel = 6600;  //declaring fuel litres.

    public static void main(String[] args) throws IOException {
        for (String[] row : queue)  //initialize array elements.
            Arrays.fill(row, "Empty");
        if(fuel == 500) {
            System.out.println("Warning ! your Stock has only 500l.");  //warning message.
            System.out.println("Remaining Fuel is : " + fuel);
        }

        System.out.println("\n********** Fuel Queue Management System **********\n");
        while(true) {
            System.out.println("\n+=======================================================+");
            System.out.println();
            System.out.println("100 or VFQ : View Fuel Queues.");
            System.out.println("101 or VEQ : View all Empty Queues.");
            System.out.println("102 or ACQ : Add customer from a Queues.");
            System.out.println("103 or RCQ : Remove a customer from a Queues.");
            System.out.println("104 or PCQ : Removed a served customer.");
            System.out.println("105 or VCS : View Customers Sorted in alphabetical order.");
            System.out.println("106 or SPD : Store Program Data into a file.");
            System.out.println("107 or LPD : Load Program Data from file.");
            System.out.println("108 or STK : View Remaining Fuel Stock.");
            System.out.println("109 or AFS : Add Fuel Stock.");
            System.out.println("999 or EXT : Exit the Program.");
            System.out.println();
            System.out.println("\n+=======================================================+");

            System.out.print("Please select an Option : ");
            String option = input.next().toUpperCase();
            switch (option) {
                case "100", "VFQ" -> ViewAllFuelQue();
                case "101", "VEQ" -> ViewAllEmpQue();
                case "102", "ACQ" -> AddCusQue();
                case "103", "RCQ" -> RemCusQue();
                case "104", "PCQ" -> RemServCusQue();
                case "105", "VCS" -> SortAlpha();
                case "106", "SPD" -> LoadFile();
                case "107", "LPD" -> ReadData();
                case "108", "STK" -> ViewRemFuelStock();
                case "109", "AFS" -> AddFuelSto();
                case "999", "EXT" -> {
                    System.out.println(".......... THANK YOU HAVE A GOOD DAY! ..........");
                    System.out.println();
                    return;
                }
                default -> System.out.println("\nInvalid Option Chosen");
            }
        }
    }
    //This method shows all the fuel queues and all the customers who are in the queue.
    public static void ViewAllFuelQue(){
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   100 or VFQ - View Fuel Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        for (int i = 0; i < queue.length; i++){
            System.out.println("\n***** " + " Queue " + (i+1) + " *****");
            for (int j = 0; j < queue[i].length; j++){
                System.out.println("Customer "+ (j+1) +" : "+ queue[i][j]);
            }
        }
    }
    //This method shows all the empty queues. If a queue has only one customer, that respective queue also not empty.
    public static void ViewAllEmpQue(){
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   101 or VEQ - View all Empty Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        for(int i = 0; i < queue.length; i++){
            if (queue[i][0].equals("Empty")){
                System.out.println("Queue "+ (i+1) + " is Empty");
            }else{
                System.out.println("Unfortunately Queue "+ (i+1)+ " is Not Empty");
            }
        }
    }
    //In this, we can add customers to the queue.
    //Program ask you to input which queue number that you want to add a customer.
    public static void AddCusQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   102 or ACQ - Add customer from a Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");


        System.out.print("Enter Queue number (Queue numbers are from 1 to 3) : ");
        int x = input.nextInt();
        if (x <= 3 && x >= 1) {
            for (int j = 0; j <= 6; j++) {
                if (j == 6) {
                    System.out.println();
                    System.out.println("Queue " + (x) + " is Full !");
                    return;
                }
                if (queue[x - 1][j].equals("Empty")) {
                    System.out.print("Enter your Name : ");
                    String name = input.next();
                    queue[x - 1][j] = name;
                    System.out.println("Successfully Added !\n");
                    return;
                }
            }
        }else {
            System.out.println("\nInvalid Input !");
        }

    }
    //You can remove a customer from anywhere you want (between the range that I mentioned).
    //when you removed the customer, the next customer to removed customer is coming to the place of removed customer.
    public static void RemCusQue(){
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                   103 or RCQ - Remove a customer from a Queues.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");


        System.out.print("From which Queue do you want to remove a customer from ? (Queue numbers are between 1 to 3) : ");
        int x = input.nextInt();
        System.out.print("Enter Customer Number (Customer numbers are between 1 to 6) : ");
        int y = input.nextInt();

        if (x <= 3 && x >= 1 && y <= 6 && y >= 1){
            if (queue[x - 1][y - 1].equals("Empty")) {
                System.out.println("\nAlready Empty there !");
            } else if (y == 6) {
                queue[x-1][y-1] = "Empty";
                System.out.println("\nSuccessfully removed a customer !");
            }else {
                queue[x-1][y-1] = queue[x-1][y];
                queue[x-1][y] = "Empty";
                System.out.println("\nSuccessfully removed a customer !");
                queue[x-1][queue[x-1].length - 1] = "Empty";
            }
        }else{
            System.out.println("\nInvalid Input !");
        }
    }
    //First I asked, which queue you want to remove a customer from.
    //After removing the served customer, the next customer come to that place.
    public static void RemServCusQue() {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("               104 or PCQ - Removed a served customer.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        System.out.print("From which Queue do you want to remove a served customer ? (Queue numbers are between 1 to 3) : ");
        int z = input.nextInt();
        if(z <= 3 && z >= 1){
            if (!queue[z - 1][0].equals("Empty")) {
                queue[z - 1][0] = queue[z - 1][1];
                queue[z - 1][1] = "Empty";
                System.out.println("\nSuccessfully removed a served customer !");
                fuel = fuel - 10;
            }else{
                System.out.println("\nThere are not any served customer yet !");
            }
        }else {
            System.out.println("\nInvalid Input !");
        }

    }
    //This will print customer names in alphabetical order.
    //I used bubble sort algorithm.
    public static void SortAlpha(){
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("          105 or VCS - View Customers Sorted in alphabetical order.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        String[][] duplicate = new String[3][6];
        duplicate[0] = new String[6];
        duplicate[1] = new String[6];
        duplicate[2] = new String[6];
        for (int i = 0; i < queue.length; i++){
            int j = 0;
            while (j < queue[i].length) {
               duplicate[i][j] = queue[i][j];
                j++;
            }
        }
        for (int i = 0; i < queue.length; i++){
            for (int j = 0; j < queue[i].length; j++){
                for (int k = 0; k < queue[i].length - 1; k++){
                    if (duplicate[i][k].toLowerCase().compareTo(duplicate[i][k+1].toLowerCase()) > 0){
                        String temp = duplicate[i][k];
                        duplicate[i][k] = duplicate[i][k+1];
                        duplicate[i][k+1] = temp;
                    }
                    if (duplicate[i][k].toLowerCase().compareTo(duplicate[i][k+1].toLowerCase()) > 0){
                        String temp = duplicate[i][k];
                        duplicate[i][k] = duplicate[i][k+1];
                        duplicate[i][k+1] = temp;
                    }
                    if (duplicate[i][k].toLowerCase().compareTo(duplicate[i][k+1].toLowerCase()) > 0){
                        String temp = duplicate[i][k];
                        duplicate[i][k] = duplicate[i][k+1];
                        duplicate[i][k+1] = temp;
                    }
                }
            }
        }
        for (int i = 0; i < duplicate.length; i++){
            for(String temp: duplicate[i]){
                if (!temp.equals("Empty")) {
                    System.out.println("Queue " + (i + 1) + ": "+ temp);
                }
            }

        }
    }
    //This will write program data to file.txt file.
    public static void LoadFile() throws IOException {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("          106 or LPD -  Store Program Data into a file.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        File dataFile = new File("File.txt");
        FileWriter file = new FileWriter(dataFile);
        file.write("\nQueue Details");
        for (int i = 0; i < queue.length; i++){
            file.write("***** " + " Queue " + (i+1) + " *****\n");
            for (int j = 0; j < queue[i].length; j++){
                file.write("\nCustomer "+ (j+1) +" : "+ queue[i][j]);
            }
        }

        file.close();
        System.out.println("PROGRAM DATA SUCCESSFULLY STORED IN THE TEXT FILE");
    }
    //This will write program data to file.txt file.
    public static void ReadData() throws FileNotFoundException {
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("          107 or SPD - Load Program Data from file.                    ");
        System.out.println("|----------------------------------------------------------------------|\n");

        File readFile = new File("File.txt");
        Scanner reader = new Scanner(readFile);
        while (reader.hasNext()) {
            System.out.println(reader.nextLine());
        }
        System.out.println("\n---------- Successfully Read ! --------------");
    }
    //This method prints the remaining fuel stock.
    public static void ViewRemFuelStock(){
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("               108 or STK - View Remaining Fuel Stock.                    ");
        System.out.println("|-------ViewRemFuelStock---------------------------------------------------------------|\n");

        if(fuel == 500){
            System.out.println("\nWarning ! your Stock has only 500l.");
            System.out.println("Remaining Fuel is : "+ fuel);
        }else{
            System.out.println("\nRemaining Fuel is : "+ fuel);
        }
    }
    //This method prints the add fuel stock amount.
    public static void AddFuelSto(){
        System.out.println("\n\n\n|----------------------------------------------------------------------|");
        System.out.println("                  109 or AFS - Add Fuel Stock.                   ");
        System.out.println("|----------------------------------------------------------------------|\n");

        int served_fuel = 6600 - fuel;
        System.out.println("\nAdd fuel stock amount is : "+ (6600-served_fuel));

    }

}

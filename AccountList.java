package classes_folder;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AccountList {
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String yellow = "\u001B[33m";
    String blue = "\u001B[34m";
    private Node head;

    public AccountList() {
        head = null;
    }

    public void insert(int accNum, double bal, String n, String c, String p, String addr,String d) {
        Node newNode = new Node(accNum, bal, n, c, p, addr,d);
        newNode.next = head;
        head = newNode;
    }

    public Node getHead() {
        return head;
    }
    void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence to clear screen
        System.out.flush();
    }
    void pressfunction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(blue + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
        scanner.nextLine(); // Wait for the user to press Enter
    }
    void loadingAnimation() {
        String green = "\u001B[32m";
        System.out.print(green+"\n\t\t\t\t\tLoading ");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
        System.out.println();
    }
    void heading(){
        String green = "\u001B[32m";
        System.out.println(blue + "\t\t\t\t\t\t::::::::::::::::::::::::::::::");
        System.out.println(blue + "\t\t\t\t\t\t::         ACCOUNTS         ::");
        System.out.println(blue + "\t\t\t\t\t\t::::::::::::::::::::::::::::::");
    }

    public Node find(int accNum) {
        Node current = head;
        while (current != null) {
            if (current.accountNumber == accNum) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void accountMenu() {
        Scanner scanner = new Scanner(System.in);
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";
        String green = "\u001B[32m";

        clearScreen();
        heading();
        System.out.println(green+"\n\n\t\t\t\t\t\tAccounts Information" + reset);
         
        System.out.print(green+ "\n\t\t\t\t\t\tEnter account number to view details: " + reset);
        int accNum = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Node current = find(accNum);
        if (current != null) {
            clearScreen();
            System.out.println(yellow + "\n\n\n\t\t\t\t\t\t-------------------------");
            System.out.println("\n\t\t\t\t\t\tAccount Number: " + current.accountNumber);
            System.out.println("\n\t\t\t\t\t\tName: " + current.name);
            System.out.println("\n\t\t\t\t\t\tCNIC: " + current.cnic);
            System.out.println("\n\t\t\t\t\t\tPhone Number: " + current.phoneNo);
            System.out.println("\n\t\t\t\t\t\tAddress: " + current.address);
            System.out.println("\n\t\t\t\t\t\tDate: " + current.date);
            System.out.println("\n\t\t\t\t\t\tBalance: " + current.balance);
          //  System.out.println("\n\t\t\t\t\t\tBalance: " + current.accountType);
            System.out.println("\n\t\t\t\t\t\t-------------------------");
                System.out.print(yellow + "\n\t\t\t\t\t\tDo you want to view another account? (Y/N): " + reset);
                String option = scanner.nextLine();
        
                if (option.equalsIgnoreCase("Y")) {
                    accountMenu(); // Restart the display process for another account
                    return; // Exit the current call to avoid infinite recursion
                } else if (option.equalsIgnoreCase("N")) {
                    return; // Go back to main menu
                } else {
                    System.out.println(red + "\n\t\t\t\t\t\tInvalid choice. Please enter 'Y' or 'N'." + reset);
                }
            
    }
    else {
            System.out.println(blue + "\n\t\t\t\t\t\tAccount not found!" + reset);
            pressfunction();

            try {
                Thread.sleep(1000); // Wait for 2 seconds to display the message
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
        }
    }
    public int countAccounts() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    public void displayAllAccounts() {
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";
        String yellow = "\u001B[33m";
        String red = "\u001B[31m";
        Scanner scanner = new Scanner(System.in);
    
        Node current = head;
        
        if (current == null) {
            System.out.println(blue + "\n\t\t\t\t\t\tNo accounts available." + reset);
            return;
        }
    
        while (current != null) {
            System.out.println(yellow+"\n\n\n\t\t\t\t\t\tAccount Number: " + current.accountNumber);
            System.out.println("\n\t\t\t\t\t\tName: " + current.name);
            System.out.println("\n\t\t\t\t\t\tCNIC: " + current.cnic);
            System.out.println("\n\t\t\t\t\t\tPhone Number: " + current.phoneNo);
            System.out.println("\n\t\t\t\t\t\tAddress: " + current.address);
            System.out.println("\n\t\t\t\t\t\tDate: " + current.date);
            System.out.println("\n\t\t\t\t\t\tBalance: " + current.balance);
            System.out.println("\n\t\t\t\t\t\t-------------------------");
            current = current.next;
        }

    }
    public void saveToFile() {
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";

        try (PrintWriter writer = new PrintWriter("account_data.txt")) {
            Node current = head;
            while (current != null) {
                writer.println(current.accountNumber + " " + current.balance + " " +
                        current.name + " " + current.cnic + " " + current.phoneNo + " " + current.address+" " + current.date);
                current = current.next;
            }
        } catch (FileNotFoundException e) {
        }
    }

    public void loadFromFile() {
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";

        try (Scanner scanner = new Scanner(new File("account_data.txt"))) {
            while (scanner.hasNextLine()) {
                int accNum = scanner.nextInt();
                double bal = scanner.nextDouble();
                String name = scanner.next();
                String cnic = scanner.next();
                String phoneNo = scanner.next();
                String address = scanner.nextLine().trim();
                String date = scanner.next();
                insert(accNum, bal, name, cnic, phoneNo, address,date);
            }
        } catch (FileNotFoundException e) {
   
        }
    }

    // Inner Node class
    public static class Node {
        int accountNumber;
        public double balance;
        String name;
        String cnic;
        public String phoneNo;
        public String address;
        public String date;
        Node next;

        Node(int accNum, double bal, String n, String c, String p, String addr,String d) {
            this.accountNumber = accNum;
            this.balance = bal;
            this.name = n;
            this.cnic = c;
            this.phoneNo = p;
            this.address = addr;
            this.date = d;
            this.next = null;
        }
    }
        public boolean account_login() {
            String yellow = "\u001B[33m";
            String reset = "\u001B[0m";
            String red = "\u001B[31m";
            String magenta = "\u001B[35m";
            String blue = "\u001B[34m";
            Scanner scanner = new Scanner(System.in);
            String loginId = "";
            String loginPassword = "";
    
            Console console = System.console();
            if (console == null) {
                System.out.println("No console available");
                return false; // Return to main menu
            }
    
            boolean idValid = false;
    
            while (true) {
                clearScreen();
                heading();
                System.out.println(magenta + "\n\n\n\t\t\t\t\t\t| Security is required |" + reset);
    
                if (!idValid) {
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter your ID: ");
                    try {
                        loginId = scanner.nextLine();
                    } catch (NoSuchElementException e) {
                        System.out.println(red + "\n\t\t\t\t\t\tNo input found. Returning to main menu..." + reset);
                        return false;
                    }
    
                    if (!loginId.equals("account")) {
                        System.out.println(red + "\n\t\t\t\t\t\tInvalid ID. Please try again." + reset);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue; // Restart the loop to prompt for ID again
                    } else {
                        idValid = true;
                    }
                } else {
                    System.out.println(blue + "\n\t\t\t\t\t\tEnter your ID: " + loginId + reset);
                }
    
                // Prompt for password
                String password = PasswordField.readPassword("\n\t\t\t\t\t\tEnter your password: ");
                if (password == null || password.isEmpty()) {
                    System.out.println("No password entered");
                    continue; // Restart the loop to prompt for password again
                } else {
                    loginPassword = password;
                }
    
                if (loginId.equals("account") && loginPassword.equals("account123")) {
                    loadingAnimation();
                    clearScreen();
                    return true; // Successful login
                } else {
                    System.out.println(red + "\n\t\t\t\t\t\tYour password is incorrect. Returning to the main menu..." + reset);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return false; // Return to main menu after displaying error
                }
            }
        }
    }

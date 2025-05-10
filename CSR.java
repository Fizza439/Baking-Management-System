package classes_folder;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CSR {
    private static int nextAccountNumber = 5000;
    private String id;
    private String accountNumber;
    public AccountList accounts;
    String reset = "\u001B[0m";
    String blue = "\u001B[34m";

    public CSR(AccountList accounts) {
        id = "ID" + nextAccountNumber++;
        this.accounts = accounts;
        this.accounts.loadFromFile(); // Add this line to load accounts from file
    }

    String getID() {
        return id;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence to clear screen
        System.out.flush();
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
    String blue = "\u001B[34m";
    System.out.println(blue + "\t\t\t\t\t\t::::::::::::::::::::::::::::::");
    System.out.println("\t\t\t\t\t\t::          CSR             ::");
    System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::" + reset);} 
    

    int createAccount(double initialBalance) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\t\t\t\t\t\tEnter name: ");
        String name = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t\tEnter CNIC: ");
        String cnic = scanner.nextLine();

        System.out.print("\n\t\t\t\t\t\tEnter phone number: ");
        String phoneNo = scanner.nextLine();

        System.out.print("\n\t\t\t\t\t\tEnter address: ");
        String address = scanner.nextLine();

        System.out.print("\n\t\t\t\t\t\tEnter Date: ");
        String date = scanner.nextLine();
        
        // Create the account
        accounts.insert(nextAccountNumber, initialBalance, name, cnic, phoneNo, address,date);

        // Print the account number
        System.out.println(blue + "\n\t\t\t\t\t\tNew account created with Account Number: " + nextAccountNumber + reset);

        // Increment account number for the next account
        nextAccountNumber++;

        // Save account data to file
        accounts.saveToFile();

        // Prompt the user to press Enter to continue
        System.out.println(blue + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
        scanner.nextLine(); // Wait for the user to press Enter

        return nextAccountNumber - 1; // Return the newly created account number
    }

    public void updateAccount(int accountNumber) {
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";
        String yellow = "\u001B[33m";
        String cyan = "\u001B[36m";
        String red = "\u001B[31m";
        AccountList.Node account = accounts.find(accountNumber);
        if (account != null) {
            Scanner scanner = new Scanner(System.in);
            clearScreen();
            System.out.println(cyan + "\n\t\t\t\t\t\tAccount found!" + reset);
            System.out.println(blue + "\n\t\t\t\t\t\tChoose which information you want to update:");
            System.out.println(yellow + "\n\t\t\t\t\t\t1. Phone Number");
            System.out.println("\n\t\t\t\t\t\t2. Address");
            System.out.println("\n\t\t\t\t\t\t3. Name");
            System.out.print("\n\t\t\t\t\t\tEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            String green = "\u001B[32m";
            switch (choice) {
                case 1:
                clearScreen();
                    System.out.print(blue + "\n\n\t\t\t\t\t\tEnter new phone number: " + reset);
                    String newPhoneNo = scanner.nextLine();
                    account.phoneNo = newPhoneNo;
                    System.out.println(green + "\n\t\t\t\t\t\tPhone number updated successfully." + reset);
                    break;
                case 2:
                clearScreen();
                
                    System.out.print(blue + "\n\n\n\t\t\t\t\tEnter new address: " + reset);
                    String newAddress = scanner.nextLine();
                    account.address = newAddress;
                    System.out.println(green + "\n\t\t\t\t\t\tAddress updated successfully." + reset);
                    break;
                case 3:
                clearScreen();
                    System.out.print(blue + "\n\n\n\t\t\t\t\tEnter name to update: " + reset);
                    String newName = scanner.nextLine();
                    account.name = newName;
                    System.out.println(green + "\n\t\t\t\t\t\tName updated successfully." + reset);
                    break;
                default:
                    System.out.println(red + "\n\t\t\t\t\t\tInvalid choice." + reset);
            }

            accounts.saveToFile();

            // Display "Press Enter to continue" and wait for user input
            System.out.println(yellow + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
            scanner.nextLine(); // Wait for the user to press Enter
        } else {
            System.out.println(red + "\n\t\t\t\t\t\tAccount not found!" + reset);
            try {
                Thread.sleep(800); // Wait for 2 seconds to display the message
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
        }
    }

    public void csrMenu() throws InterruptedException {
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            clearScreen();
            heading();
            System.out.println(yellow + "\n\n\n\t\t\t\t\t\tCSR Menu:");
            System.out.println("\n\t\t\t\t\t\t1. Create Account");
            System.out.println("\n\t\t\t\t\t\t2. Update Account");
            System.out.println("\n\t\t\t\t\t\t3. Go back to main menu" + reset);
            System.out.print(blue + "\n\t\t\t\t\t\tEnter your choice: " + reset);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        clearScreen();
                        heading();
                        System.out.println(yellow + "\n\n\n\t\t\t\t\t\tAdd New Account" + reset);
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter initial balance: " + reset);
                        double initialBalance = scanner.nextDouble();
                        createAccount(initialBalance); // Create new account
                        break;
                    case 2:
                        clearScreen();
                        heading();
                        System.out.println(yellow + "\n\n\n\t\t\t\t\t\tUpdate Account" + reset);
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter account number to update: " + reset);
                        int accNum = scanner.nextInt();
                        updateAccount(accNum);
                        break;
                    case 3:
                        clearScreen();
                        System.out.println("\n\t\t\t\t\t\tGoing back to main menu...");
                        return; // Exit the method after choosing option 3
                    default:
                        clearScreen();
                        System.out.println(red + "\n\t\t\t\t\t\tInvalid choice." + reset);
                        Thread.sleep(2000); // Sleep for 2 seconds
                        break;
                }
            } catch (InputMismatchException e) {
                clearScreen();
                System.out.println(red + "\n\t\t\t\t\t\tInvalid input. Please enter a number." + reset);
                Thread.sleep(2000); // Sleep for 2 seconds
                scanner.next(); // Clear the invalid input
            }
        }
    }

        public boolean csr_login() {
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
    
                    if (!loginId.equals("csr")) {
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
                if (loginId.equals("csr") && loginPassword.equals("csr123")) {
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


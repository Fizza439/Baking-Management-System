package classes_folder;

import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Manager {
    String yellow = "\u001B[33m";
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String magenta = "\u001B[35m";
    String cyan = "\u001B[36m";
    String blue = "\u001B[34m";
    String green = "\u001B[32m";
    public Teller teller;
    public classes_folder.AccountList account;
    public Employee employee;


    void pressFunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t\tPress Enter to continue...");
        scanner.nextLine(); // Wait for the user to press Enter
    }

    public Manager(AccountList accounts) {
        this.account = accounts;
        this.teller = new Teller(accounts);
        this.employee = new Employee(); // Initialize the Employee object
    }

    void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence to clear screen
        System.out.flush();
    }

    void loadingAnimation() {
        String green = "\u001B[32m";
        System.out.print(green + "\n\t\t\t\t\tLoading ");
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

    void pressfunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(blue + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
        scanner.nextLine(); // Wait for the user to press Enter
    }
    void heading(){
        System.out.println(blue + "\t\t\t\t\t\t:::::::::::::::::::::::::::::::");
        System.out.println("\t\t\t\t\t\t::          MANAGER          ::");
        System.out.println("\t\t\t\t\t\t:::::::::::::::::::::::::::::::" + reset);
    }
    public void managerMenu() {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        String yellow = "\u001B[33m";

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            clearScreen();
            heading();
            System.out.println(yellow + "\n\n\t\t\t\t\t\t Manager Menu");
            System.out.println("\n\t\t\t\t\t\t1. To view accounts");
            System.out.println("\n\t\t\t\t\t\t2. To view employees data");
            System.out.println("\n\t\t\t\t\t\t3. To check balance of account");
            System.out.println("\n\t\t\t\t\t\t4. MIS 1o1 report");
            System.out.println("\n\t\t\t\t\t\t5. To go back to main menu" + reset);
            System.out.println(blue + "\n\t\t\t\t\t\tEnter your choice");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (choice) {
                    case 1:
                        clearScreen();
                        System.out.println(blue + "\n\n\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t\t\t\t::          View Accounts          ::");
                        System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::" + reset);
                        account.accountMenu();
                        break;
                    case 2:
                        clearScreen();
                        System.out.println(blue + "\n\n\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t\t\t\t::        View Employees Data       ::");
                        System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::" + reset);
                        employee.displayEmployeeById(scanner);
                        break;
                    case 3:
                        clearScreen();
                        System.out.println(blue + "\n\n\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t\t\t\t::       Check Account Balance      ::");
                        System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::" + reset);
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter account number: ");
                        int viewAccNum = scanner.nextInt();
                        teller.viewBalance(viewAccNum); // View account balance
                        
                        break;
                    case 4:
                        clearScreen();
                        System.out.println(blue + "\n\n\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::");
                        System.out.println("\t\t\t\t\t\t::            MIS 1O1 Report        ::");
                        System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::" + reset);
                        System.out.println(yellow + "\n\t\t\t\t\t\t Accounts Information"+reset);
                        account.displayAllAccounts();
                
                        // Display the total number of accounts
                        int totalAccounts = account.countAccounts();
                        System.out.println(yellow + "\n\t\t\t\t\t\tTotal Number of Accounts: " + totalAccounts + reset);
                        System.out.println(blue+"\n\t\t\t\t\t\tPress enter to go back to main menu..."+reset);
                        scanner.nextLine(); // Pause until the user presses enter
                        clearScreen();
                        break;
                    case 5:
                        System.out.println("\n\t\t\t\t\t\tExiting...");
                        break;
                    default:
                        System.out.println(red + "\n\t\t\t\t\t\tInvalid choice. Please try again." + reset);
                        pressFunction();
                }
            } else {
                System.out.println(red + "\n\t\t\t\t\t\tInvalid input. Please enter a number." + reset);
                pressFunction();
                scanner.next(); // Clear the invalid input
            }
        } while (choice != 5);
    }

    public boolean manager_login() {
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
    
                    if (!loginId.equals("manager")) {
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

            if (loginId.equals("manager") && loginPassword.equals("manager123")) {
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
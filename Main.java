import java.util.Scanner;

import classes_folder.AccountList;
import classes_folder.CSR;
import classes_folder.Employee;
import classes_folder.Manager;
import classes_folder.Teller;
import classes_folder.intro_page;
import classes_folder.loan_Officer;
public class Main {

    public static void main(String[] args) {
        intro_page introPage = new intro_page();
        String reset = "\u001B[0m";
        String magenta = "\u001B[35m";
        String cyan = "\u001B[36m";
        String black = "\u001B[30m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        class Terminate{

            public void TerminatingAnimation() {
                String green = "\u001B[32m";
                System.out.print(green + "\n\n\n\n\t\t\t\t\tTerminating ");
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Preserve the interrupt status
                        e.printStackTrace();
                    }
                    System.out.print(".");
                }
                System.out.println();
            }
        }
        AccountList accounts = new AccountList();
        try {
            accounts.loadFromFile(); // Ensure accounts are loaded from the file
        } catch (Exception e) {
            System.out.println(red + "Error loading accounts from file: " + e.getMessage() + reset);
            // Optionally, you can log the exception or take other actions here
        }

        CSR csr = new CSR(accounts);
        Manager manager = new Manager(accounts);

        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            introPage.intro();
            scanner.nextLine();
            introPage.clearScreen();
            System.out.println(blue + "\n\n\n\t\t\t\t\t\t:::::::::::::::::::::");
            System.out.println(blue + "\t\t\t\t\t\t::    Main menu    ::");
            System.out.println(blue + "\t\t\t\t\t\t:::::::::::::::::::::");
            System.out.println(yellow + "\n\t\t\t\t\t\t1.CSR Information");
            System.out.println("\n\t\t\t\t\t\t2.Teller Information");
            System.out.println("\n\t\t\t\t\t\t3.Loan Officer");
            System.out.println("\n\t\t\t\t\t\t4.Accounts Information");
            System.out.println("\n\t\t\t\t\t\t5.Employee Information");
            System.out.println("\n\t\t\t\t\t\t6.Manager Information");
            System.out.println("\n\t\t\t\t\t\t7.To Terminate");
            System.out.print(blue + "\n\t\t\t\t\t\tEnter your choice: " + reset);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        boolean loginSuccessful = csr.csr_login();
                        if (loginSuccessful) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Returning to main menu...");
                            break;
                        }
                        csr.csrMenu();
                        break;
                    case 2:
                        Teller teller = new Teller(accounts);
                       // teller.teller_login();
                    boolean loginteller =teller.teller_login();
                    if (loginteller) {
                    System.out.println("Login successful!");
                    } else {
                    System.out.println("Returning to main menu...");
                    break;
                    }
                        teller.tellerMenu();
                        break;
                    case 3:
                        loan_Officer officer = new loan_Officer(accounts);
                       // officer.loanOfficer_login();
                    boolean loginloan = officer.loanOfficer_login();
                    if (loginloan) {
                    System.out.println("Login successful!");
                    } else {
                    System.out.println("Returning to main menu...");
                    break;
                    }
                        officer.processLoan();
                        break;
                    case 4:
                    
                    boolean loginaccount = accounts.account_login();
                    if (loginaccount) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Returning to main menu...");
                        break;
                    }
                        accounts.accountMenu();
                        break;
                    case 5:
                        Employee employee = new Employee();
                       // employee.employee_login();
                    boolean loginemployee = employee.employee_login();
                    if (loginemployee) {
                    System.out.println("Login successful!");
                    } else {
                    System.out.println("Returning to main menu...");
                    break;
                    }
                        employee.employeeMenu();
                        break;
                    case 6:
                        Manager manager2 = new Manager(accounts);
                        //manager2.manager_login();
                        boolean loginmanager = manager.manager_login();
                        if (loginmanager) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Returning to main menu...");
                            break;
                        }
                        manager2.managerMenu();
                        break;
                    case 7:
                    introPage.clearScreen();
                    Terminate t = new Terminate();
                    t.TerminatingAnimation();
                        return; // Exit the program
                    default:
                    introPage.clearScreen();
                    System.out.println(red + "\n\n\n\t\t\t\t\t\tInvalid choice" + reset);
                    try {
                        Thread.sleep(800); // Pause for 2 seconds
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // Preserve the interrupt status
                        ie.printStackTrace();
                    }
                    System.err.println();
                    scanner.nextLine(); // Consume the invalid input
                    introPage.clearScreen();
                }
            } catch (Exception e) {
                introPage.clearScreen();
                System.out.println(red + "\n\n\n\t\t\t\t\t\tEnter correct option" + reset);
                try {
                    Thread.sleep(800); // Pause for 2 seconds
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Preserve the interrupt status
                    ie.printStackTrace();
                }
                System.err.println();
                scanner.nextLine(); // Consume the invalid input
                introPage.clearScreen();
        }
    }
}
}



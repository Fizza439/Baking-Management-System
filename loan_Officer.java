package classes_folder;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class loan_Officer {
    String yellow = "\u001B[33m";
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String magenta = "\u001B[35m";
    String cyan = "\u001B[36m";
    String blue = "\u001B[34m";
    String green = "\u001B[32m";
    String brightMagenta = "\u001B[95m";
    private AccountList accounts; // Add this
    private int accountCount = 4000; // Starting account number

    public loan_Officer(AccountList accounts) {
        this.accounts = accounts;
    }

    public Loan getApprovedLoans() {
        return approvedLoansHead;
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
    void pressfunction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(blue + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
        scanner.nextLine(); // Wait for the user to press Enter
    }
void heading(){
    System.out.println(blue+"\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::");
    System.out.println("\t\t\t\t\t\t::          LOAN OFFICER          ::");
    System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::"+reset);
}
    private static class Node {
        int accNum;
        double totalAmount;
        String name;
        String cnic;
        String phoneNo;
        String address;
        String date;
        Node next;

        Node(int accNum, double totalAmount, String name, String cnic, String phoneNo, String address,String date) {
            this.accNum = accNum;
            this.totalAmount = totalAmount;
            this.name = name;
            this.cnic = cnic;
            this.phoneNo = phoneNo;
            this.address = address;
            this.date = date;
            next = null;
        }
    }

    public void insert(int accNum, double totalAmount, String name, String cnic, String phoneNo, String address,String date) {
        Node newNode = new Node(accNum, totalAmount, name, cnic, phoneNo, address,date);
        Node head = null;
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    private static class LoanApplication {
        String name;

        double income;
        double requestedAmount;
        int creditScore;
        LoanApplication next;

        LoanApplication(String n, double inc, double reqAmt, int credit) {
            name = n;
            income = inc;
            requestedAmount = reqAmt;
            creditScore = credit;
            next = null;
        }
    }

    private static class Loan {
        String name;
        double amount;
        Loan next;

        Loan(String n, double amt) {
            name = n;
            amount = amt;
            next = null;
        }
    }

    private LoanApplication pendingApplicationsHead;
    private Loan approvedLoansHead;

    // Function to submit a loan application
    void submitLoanApplication(String name, double income, double requestedAmount, int creditScore) {
        LoanApplication application = new LoanApplication(name, income, requestedAmount, creditScore);
        if (pendingApplicationsHead == null) {
            pendingApplicationsHead = application;
        } else {
            LoanApplication current = pendingApplicationsHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = application;
        }
        System.out.println(green+"\n\t\t\t\t\t\tLoan application submitted for " + name+reset);
    }

    // Function to approve a loan application
    void approveLoan(String name, double approvedAmount) {
        LoanApplication prev = null;
        LoanApplication current = pendingApplicationsHead;
        while (current != null && !current.name.equals(name)) {
            prev = current;
            current = current.next;
        }
        if (current != null) {
            if (approvedAmount <= current.requestedAmount) {
                Loan loan = new Loan(name, approvedAmount);
                loan.next = approvedLoansHead;
                approvedLoansHead = loan;
                System.out.println(yellow+"\n5\t\t\t\t\t\tLoan approved for " + name + " with amount " + approvedAmount+reset);
                // Remove from pending applications
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    pendingApplicationsHead = current.next;
                }
            } else {
                System.out.println(red+"\n\t\t\t\t\t\tLoan amount exceeds requested amount for " + name+reset);
            }
        } else {
            System.out.println(green+"\n\t\t\t\t\t\tNo pending application found for " + name+reset);
        }
    }

    // Function to disburse a loan
    void disburseLoan(String name, String cnic, String phoneNo, String address,String date) {
        Scanner scanner = new Scanner(System.in);

        Loan prev = null;
        Loan current = approvedLoansHead;
        while (current != null && !current.name.equals(name)) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            double interest = current.amount * 0.30; // Calculating 30% interest
            double totalAmount = current.amount + interest;
            System.out.println(yellow+"\n\t\t\t\t\t\tLoan disbursed for " + name + " with amount " + current.amount + " and interest of "
                    + interest + ". Total amount: " + totalAmount+reset);

            // Increment account count
            accountCount++;
            // Get available account number and increment account count
            int accNum = accountCount++;

            // Remove from approved loans
            if (prev != null) {
                prev.next = current.next;
            } else {
                approvedLoansHead = current.next;
            }
            // Add the new loan account information to the accounts list
            if (accounts != null) {
                accounts.insert(accNum, totalAmount, name, cnic, phoneNo, address,date);
            }


            // Display the account number
            System.out.println(green+"\n\t\t\t\t\t\tLoan account created with account number: " + accNum+reset);
            pressfunction();
        }  else {
            System.out.println(red+"\n\t\t\t\t\t\tNo approved loan found for " + name+reset);
            pressfunction();
        }
    }

    // Function to process loan application, approval, and disbursement
    public void processLoan() {
        Scanner scanner = new Scanner(System.in);
    
        try {
            int accountNumber = 0; // Initialize accountNumber
            String cnic = ""; // Initialize cnic
            String phoneNo = ""; // Initialize phoneNo
            String address = "";
            String date = ""; // Initialize date
    
            // Submit loan application
            clearScreen();
            heading();
            System.out.println(yellow + "\n\t\t\t\t\t\t\t\t Loan menu");
            System.out.println("\n\t\t\t\t\t\t1. Add new Loan Account");
            System.out.println("\n\t\t\t\t\t\t2. Go to main menu" + reset);
            System.out.print(blue + "\n\t\t\t\t\t\tEnter your choice: " + reset);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    clearScreen();
                    heading();
                    System.out.println(yellow + "\n\t\t\t\t\t\tLoan Application Submission:" + reset);
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's name: " + reset);
                    String name = scanner.next();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's income: " + reset);
                    double income = scanner.nextDouble();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter requested loan amount: " + reset);
                    double requestedAmount = scanner.nextDouble();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's credit score: " + reset);
                    int creditScore = scanner.nextInt();
    
                    submitLoanApplication(name, income, requestedAmount, creditScore);
    
                    // Approve loan
                    clearScreen();
                    heading();
                    System.out.println(yellow + "\n\t\t\t\t\t\tLoan Approval:" + reset);
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's name for loan approval: " + reset);
                    name = scanner.next();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter approved loan amount: " + reset);
                    double approvedAmount = scanner.nextDouble();
    
                    approveLoan(name, approvedAmount);
                    // Disburse loan
                    clearScreen();
                    heading();
                    System.out.println(yellow + "\n\t\t\t\t\t\tLoan Disbursement:" + reset);
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's name for loan disbursement: " + reset);
                    name = scanner.next();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's CNIC: " + reset);
                    cnic = scanner.next();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's phone number: " + reset);
                    phoneNo = scanner.next();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter applicant's address: " + reset);
                    address = scanner.next();
                    System.out.print(blue + "\n\t\t\t\t\t\tEnter Date: " + reset);
                    date = scanner.next();
    
                    disburseLoan(name, cnic, phoneNo, address, date);
                    break;
                case 2:
                    System.out.println("\n\t\t\t\t\t\tExiting...");
                    break;
    
                default:
                    clearScreen();
                    System.out.println(red + "\n\t\t\t\t\t\tInvalid choice." + reset);
                    try {
                        Thread.sleep(800); // Sleep for 2 seconds
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                    }
                    break;
            }
        } catch (Exception e) {
            clearScreen();
            System.out.println(red + "\n\t\t\t\t\t\tInvalid input. Please enter a number." + reset);
            try {
                Thread.sleep(800); // Sleep for 2 seconds
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
            scanner.next(); // Clear the invalid input
        }
    }
    

        
    public void saveToFile() {
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";

        try (PrintWriter writer = new PrintWriter("loan_data.txt")) {
            Loan current = approvedLoansHead;
            while (current != null) {
                writer.println(current.name + " " + current.amount);
                current = current.next;
            }
         //   System.out.println(cyan+ "Loan data saved successfully."+reset);
        } catch (FileNotFoundException e) {
            System.err.println(red+"Error: Unable to open file for saving loan data. Error message: " +reset+e.getMessage());
        }
    }

    // Load data from file
    public void loadFromFile() {
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";

        try (Scanner scanner = new Scanner(new File("loan_data.txt"))) {
            while (scanner.hasNextLine()) {
                int accNum = scanner.nextInt();
                double totalAmount = scanner.nextDouble();
                double interest = scanner.nextDouble();
                String name = scanner.next();
                String cnic = scanner.next();
                String phoneNo = scanner.next();
                String address = scanner.nextLine().trim();
                String date = scanner.next();
                insert(accNum, totalAmount, name, cnic, phoneNo, address,date);
            }
          //  System.out.println(blue + "Account data loaded from file.");
        } catch (FileNotFoundException e) {
            System.err.println(red+"Error: Unable to open file for loading account data. Error message: " + e.getMessage());
        }
    }
    public boolean loanOfficer_login() {
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        String brightMagenta = "\u001B[95m";
    
        Scanner scanner = new Scanner(System.in);
        String loginId = "";
        String loginPassword = "";
    
        Console console = System.console();
        if (console == null) {
            System.out.println("No console available");
            return false;
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
    
                    if (!loginId.equals("loan")) {
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

            if (loginId.equals("loan") && loginPassword.equals("loan123")) {
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

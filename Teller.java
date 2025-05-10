package classes_folder;
import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Teller {
    private String accountNumber;
    private AccountList accounts;
    private Queue<Integer> accountQueue;
    private String reset = "\u001B[0m";
    private String blue = "\u001B[34m";
    String yellow = "\u001B[33m";
    private int depositCount;
    private int withdrawalCount;

    public Teller(AccountList accounts) {
        this.accounts = accounts;
        this.accountQueue = new Queue<>();
        this.depositCount = 0;
        this.withdrawalCount = 0;
        populateAccountQueue();
    }

    private void populateAccountQueue() {
        AccountList.Node current = accounts.getHead();
        while (current != null) {
            accountQueue.enqueue(current.accountNumber);
            current = current.next;
        }
    }

    String getAccountNumber() {
        return accountNumber;
    }

    void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence to clear screen
        System.out.flush();
    }

    void pressfunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(blue + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
        scanner.nextLine(); // Wait for the user to press Enter
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
    void heading(){
        System.out.println(blue+"\t\t\t\t\t\t::::::::::::::::::::::::::::::");
        System.out.println("\t\t\t\t\t\t::          TELLER          ::");
        System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::"+reset);
    }

    void deposit(int accountNumber, double amount) {
        if (accountQueue.contains(accountNumber)) {
            AccountList.Node account = accounts.find(accountNumber);
            if (account != null) {
                account.balance += amount;
                System.out.println(blue + "\n\t\t\t\t\t\tDeposit successful. New balance: " + account.balance + reset);
                accounts.saveToFile(); // Save account data to file after depositing
                pressfunction();
            }
        } else {
            System.out.println(blue + "\n\t\t\t\t\t\tAccount not found!" + reset);
            pressfunction();
        }
    }

    void withdraw(int accountNumber, double amount) {
        if (accountQueue.contains(accountNumber)) {
            AccountList.Node account = accounts.find(accountNumber);
            if (account != null) {
                if (account.balance >= amount) {
                    account.balance -= amount;
                    System.out.println(blue + "\n\t\t\t\t\t\tWithdrawal successful. New balance: " + account.balance + reset);
                    accounts.saveToFile(); // Save account data to file after withdrawing
                    pressfunction();
                } else {
                    System.out.println(blue + "\n\t\t\t\t\t\tInsufficient balance!" + reset);
                    pressfunction();
                }
            }
        } else {
            System.out.println(blue + "\n\t\t\t\t\t\tAccount not found!" + reset);
            pressfunction();
        }
    }

    void viewBalance(int accountNumber) {
        if (accountQueue.contains(accountNumber)) {
            String red = "\u001B[31m";
            AccountList.Node account = accounts.find(accountNumber);
            if (account != null) {
                System.out.println(blue + "\n\t\t\t\t\t\tCurrent balance: " + account.balance + reset);
                pressfunction();
            }
        } else {
            System.out.println(blue + "\n\t\t\t\t\t\tAccount not found!" + reset);
            pressfunction();
        }
    }
    public void tellerMenu() {
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            clearScreen();
            heading();
            System.out.println(yellow + "\n\n\n\t\t\t\t\t\tTeller Menu:");
            System.out.println("\n\t\t\t\t\t\t1. Deposit");
            System.out.println("\n\t\t\t\t\t\t2. Withdraw");
            System.out.println("\n\t\t\t\t\t\t3. View Balance");
            System.out.println("\n\t\t\t\t\t\t4. Go back to main menu" + reset);
            System.out.print(blue + "\n\t\t\t\t\t\tEnter your choice: ");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        clearScreen();
                        heading();
                        System.out.println(yellow + "\n\n\n\t\t\t\t\t\tDeposit Money");
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter account number: ");
                        int depAccNum = scanner.nextInt();
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter amount to deposit: ");
                        double depAmount = scanner.nextDouble();
                        deposit(depAccNum, depAmount); // Perform deposit
                        break;
                    case 2:
                        clearScreen();
                        heading();
                        System.out.println(yellow + "\n\n\n\t\t\t\t\t\tWithdraw Money");
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter account number: ");
                        int withAccNum = scanner.nextInt();
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter amount to withdraw: ");
                        double withAmount = scanner.nextDouble();
                        withdraw(withAccNum, withAmount); // Perform withdrawal
                        break;
                    case 3:
                        clearScreen();
                        heading();
                        System.out.println(yellow + "\n\n\n\t\t\t\t\t\tView Balance" + reset);
                        System.out.print(blue + "\n\t\t\t\t\t\tEnter account number: ");
                        int viewAccNum = scanner.nextInt();
                        viewBalance(viewAccNum); // View account balance
                        break;
                    case 4:
                        clearScreen();
                        System.out.println("\n\t\t\t\t\t\tExiting...");
                        return; // Exit the method after choosing option 4
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
    }


        public boolean teller_login() {
        String yellow = "\u001B[33m";
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String magenta = "\u001B[35m";
        String cyan = "\u001B[36m";
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
    
                    if (!loginId.equals("teller")) {
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
            if (loginId.equals("teller") && loginPassword.equals("teller123")) {
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

    private static class Queue<T> {
        private static class Node<T> {
            private T data;
            private Node<T> next;
    
            public Node(T data) {
                this.data = data;
                this.next = null;
            }
        }
    
        private Node<T> front;
        private Node<T> rear;
    
        public Queue() {
            this.front = null;
            this.rear = null;
        }
    
        public boolean isEmpty() {
            return front == null;
        }
    
        public void enqueue(T item) {
            Node<T> newNode = new Node<>(item);
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }
    
        public T dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return data;
        }
    
        public boolean contains(T item) {
            Node<T> current = front;
            while (current != null) {
                if (current.data.equals(item)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }
}
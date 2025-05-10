package classes_folder;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Employee {
    String yellow = "\u001B[33m";
    String brightMagenta = "\u001B[95m";
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String magenta = "\u001B[35m";
    String cyan = "\u001B[36m";
    String blue = "\u001B[34m";
    String green = "\u001B[32m";
    private QueueNode front;
    private QueueNode rear;
    private static int idCounter = 1; // Static variable to generate unique IDs

    public Employee() {
        front = null;
        rear = null;
        loadFromFile();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void addEmployee(String post, double salary, String name, String cnic, String phoneNo, String address) {
        int id = idCounter++;
        QueueNode newNode = new QueueNode(id, post, salary, name, cnic, phoneNo, address);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        saveToFile(); // Save the newly added employee to the file
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
    void pressfunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(blue + "\n\t\t\t\t\t\tPress Enter to continue..." + reset);
        scanner.nextLine(); // Wait for the user to press Enter
    }
    void heading(){
        System.out.println(blue + "\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::");
        System.out.println("\t\t\t\t\t\t::          EMPLOYEES DATA          ::");
        System.out.println("\t\t\t\t\t\t::::::::::::::::::::::::::::::::::::::" + reset);
    }

    public void removeEmployee() {
        if (isEmpty()) {
            System.out.println(red + "\n\t\t\t\t\t\tNo employees to remove." + reset);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print(blue + "\n\t\t\t\t\t\tEnter Employee ID to delete: " + reset);
        int searchId = scanner.nextInt();

        QueueNode current = front;
        QueueNode previous = null;

        while (current != null && current.id != searchId) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println(red + "\n\t\t\t\t\t\tEmployee with ID " + searchId + " not found." + reset);
            return;
        }

        if (current == front) {
            front = front.next;
            if (front == null) {
                rear = null;
            }
        } else {
            previous.next = current.next;
            if (current == rear) {
                rear = previous;
            }
        }

        System.out.println(green + "\n\t\t\t\t\t\tEmployee removed successfully." + reset);
        saveToFile(); // Save the updated list to the file
        pressfunction();
    }

    public void employeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            do {
                clearScreen();
                heading();
                System.out.println(yellow + "\n\t\t\t\t\t\t Employee menu");
                System.out.println("\n\t\t\t\t\t\t1. Add Employee");
                System.out.println("\n\t\t\t\t\t\t2. Remove Employee");
                System.out.println("\n\t\t\t\t\t\t3. Display Employee by ID");
                System.out.println("\n\t\t\t\t\t\t4. Go back to main menu" + reset);
                System.out.print(blue + "\n\t\t\t\t\t\tEnter your choice: " + reset);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        clearScreen();
                        heading();
                        addEmployeeFromInput(scanner);
                        break;
                    case 2:
                        clearScreen();
                        heading();
                        removeEmployee();
                        break;
                    case 3:
                        clearScreen();
                        heading();
                        displayEmployeeById(scanner);
                        break;
                    case 4:
                        System.out.println("\n\t\t\t\t\t\tGo back to main menu...");
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
            } while (choice != 4);
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
    


    public boolean employee_login() {
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
    
                    if (!loginId.equals("employee")) {
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

            if (loginId.equals("employee") && loginPassword.equals("employee123")) {
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
    
    private void addEmployeeFromInput(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print(blue + "\n\n\n\t\t\t\t\t\tEnter post of employee: ");
        String post = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t\tEnter salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("\n\t\t\t\t\t\tEnter name: ");
        String name = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t\tEnter CNIC: ");
        String cnic = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t\tEnter phone number: ");
        String phoneNo = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t\tEnter address: " + reset);
        String address = scanner.nextLine();

        addEmployee(post, salary, name, cnic, phoneNo, address);
        System.out.println(green + "\n\t\t\t\t\t\tEmployee added successfully with ID " + (idCounter - 1) + reset);
        pressfunction();
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter("employee_data.txt")) {
            QueueNode current = front;
            while (current != null) {
                writer.println(current.id + " " + current.post + " " + current.salary + " " +
                        current.name + " " + current.cnic + " " + current.phoneNo + " " + current.address);
                current = current.next;
            }
         //   System.out.println(yellow + "\n\t\t\t\t\t\tEmployee data saved successfully." + reset);
        } catch (FileNotFoundException e) {
            System.err.println(red + "Error: Unable to open file for saving employee data. Error message: " + reset + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (Scanner scanner = new Scanner(new File("employee_data.txt"))) {
            while (scanner.hasNextLine()) {
                int id = scanner.nextInt();
                String post = scanner.next();
                double salary = scanner.nextDouble();
                String name = scanner.next();
                String cnic = scanner.next();
                String phoneNo = scanner.next();
                String address = scanner.nextLine().trim();
                QueueNode newNode = new QueueNode(id, post, salary, name, cnic, phoneNo, address);
                if (isEmpty()) {
                    front = rear = newNode;
                } else {
                    rear.next = newNode;
                    rear = newNode;
                }
                idCounter = Math.max(idCounter, id + 1); // Ensure idCounter is set correctly
            }
         //   System.out.println("\n\t\t\t\t\t\tEmployee data loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println(" ");
        } catch (Exception e) {
            System.err.println(red + "Error loading employee data: " + reset + e.getMessage());
        }
    }

    private QueueNode findEmployeeById(int id) {
        QueueNode current = front;
        while (current != null && current.id != id) {
            current = current.next;
        }
        return current;
    }

    public void displayEmployeeById(Scanner scanner) {
        System.out.print(blue + "\n\n\n\t\t\t\t\t\tEnter Employee ID to display: " + reset);
        while (!scanner.hasNextInt()) {
            System.out.print(red + "\n\t\t\t\t\t\tInvalid input. Please enter a numeric ID: " + reset);
            scanner.next(); // Clear the invalid input
        }
        int searchId = scanner.nextInt();
        QueueNode employee = findEmployeeById(searchId);
        if (employee == null) {
            System.out.println(red + "\n\t\t\t\t\t\tEmployee with ID " + searchId + " not found." + reset);
        } else {
            System.out.println(green + "\n\t\t\t\t\t\tEmployee Details:" + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tID: " + employee.id + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tPost: " + employee.post + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tSalary: " + employee.salary + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tName: " + employee.name + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tCNIC: " + employee.cnic + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tPhone No: " + employee.phoneNo + reset);
            System.out.println(yellow + "\n\t\t\t\t\t\tAddress: " + employee.address + reset);
            System.out.println(green + "\n\t\t\t\t\t\t-------------------------" + reset);
        }
        pressfunction();
    }
    
    private class QueueNode {
        int id;
        String post;
        double salary;
        String name;
        String cnic;
        String phoneNo;
        String address;
        QueueNode next;

        public QueueNode(int id, String post, double sal, String n, String c, String p, String addr) {
            this.id = id;
            this.post = post;
            this.salary = sal;
            this.name = n;
            this.cnic = c;
            this.phoneNo = p;
            this.address = addr;
            this.next = null;
        }
    }
}

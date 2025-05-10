package classes_folder;

import java.util.Scanner;

public class intro_page {
    String green = "\u001B[32m";
    String blue = "\u001B[34m";
    String brightBlack = "\u001B[90m";
    String brightRed = "\u001B[91m";
    String brightGreen = "\u001B[92m";
    String brightYellow = "\u001B[93m";
    String brightBlue = "\u001B[94m";
    String brightMagenta = "\u001B[95m";
    String brightCyan = "\u001B[96m";
    String white = "\u001B[97m";
    String brightWhite = "\u001B[97m";

    public void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape sequence to clear screen
        System.out.flush();
    }

    public void intro() {
        System.out.println(blue + "\n\n\n\t\t\t\t\t::::::::::::::::::::::::::::::::::::::::");
        System.out.println(blue + "\t\t\t\t\t::WELCOME TO BANKING MANAGEMENT SYSTEM::");
        System.out.println(blue + "\t\t\t\t\t::::::::::::::::::::::::::::::::::::::::");
        System.out.println(brightMagenta + "\n\t\t\t\t\tInstructor: \t\t Lecturer Waqar Hussain");
        System.out.println("\n\t\t\t\t\tDeveloped By: \t\t Fizza \t Pakeeza");
        System.out.println("\n\t\t\t\t\tRoll No: \t\t 225333  225336");
        System.out.println("\n\t\t\t\t\tInstitute: \t\t GC University Chiniot Campus");
        System.out.println("\n\t\t\t\t\tSubject: \t\t Data Structure & Algorithm (4th Semester)");
        System.out.println("\n\t\t\t\t\tDepartment: \t\t Computer Science");
        System.out.println(blue + "\n\t\t\t\t\tPress enter to go to main menu....");
        Scanner scanner = new Scanner(System.in);
    }
}
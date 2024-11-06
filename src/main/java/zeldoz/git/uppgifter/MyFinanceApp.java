
package zeldoz.git.uppgifter;

import zeldoz.git.uppgifter.InfoManager.MyUserLoginService;
import zeldoz.git.uppgifter.InfoManager.User;
import zeldoz.git.uppgifter.Menu.Menu;
import zeldoz.git.uppgifter.StorageService.SaveMyFile;
import zeldoz.git.uppgifter.TransactionManager.MyManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyFinanceApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the login service and perform login
        MyUserLoginService loginService = new MyUserLoginService(scanner);
        User user = loginService.login();

        // Prompt for and retrieve the user ID
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        // Initialize the file manager and transaction manager with userID
        SaveMyFile fileManager = new SaveMyFile();
        MyManager manager = new MyManager(userID, fileManager);

        // Initialize the menu with manager and scanner
        Menu menu = new Menu(manager, scanner);
        boolean isRunning = true;

        // Main loop for displaying the menu and handling user input
        while (isRunning) {
            menu.displayMenu();
            try {
                int choice = scanner.nextInt();
                isRunning = menu.executeOption(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        scanner.close();
    }
}
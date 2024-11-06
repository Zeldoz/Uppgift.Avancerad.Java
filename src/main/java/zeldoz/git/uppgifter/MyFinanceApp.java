
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


        MyUserLoginService loginService = new MyUserLoginService(scanner);
        User user = loginService.login();


        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();


        SaveMyFile fileManager = new SaveMyFile();
        MyManager manager = new MyManager(userID, fileManager);


        Menu menu = new Menu(manager, scanner);
        boolean isRunning = true;


        while (isRunning) {
            menu.displayMenu();
            try {
                int choice = scanner.nextInt();
                isRunning = menu.executeOption(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option number.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
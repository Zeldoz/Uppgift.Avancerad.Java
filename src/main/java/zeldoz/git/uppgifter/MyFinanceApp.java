package zeldoz.git.uppgifter;

import zeldoz.git.uppgifter.InfoManager.MyUserLoginService;
import zeldoz.git.uppgifter.InfoManager.User;
import zeldoz.git.uppgifter.Menu.Menu;
import zeldoz.git.uppgifter.TransactionManager.Mymanager;

import java.util.Scanner;


public class MyFinanceApp {

    public static  void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MyUserLoginService loginService = new MyUserLoginService(scanner);
        User user = loginService.login();
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        Mymanager manager = new Mymanager(userID);
        Menu menu = new Menu(manager);
        int choice;

        do {
            menu.displayMenu();
            choice = scanner.nextInt();
            menu.executeOption(choice);
        } while (choice != 9);





        scanner.close();
    }
}

        
        
    




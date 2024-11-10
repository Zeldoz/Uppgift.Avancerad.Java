
package zeldoz.git.uppgifter;

import zeldoz.git.uppgifter.InfoManager.MyUserLoginService;
import zeldoz.git.uppgifter.InfoManager.User;
import zeldoz.git.uppgifter.Menu.*;
import zeldoz.git.uppgifter.TransactionService.StorageService.DatabaseService;
import zeldoz.git.uppgifter.TransactionService.StorageService.SetStorageOption;
import zeldoz.git.uppgifter.TransactionService.StorageService.StorageOption;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;
import java.util.Scanner;


public class MyFinanceApp {
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        StorageOption initialStorageMode = StorageOption.TXT_FILE;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("welcome!");

            System.out.println("Enter your username");
            String username = scanner.nextLine();

            System.out.println("Enter your password");
            String password = scanner.nextLine();

            User user = new User(username, password);
            MyUserLoginService loginService = new MyUserLoginService(user);

            if (loginService.authenticator(username, password)) {
                System.out.println("welcome back!" + username + ". " + "You are now logged in!");
                TransactionManager transactionManager = new TransactionManager(databaseService, initialStorageMode);
                Menu menu = new Menu();

                menu.addOption(1, new CurrentBalanceView(transactionManager));
                menu.addOption(2, new ViewTransactions(transactionManager));
                menu.addOption(3, new AddTransactionOption(transactionManager));
                menu.addOption(4, new DeleteTransactionOption(transactionManager));
                menu.addOption(5, new TotalIncomeView(transactionManager));
                menu.addOption(6, new TotalExpensesView(transactionManager));
                menu.addOption(7, new IncomeByTimePeriodView(transactionManager));
                menu.addOption(8, new ExpensesByTimePeriodView(transactionManager));
                menu.addOption(9, new SetStorageOption(transactionManager));
                menu.addOption(10, new LogOutOption(loginService));
                menu.addOption(11, new ExitOption());

                menu.showMenu();

                if (!user.isLoggedIn()) {
                    System.out.println("Returning to login section");
                } else {
                    break;
                }

            } else {
                System.out.println("Login failed! Please try again!");
            }
        }
    }
}
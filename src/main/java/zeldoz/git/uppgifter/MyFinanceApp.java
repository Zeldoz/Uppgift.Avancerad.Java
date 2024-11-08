
package zeldoz.git.uppgifter;

import zeldoz.git.uppgifter.InfoManager.MyUserLoginService;
import zeldoz.git.uppgifter.InfoManager.User;
import zeldoz.git.uppgifter.Menu.*;
import zeldoz.git.uppgifter.TransactionService.StorageService.DatabaseService;
import zeldoz.git.uppgifter.TransactionService.StorageService.SetStorageOption;
import zeldoz.git.uppgifter.TransactionService.StorageService.StorageOption;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;


public class MyFinanceApp {
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        StorageOption initialStorageMode = StorageOption.TXT_FILE;

        TransactionManager transactionManager = new TransactionManager(databaseService, initialStorageMode);
        User user = new User("Zeldoz", "password");
        MyUserLoginService loginService = new MyUserLoginService(user);

        if (loginService.authenticator("Zeldoz", "password")) {
            System.out.println("welcome back!" + " ZeldoZ" +". " + "You are now logged in!");

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
            menu.addOption(10, new ExitOption());

            menu.showMenu();
        } else {
            System.out.println("Login failed! Please try again!");
        }
    }
}
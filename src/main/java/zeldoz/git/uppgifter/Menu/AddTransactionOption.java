package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

import java.util.Scanner;

public class AddTransactionOption implements MenuOption {

    private final TransactionManager transactionManager;

    public AddTransactionOption(TransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    @Override
    public String getDescription() {
        return "Add Transaction";

    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter transaction type [Income//Expense]: ");
        String type = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Transaction transaction = new Transaction(0, type, amount, category, date);
        transactionManager.addTransaction(transaction);

        System.out.println("Transaction added successfully.");
    }
}

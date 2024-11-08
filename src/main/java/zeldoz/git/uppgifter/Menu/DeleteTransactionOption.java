package zeldoz.git.uppgifter.Menu;



import zeldoz.git.uppgifter.TransactionService.TransactionManager;

import java.util.Scanner;


public class DeleteTransactionOption implements MenuOption {
    private final TransactionManager transactionManager;

    public DeleteTransactionOption(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public String getDescription() {
        return "Delete Transaction";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter transaction ID to delete: ");
        int transactionId = scanner.nextInt();

        transactionManager.deleteTransaction(transactionId);
        System.out.println("Transaction deleted successfully.");
    }
}
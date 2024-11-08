package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

public class ViewTransactions extends ViewOption {

    public ViewTransactions(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public String getDescription() {
        return "View All Transactions";
    }

    @Override
    public void execute() {
        System.out.println("Transactions:");
        for (Transaction transaction : transactionManager.getAllTransactions()) {
            System.out.println("ID: " + transaction.getId() +
                    ", Type: " + transaction.getType() +
                    ", Amount: " + transaction.getAmount() +
                    ", Category: " + transaction.getCategory() +
                    ", Date: " + transaction.getDate());
        }
    }
}
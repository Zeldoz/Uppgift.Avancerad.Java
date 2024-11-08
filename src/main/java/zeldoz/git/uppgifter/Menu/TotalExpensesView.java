package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

public class TotalExpensesView extends ViewOption {


    public TotalExpensesView(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public String getDescription() {
        return "View Total Expenses";
    }

    @Override
    public void execute() {
        double totalExpenses = transactionManager.getAllTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("Expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        System.out.println("Total Expenses: $" + totalExpenses);
    }
}
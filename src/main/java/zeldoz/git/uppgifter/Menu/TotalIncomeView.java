package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

public class TotalIncomeView extends ViewOption {

    public TotalIncomeView(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public String getDescription() {
        return "View Total Income";
    }

    @Override
    public void execute() {
        double totalIncome = transactionManager.getAllTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("Income"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        System.out.println("Total Income: $" + totalIncome);
    }
}
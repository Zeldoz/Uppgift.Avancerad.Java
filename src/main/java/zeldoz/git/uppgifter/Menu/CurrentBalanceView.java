package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.TransactionService.TransactionManager;

public class CurrentBalanceView implements MenuOption {
    private final TransactionManager transactionManager;

    public CurrentBalanceView(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public String getDescription() {
        return "View Current Balance";
    }

    @Override
    public void execute() {
        double balance = transactionManager.getAllTransactions().stream()
                .mapToDouble(t -> t.getType().equalsIgnoreCase("Income") ? t.getAmount() : -t.getAmount())
                .sum();

        System.out.println("Current Balance: $" + balance);
    }
}
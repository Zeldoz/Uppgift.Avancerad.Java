package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExpensesByTimePeriodView implements MenuOption {
    private final TransactionManager transactionManager;

    public ExpensesByTimePeriodView(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public String getDescription() {
        return "View Expenses by Time Period";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        List<Transaction> expenseTransactions = transactionManager.getAllTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("Expense") &&
                        t.getDate().compareTo(startDate) >= 0 &&
                        t.getDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());

        System.out.println("Expense transactions from " + startDate + " to " + endDate + ":");
        expenseTransactions.forEach(transaction ->
                System.out.println("ID: " + transaction.getId() +
                        ", Amount: " + transaction.getAmount() +
                        ", Category: " + transaction.getCategory() +
                        ", Date: " + transaction.getDate())
        );
    }
}
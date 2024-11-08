package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncomeByTimePeriodView implements MenuOption {
    private final TransactionManager transactionManager;

    public IncomeByTimePeriodView(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public String getDescription() {
        return "View Income by Time Period";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        List<Transaction> incomeTransactions = transactionManager.getAllTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("Income") &&
                        t.getDate().compareTo(startDate) >= 0 &&
                        t.getDate().compareTo(endDate) <= 0)
                .collect(Collectors.toList());

        System.out.println("Income transactions from " + startDate + " to " + endDate + ":");
        incomeTransactions.forEach(transaction ->
                System.out.println("ID: " + transaction.getId() +
                        ", Amount: " + transaction.getAmount() +
                        ", Category: " + transaction.getCategory() +
                        ", Date: " + transaction.getDate())
        );
    }
}
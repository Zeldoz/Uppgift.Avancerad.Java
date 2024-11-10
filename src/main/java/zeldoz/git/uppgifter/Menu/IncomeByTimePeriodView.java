package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.DateService.DateInput;
import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

import java.time.LocalDate;
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

        LocalDate startDate = DateInput.promptForDate();

        LocalDate endDate = DateInput.promptForDate();

        List<Transaction> incomeTransactions = transactionManager.getAllTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("Income") &&
                        !t.getDate().isBefore(startDate) &&
                        !t.getDate().isAfter(endDate))
                .toList();

        System.out.println("Income transactions from " + startDate + " to " + endDate + ":");
        incomeTransactions.forEach(transaction ->
                System.out.println("ID: " + transaction.getId() +
                        ", Amount: " + transaction.getAmount() +
                        ", Category: " + transaction.getCategory() +
                        ", Date: " + transaction.getDate())
        );
    }
}
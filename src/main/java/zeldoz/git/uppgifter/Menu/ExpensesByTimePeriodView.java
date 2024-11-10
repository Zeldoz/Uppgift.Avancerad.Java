package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.DateService.DateInput;
import zeldoz.git.uppgifter.TransactionService.Transaction;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

import java.time.LocalDate;
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

        LocalDate startDate = DateInput.promptForDate();

        LocalDate endDate = DateInput.promptForDate();

        List<Transaction> expenseTransactions = transactionManager.getAllTransactions().stream()
                .filter(t -> t.getType().equalsIgnoreCase("Expense") &&
                        !t.getDate().isBefore(startDate) &&
                        !t.getDate().isAfter(endDate))
                .toList();

        System.out.println("Expense transactions from " + startDate + " to " + endDate + ":");
        expenseTransactions.forEach(transaction ->
                System.out.println("ID: " + transaction.getId() +
                        ", Amount: " + transaction.getAmount() +
                        ", Category: " + transaction.getCategory() +
                        ", Date: " + transaction.getDate())
        );
    }
}
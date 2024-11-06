package zeldoz.git.uppgifter.TransactionManager;

import zeldoz.git.uppgifter.StorageService.SaveMyFile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class MyManager {

    private String userID;
    private final SaveMyFile fileManager;
    private List<Mytransactions> transactions;

    public MyManager(String userID, SaveMyFile fileManager) {
        this.userID = userID;
        this.fileManager = fileManager;

        // Load transactions from the file for the specified user ID
        this.transactions = fileManager.loadTransactionsFromFile(userID);
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (Mytransactions t : transactions) {
                System.out.println(t);
            }
        }
    }

    public void addTransaction(String type, String category, double amount, LocalDate date) {
        Mytransactions transaction = new Mytransactions(type, category, amount, date);
        transactions.add(transaction);
        
        fileManager.saveTransactionsToFile(userID, transactions.toArray(new Mytransactions[0]));
    
    }

    public void deleteTransactionByString(String type, String category) {
        transactions.removeIf(transaction -> 
            transaction.type.equalsIgnoreCase(type) && transaction.category.equalsIgnoreCase(category));
        
        fileManager.saveTransactionsToFile(userID, transactions.toArray(new Mytransactions[0]));
    
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.type.equalsIgnoreCase("Income"))
                .mapToDouble(t -> t.amount)
                .sum();

    }

    public double getTotalExpenses() {
        return transactions.stream()
                .filter(t -> t.type.equalsIgnoreCase("Expense"))
                .mapToDouble(t -> t.amount)
                .sum();
    }

    public double getCurrentBalance() {
        double balance = 0.0;
        for (Mytransactions t : transactions) {
            if (t.type.equalsIgnoreCase("Income")) {
                balance += t.amount;
            } else if (t.type.equalsIgnoreCase("Expense")) {
                balance -= t.amount;
            }
        }

        return balance;
    }
    public double getTotalIncomeForPeriod(LocalDate startDate, LocalDate endDate) {
        return getTotalForPeriod("Income", startDate, endDate);
    }

    public double getTotalExpensesForPeriod(LocalDate startDate, LocalDate endDate) {
        return getTotalForPeriod("Expense", startDate, endDate);
    }

    private double getTotalForPeriod(String type, LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> t.type.equalsIgnoreCase(type) &&
                             (t.getDate().isEqual(startDate) || t.getDate().isAfter(startDate)) &&
                             (t.getDate().isEqual(endDate) || t.getDate().isBefore(endDate)))
                .mapToDouble(t -> t.amount)
                .sum();
    }

    
    public double getYearlyTotalIncome(int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = startOfYear.with(TemporalAdjusters.lastDayOfYear());
        return getTotalIncomeForPeriod(startOfYear, endOfYear);
    }

    public double getYearlyTotalExpenses(int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = startOfYear.with(TemporalAdjusters.lastDayOfYear());
        return getTotalExpensesForPeriod(startOfYear, endOfYear);
    }

    
    public double getMonthlyTotalIncome(int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        return getTotalIncomeForPeriod(startOfMonth, endOfMonth);
    }

    public double getMonthlyTotalExpenses(int year, int month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        return getTotalExpensesForPeriod(startOfMonth, endOfMonth);
    }

    
    public double getWeeklyTotalIncome(LocalDate dateInWeek) {
        LocalDate startOfWeek = dateInWeek.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = dateInWeek.with(DayOfWeek.SUNDAY);
        return getTotalIncomeForPeriod(startOfWeek, endOfWeek);
    }

    public double getWeeklyTotalExpenses(LocalDate dateInWeek) {
        LocalDate startOfWeek = dateInWeek.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = dateInWeek.with(DayOfWeek.SUNDAY);
        return getTotalExpensesForPeriod(startOfWeek, endOfWeek);
    }

  
    public double getDailyTotalIncome(LocalDate date) {
        return getTotalIncomeForPeriod(date, date);
    }

    public double getDailyTotalExpenses(LocalDate date) {
        return getTotalExpensesForPeriod(date, date);
    }

    public void viewtransactions() {

    }
}

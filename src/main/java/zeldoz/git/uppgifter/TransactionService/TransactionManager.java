package zeldoz.git.uppgifter.TransactionService;

import zeldoz.git.uppgifter.DateService.DateInput;
import zeldoz.git.uppgifter.TransactionService.StorageService.DatabaseService;
import zeldoz.git.uppgifter.TransactionService.StorageService.StorageOption;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionManager {
    private final DatabaseService databaseService;
    private StorageOption storageMode;
    private static final String FILE_PATH = "transactions.txt";


    public void setStorageMode(StorageOption storageMode) {
        this.storageMode = storageMode;
    }

    public StorageOption getStorageMode() {
        return storageMode;
    }

    public TransactionManager(DatabaseService databaseService, StorageOption storageMode) {
        this.databaseService = databaseService;
        this.storageMode = storageMode;
    }

    public void addTransaction(Transaction transaction) {
        if (storageMode == StorageOption.DATABASE) {
            String sql = "INSERT INTO transactions (type, amount, category, date) VALUES (?, ?, ?, ?)";
            databaseService.executeUpdate(sql, transaction.getType(), transaction.getAmount(), transaction.getCategory(), DateInput.formatDate(transaction.getDate()));
        } else if (storageMode == StorageOption.TXT_FILE) {
            saveTransactionToFile(transaction);
        }
    }

    public List<Transaction> getAllTransactions() {
        if (storageMode == StorageOption.DATABASE) {
            return getTransactionsFromDatabase();
        } else {
            return getTransactionsFromFile();
        }
    }

    public void deleteTransaction(int transactionId) {
        if (storageMode == StorageOption.DATABASE) {
            String sql = "DELETE FROM transactions WHERE id = ?";
            databaseService.executeUpdate(sql, transactionId);
        } else {
            deleteTransactionFromFile(transactionId);
        }
    }

    private void saveTransactionToFile(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(transaction.getId() + "," + transaction.getType() + "," + transaction.getAmount() + "," +
                    transaction.getCategory() + "," + DateInput.formatDate(transaction.getDate()));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving transaction to file: " + e.getMessage());
        }
    }

    private List<Transaction> getTransactionsFromDatabase() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (ResultSet rs = databaseService.executeQuery(sql)) {
            while (rs != null && rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        DateInput.parseDate(rs.getString("date"))
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving transactions: " + e.getMessage());
        }
        return transactions;
    }

    private List<Transaction> getTransactionsFromFile() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Transaction transaction = new Transaction(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2]),
                        parts[3],
                        DateInput.parseDate(parts[4])
                );
                transactions.add(transaction);
            }
        } catch (IOException e) {
            System.err.println("Error reading transactions from file: " + e.getMessage());
        }
        return transactions;
    }

    private void deleteTransactionFromFile(int transactionId) {
        List<Transaction> transactions = getTransactionsFromFile();
        transactions.removeIf(transaction -> transaction.getId() == transactionId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.getId() + "," + transaction.getType() + "," + transaction.getAmount() + "," +
                        transaction.getCategory() + "," + transaction.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error deleting transaction from file: " + e.getMessage());
        }
    }
}

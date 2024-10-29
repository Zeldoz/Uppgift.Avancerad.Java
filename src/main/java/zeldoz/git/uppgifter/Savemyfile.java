package zeldoz.git.uppgifter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Savemyfile {

    public void saveTransactionsToFile(String userID, Mytransactions[] transactions) {
        String filename = "transactions_" + userID + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            for (Mytransactions t : transactions) {
                writer.write(t.type + "," + t.category + "," +  + t.amount + "," + t.date + "\n");
            }
            System.out.println("Transactions for user " + userID + " saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public List<Mytransactions> loadTransactionsFromFile(String userID) {
        String filename = "transactions_" + userID + ".txt";
        List<Mytransactions> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                LocalDate date = LocalDate.parse(parts[3]);
                transactions.add(new Mytransactions(type, category, amount, date));
            }
            System.out.println("Transactions for user " + userID + " loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return transactions;
    }

    public static void loadUserTransactions(String userID) {
        String filename = "transactions_" + userID + ".txt";
        File file = new File(filename);
        
        
        if (!file.exists()) {
            System.out.println("No existing transactions for User " + userID + ". Creating new file.");
            try {
                
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating new file for user: " + e.getMessage());
            }
        } else {
            System.out.println("File for User " + userID + " exists.");
        }
    }
}




    
    


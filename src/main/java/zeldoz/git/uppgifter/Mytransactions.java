package zeldoz.git.uppgifter;

import java.time.LocalDate;

public class Mytransactions {
    String type;
    String category;
    double amount;
    LocalDate date;

    public Mytransactions(String type, String category, double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
    
    
    public String toString() {
        String result;
        if (type.equalsIgnoreCase("expense")) {
            result = "Expense of -$" + amount + " in " + category + " on " + date; 
        } else if (type.equalsIgnoreCase("income")) {
            result = "Income of +$" + amount + " in " + category;
        } else {
            result = "Unknown transaction type: " + type;
        }
        return result;
    
        
    }

    public static void add(Mytransactions mytransactions) {

        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    
    

}

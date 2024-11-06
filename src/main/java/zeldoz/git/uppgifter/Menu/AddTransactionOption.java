package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.DateService.DateInput;
import zeldoz.git.uppgifter.TransactionManager.MyManager;

import java.time.LocalDate;
import java.util.Scanner;

public class AddTransactionOption implements MenuOption {

    private final MyManager manager;

    public AddTransactionOption(MyManager manager) {
        this.manager = manager;
    }

    @Override
    public String getDescription() {
        return "Add Transaction";
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter type (Income/Expense): ");
        String type = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        LocalDate date = DateInput.promptForDate();

        manager.addTransaction(type, category, amount, date);
        System.out.println("Transaction added successfully.");
    }
}

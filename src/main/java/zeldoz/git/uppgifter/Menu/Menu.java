
package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.DateService.DateInput;
import zeldoz.git.uppgifter.TransactionManager.MyManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


    public class Menu {
        private final MyManager manager;
        private final Scanner scanner;
        private final Map<Integer, MenuOption> options = new HashMap<>();

        public Menu(MyManager manager, Scanner scanner) {
            this.manager = manager;
            this.scanner = scanner;
            initializeOptions();
        }

        private void initializeOptions() {
            options.put(1, new ViewTransactions(manager));
            options.put(2, new AddTransactionOption(manager, scanner));
            options.put(3, new DeleteTransactionOption(manager, scanner));
            options.put(4, new TotalIncomeView(manager));
            options.put(5, new TotalExpensesView(manager));
            options.put(6, new CurrentBalanceView(manager));
            options.put(7, new IncomeByTimePeriodView(manager));
            options.put(8, new ExpensesByTimePeriodView(manager));
            options.put(9, new ExitOption());
        }

        public void displayMenu() {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("Choose an option:");
            options.forEach((key, option) -> System.out.println(key + ". " + option.getDescription()));
        }

        public boolean executeOption(int choice) {
            MenuOption option = options.get(choice);
            if (option != null) {
                option.execute();
                return !(option instanceof ExitOption);
            } else {
                System.out.println("Invalid choice. Please try again.");
                return true;
        }
    }

    static class AddTransactionOption implements MenuOption {
        private final MyManager manager;
        private final Scanner scanner;

        public AddTransactionOption(MyManager manager, Scanner scanner) {
            this.manager = manager;
            this.scanner = scanner;
        }

        @Override
        public String getDescription() {
            return "Add Transaction";
        }

        @Override
        public void execute() {
            try {
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
            } catch (Exception e) {
                System.out.println("Error adding transaction. Please try again.");
                scanner.nextLine();
            }
        }
    }

    static class DeleteTransactionOption implements MenuOption {
        private final MyManager manager;
        private final Scanner scanner;

        public DeleteTransactionOption(MyManager manager, Scanner scanner) {
            this.manager = manager;
            this.scanner = scanner;
        }

        public String getDescription() {
            return "Delete Transaction";
        }

        public void execute() {
            System.out.print("Enter type to delete: ");
            String type = scanner.nextLine();
            System.out.print("Enter category to delete: ");
            String category = scanner.nextLine();
            manager.deleteTransactionByString(type, category);
            System.out.println("Transaction deleted successfully.");
        }
    }

    static class ExitOption implements MenuOption {
        public String getDescription() {
            return "Exit";
        }

        public void execute() {
            System.out.println("Exiting the application...");
        }
    }


}

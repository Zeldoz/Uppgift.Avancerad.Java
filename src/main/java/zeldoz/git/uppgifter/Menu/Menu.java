package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.DateService.DateInput;
import zeldoz.git.uppgifter.TransactionManager.Mymanager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<Integer, MenuOption> options = new HashMap<>();
    private final Mymanager manager;

    public Menu(Mymanager manager) {
        this.manager = manager;

        options.put(1, new ViewTransactions(manager));
        options.put(2, new AddTransactionOption(manager));
        options.put(3, new DeleteTransactionOption(manager));
        options.put(4, new TotalIncomeView(manager));
        options.put(5, new TotalExpensesView(manager));
        options.put(6, new CurrentBalanceView(manager));
        options.put(7, new IncomeByTimePeriodView(manager));
        options.put(8, new ExpensesByTimePeriodView(manager));
        options.put(9, new ExitOption());
    }

    public void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");
        options.forEach((key, option) -> System.out.println(key + ". " + option.getDescription()));
        System.out.print("Choose an option: ");
    }

    public void executeOption(int choice) {
        MenuOption option = options.get(choice);
        if (option != null) {
            option.execute();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }


    static class AddTransactionOption implements MenuOption {
        private final Mymanager manager;

        public AddTransactionOption(Mymanager manager) {
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
            scanner.nextLine(); // Consume the newline

            LocalDate date = DateInput.promptForDate();

            manager.addTransaction(type, category, amount, date);
            System.out.println("Transaction added successfully.");
        }
    }


    static class CurrentBalanceView extends ViewOption {
        public CurrentBalanceView(Mymanager manager) {
            super(manager);
        }

        public String getDescription() {
            return "View Current Balance";
        }

        protected void view() {
            System.out.println("Current Balance: " + manager.getCurrentBalance());
        }
    }


    static class DeleteTransactionOption implements MenuOption {
        private final Mymanager manager;

        public DeleteTransactionOption(Mymanager manager) {
            this.manager = manager;
        }

        public String getDescription() {
            return "Delete Transaction";
        }

        public void execute() {
            Scanner scanner = new Scanner(System.in);
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
            System.exit(0);
        }
    }


    static class ExpensesByTimePeriodView extends ViewOption {
        public ExpensesByTimePeriodView(Mymanager manager) {
            super(manager);
        }

        public String getDescription() {
            return "View Expenses by Time Period";
        }

        protected void view() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter end date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Total Expenses in Period: " + manager.getTotalExpensesForPeriod(startDate, endDate));
        }
    }


    static class IncomeByTimePeriodView extends ViewOption {
        public IncomeByTimePeriodView(Mymanager manager) {
            super(manager);
        }

        public String getDescription() {
            return "View Income by Time Period";
        }

        protected void view() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter end date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.println("Total Income in Period: " + manager.getTotalIncomeForPeriod(startDate, endDate));
        }
    }


    static class TotalExpensesView extends ViewOption {
        public TotalExpensesView(Mymanager manager) {
            super(manager);
        }

        public String getDescription() {
            return "View Total Expenses";
        }

        protected void view() {
            System.out.println("Total Expenses: " + manager.getTotalExpenses());
        }
    }


    static class TotalIncomeView extends ViewOption {
        public TotalIncomeView(Mymanager manager) {
            super(manager);
        }

        public String getDescription() {
            return "View Total Income";
        }

        protected void view() {
            System.out.println("Total Income: " + manager.getTotalIncome());
        }
    }


    static class ViewTransactions extends ViewOption {
        public ViewTransactions(Mymanager manager) {
            super(manager);
        }

        @Override
        public String getDescription() {
            return "View Transactions";
        }

        @Override
        protected void view() {
            manager.viewTransactions();
        }
    }


    abstract static class ViewOption implements MenuOption {
        protected final Mymanager manager;

        public ViewOption(Mymanager manager) {
            this.manager = manager;
        }

        abstract void view();

        @Override
        public void execute() {
            view();
        }
    }
}





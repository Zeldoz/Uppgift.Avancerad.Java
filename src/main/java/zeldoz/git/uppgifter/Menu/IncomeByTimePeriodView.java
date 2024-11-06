package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.TransactionManager.MyManager;

import java.time.LocalDate;
import java.util.Scanner;

public class IncomeByTimePeriodView extends ViewOption implements MenuOption {
    public IncomeByTimePeriodView(MyManager manager) {
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

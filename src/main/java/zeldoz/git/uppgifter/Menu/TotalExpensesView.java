package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionManager.MyManager;

public class TotalExpensesView extends ViewOption implements MenuOption {
    public TotalExpensesView(MyManager manager) {

    }

    public String getDescription() {
        return "View Total Expenses";
    }

    protected void view() {
        System.out.println("Total Expenses: " + manager.getTotalExpenses());
    }
}

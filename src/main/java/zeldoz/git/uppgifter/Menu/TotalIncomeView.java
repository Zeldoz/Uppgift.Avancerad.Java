package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionManager.MyManager;

public class TotalIncomeView extends ViewOption implements MenuOption {
    public TotalIncomeView(MyManager manager) {
        super(manager);
    }

    public String getDescription() {
        return "View Total Income";
    }

    protected void view() {
        System.out.println("Total Income: " + manager.getTotalIncome());
    }
}

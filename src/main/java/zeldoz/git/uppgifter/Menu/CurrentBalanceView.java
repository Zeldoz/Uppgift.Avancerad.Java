package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.TransactionManager.MyManager;

public class CurrentBalanceView extends ViewOption implements MenuOption {
    public CurrentBalanceView(MyManager manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return "View Current Balance";
    }

    protected void view() {
        System.out.println("Current Balance: " + manager.getCurrentBalance());
    }
}

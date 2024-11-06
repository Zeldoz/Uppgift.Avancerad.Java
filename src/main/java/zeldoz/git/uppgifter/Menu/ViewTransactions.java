package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.TransactionManager.MyManager;

public class ViewTransactions extends ViewOption implements MenuOption {
    public ViewTransactions(MyManager manager){
        manager.viewtransactions();
    }

    @Override
    public String getDescription() {
        return "View Transactions";
    }

    @Override
    protected void view() {

    }
}

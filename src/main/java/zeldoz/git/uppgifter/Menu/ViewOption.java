package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionService.TransactionManager;

public abstract class ViewOption implements MenuOption {
    protected final TransactionManager transactionManager;

    public ViewOption(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public abstract String getDescription();

    @Override
    public abstract void execute();
}
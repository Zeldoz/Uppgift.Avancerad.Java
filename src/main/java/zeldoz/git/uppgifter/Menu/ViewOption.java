package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.TransactionManager.MyManager;

abstract class ViewOption implements MenuOption {
    protected MyManager manager;

    public ViewOption(MyManager manager) {
        this.manager = manager;
    }

    ViewOption() {
    }

    abstract void view();

    @Override
    public void execute() {
        view();
    }
}

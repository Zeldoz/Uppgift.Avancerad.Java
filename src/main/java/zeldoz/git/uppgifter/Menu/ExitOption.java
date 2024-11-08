package zeldoz.git.uppgifter.Menu;

public class ExitOption implements MenuOption {
    @Override
    public String getDescription() {
        return "Exit";
    }

    @Override
    public void execute() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }
}
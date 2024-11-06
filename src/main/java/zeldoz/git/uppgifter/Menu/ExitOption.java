package zeldoz.git.uppgifter.Menu;

public class ExitOption implements MenuOption {
    public String getDescription() {
        return "Exit";
    }

    public void execute() {
        System.out.println("Exiting the application...");
        System.exit(0);
    }
}

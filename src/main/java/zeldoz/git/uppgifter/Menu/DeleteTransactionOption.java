package zeldoz.git.uppgifter.Menu;


import zeldoz.git.uppgifter.StorageService.SaveMyFile;
import zeldoz.git.uppgifter.TransactionManager.MyManager;

import java.util.Scanner;

public class DeleteTransactionOption implements MenuOption {
    private final MyManager manager;

    public DeleteTransactionOption(String userID, SaveMyFile fileManager) {
        this.manager = new MyManager(userID, fileManager);
    }

    @Override
    public String getDescription() {
        return "Delete Transaction";
    }

    @Override
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
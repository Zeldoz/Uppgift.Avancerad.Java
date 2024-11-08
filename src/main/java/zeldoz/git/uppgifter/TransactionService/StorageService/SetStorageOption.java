package zeldoz.git.uppgifter.TransactionService.StorageService;


import zeldoz.git.uppgifter.Menu.MenuOption;
import zeldoz.git.uppgifter.TransactionService.TransactionManager;

public class SetStorageOption implements MenuOption {
        private final TransactionManager transactionManager;

        public SetStorageOption(TransactionManager transactionManager) {
            this.transactionManager = transactionManager;
        }

        @Override
        public String getDescription() {
            return "Toggle Storage Mode (Current mode: " + transactionManager.getStorageMode() + ")";
        }

        @Override
        public void execute() {
            StorageOption currentMode = transactionManager.getStorageMode();
            StorageOption newMode = (currentMode == StorageOption.DATABASE) ? StorageOption.TXT_FILE : StorageOption.DATABASE;

            transactionManager.setStorageMode(newMode);
            System.out.println("Storage mode changed to: " + newMode);
        }
    }


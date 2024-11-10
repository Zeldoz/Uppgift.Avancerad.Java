
package zeldoz.git.uppgifter.Menu;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


    public class Menu {
        private Map<Integer, MenuOption> options = new HashMap<>();

        public void addOption(int number, MenuOption option) {
            options.put(number, option);

        }

        public void display() {
            System.out.println("Please select an option");
            for (Map.Entry<Integer, MenuOption> entry : options.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getDescription());
            }
        }

        public void executeOption(int choice) {
            MenuOption option = options.get(choice);
            if (option != null) {
                option.execute();

            } else {
                System.out.println("Invalid Option, try again please!");
            }
        }

        public void showMenu() {
            Scanner scanner = new Scanner(System.in);
            boolean isRunning = true;

            while (isRunning) {
                display();
                int choice = scanner.nextInt();
                executeOption(choice);
                if (choice == 10) {
                    System.out.println("Logging out...");
                    isRunning = false;
                } else if (choice == 0) {
                    System.out.println("Exiting application...");
                    isRunning = false;

                }
            }
        }
    }
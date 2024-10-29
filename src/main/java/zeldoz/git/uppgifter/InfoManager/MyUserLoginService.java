package zeldoz.git.uppgifter.InfoManager;

import java.util.Scanner;

public class MyUserLoginService extends User {
        private final Scanner scanner;

        public MyUserLoginService(Scanner scanner) {
            this.scanner = scanner;
        }


        public User login() {
            int userID = 0;
            boolean validInput = false;

            System.out.println("Welcome! Please insert your User ID [4-digits]:");

            while (!validInput) {
                if (scanner.hasNextInt()) {
                    userID = scanner.nextInt();
                    if (User.isValidUserID(userID)) {
                        validInput = true;
                        System.out.println("Welcome back, User " + userID + " you are now Logged in!");
                    } else {
                        System.out.println("Please enter a valid [4-digit: ****] user ID.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid [4-digit: ****] user ID.");
                    scanner.next();
                }
            }

            return new User(userID);
        }
    }





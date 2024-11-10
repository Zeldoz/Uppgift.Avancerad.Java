package zeldoz.git.uppgifter.DateService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

    public class DateInput {
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static LocalDate promptForDate() {
            Scanner scanner = new Scanner(System.in);
            LocalDate date = null;

            while (date == null) {
                System.out.print("Enter date (YYYY-MM-DD): ");
                String dateStr = scanner.nextLine();

                try {
                    date = LocalDate.parse(dateStr, DATE_FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter date in the format YYYY-MM-DD.");
                }
            }

            return date;
        }

        public static LocalDate parseDate (String dateStr) {
            try {
                return LocalDate.parse(dateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in the format YYYY-MM-DD.");
                return null;
            }
        }

        public static String formatDate (LocalDate date){
            return date.format(DATE_FORMATTER);
        }
    }


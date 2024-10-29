package zeldoz.git.uppgifter;

public class User {

    private final int userID;


        public User(int userID) {
            this.userID = userID;
        }

        public int getUserID() {
            return userID;
        }


        public static boolean isValidUserID(int userID) {
            return userID >= 1000 && userID <= 9999;
        }
    }


package zeldoz.git.uppgifter.InfoManager;

public class User {

    private int userID;


        public User(int userID) {
            this.userID = userID;
        }


        public User() {
    }

    public int getUserID() {
            return userID;
        }


        public static boolean isValidUserID(int userID) {
            return userID >= 1000 && userID <= 9999;
        }
    }


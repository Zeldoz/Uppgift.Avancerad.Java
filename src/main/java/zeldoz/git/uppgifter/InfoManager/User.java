package zeldoz.git.uppgifter.InfoManager;

public class User {
    private String username;
    private String password;
    private boolean isLoggedIn;

    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public boolean login(String inputUsername, String inputPassword){
        if(this.username.equals(inputUsername) && this.password.equals(inputPassword)){
            this.isLoggedIn = true;
        }
        return this.isLoggedIn;
    }

    public void logout(){
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

}




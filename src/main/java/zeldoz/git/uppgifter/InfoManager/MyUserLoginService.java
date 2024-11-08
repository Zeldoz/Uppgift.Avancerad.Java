package zeldoz.git.uppgifter.InfoManager;

import java.util.Scanner;

public class MyUserLoginService{
    private final User user;

    public MyUserLoginService(User user){
        this.user = user;
    }

    public boolean authenticator(String username, String password){
        return user.login(username, password);
    }

    public void logout(){
        user.logout();
    }
}





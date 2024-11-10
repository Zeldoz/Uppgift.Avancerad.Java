package zeldoz.git.uppgifter.InfoManager;



public class MyUserLoginService{
    private static User user;

    public MyUserLoginService(User user){
        this.user = user;
    }

    public boolean authenticator(String username, String password){
        return user.login(username, password);
    }

    public static void logout(){
        user.logout();
    }
}





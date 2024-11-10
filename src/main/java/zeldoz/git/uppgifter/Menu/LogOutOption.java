package zeldoz.git.uppgifter.Menu;

import zeldoz.git.uppgifter.InfoManager.MyUserLoginService;

public class LogOutOption implements MenuOption{

    private final MyUserLoginService loginService;

public LogOutOption(MyUserLoginService loginService){
    this.loginService = loginService;
}
    @Override
    public String getDescription() {
        return "Log Out";
    }

    @Override
    public void execute() {
        loginService.logout();


    }
}

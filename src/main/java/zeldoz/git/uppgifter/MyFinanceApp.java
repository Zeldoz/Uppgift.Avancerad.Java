package zeldoz.git.uppgifter;

import java.util.Scanner;


public class MyFinanceApp {

    public static  void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MyUserLoginService loginService = new MyUserLoginService(scanner);

        User user = loginService.login();
        System.out.println("Logged in with User ID: " + user.getUserID());




        scanner.close();
    }
}

        
        
    




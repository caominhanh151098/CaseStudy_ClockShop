package services;


import model.Account;
import view.client.MainMenuClient;
import view.user.MainMenuUser;

import java.util.ArrayList;

public class LoginService {
    public static ArrayList<Account> accountList = new ArrayList<>();
    public static UserService userService = new UserService();

    public static boolean login(String username, String password) {
        accountList = userService.getUserList();
        for (Account account : accountList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                if (account.getRote() == 0 || account.getRote() == 1) {
                    ClearScreen.clearScreen();
                    System.out.printf("Welcome back [%s]!%n%n", account.getName());
                    MainMenuUser.mainMenuAdmin(account);
                } else {
                    ClearScreen.clearScreen();
                    MainMenuClient.mainMenuClient(account);
                }
                return true;
            }
        }
        return false;
    }
}

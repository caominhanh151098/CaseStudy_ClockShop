package services;


import model.Account;
import view.client.MainMenuClient;
import view.user.MainMenuUser;

import java.util.ArrayList;

public class LoginService {
    private static UserService userService = new UserService();

    public static boolean login(String username, String password) {
        ArrayList<Account> accountList = userService.getUserList();
        for (Account account : accountList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                ClearScreen.clearScreen();
                System.out.printf("                                                                    Welcome back [%s]!%n%n", account.getName());
                if (account.getRote() == 0 || account.getRote() == 1) {
                    MainMenuUser.mainMenuAdmin(account);
                } else {
                    MainMenuClient.mainMenuClient(account);
                }
                return true;
            }
        }
        return false;
    }
}

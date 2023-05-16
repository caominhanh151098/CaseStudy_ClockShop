package view;

import input.InputData;
import model.Account;
import services.LoginService;
import services.UserService;
import view.user.ClearScreen;

import java.util.Date;
import java.util.Scanner;

public class Login {
    public static UserService userService = new UserService();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        ClearScreen.clearScreen();
        do {
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.printf("⚃%10s%-30s⚃%n", "", "--WEB BÁN ĐỒNG HỒ--");
            System.out.printf("⚃%10s%-30s⚃%n", "", "Chọn trong các mục");
            System.out.printf("⚃%10s%-30s⚃%n", "", "Nhấn 1: Đăng nhập");
            System.out.printf("⚃%10s%-30s⚃%n", "", "Nhấn 2: Đăng ký");
            System.out.printf("⚃%10s%-30s⚃%n", "", "Nhấn 0: Thoát");
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.print("Enter number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    System.exit(0);
                default:
                    System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (true);
    }

    public static void login() {
        String username;
        String password;
        do {
            username = InputData.getUsername();
            password = InputData.getPassword();
            if (LoginService.login(username, password) == false)
                System.out.println("Wrong Username or Password!");
            else break;
        } while (true);
    }

    public static void register() {
        String username = InputData.getUsername();
        String password = InputData.getPassword();
        String name = InputData.getName();
        Date dob = InputData.getDate();
        String address = InputData.getAddress();
        String email = InputData.getEmail();
        String phoneNum = InputData.getPhoneNum();
        Account accountInfo = new Account()
                .setUsername(username)
                .setPassword(password)
                .setName(name)
                .setDob(dob)
                .setAddress(address)
                .setEmail(email)
                .setNumPhone(phoneNum);

        System.out.printf("Bạn muốn tạo mới tài khoản này? %s (y/n): ",
                accountInfo.info());
        if (InputData.choice()) {
            userService.addUser(accountInfo);
        }
    }
}

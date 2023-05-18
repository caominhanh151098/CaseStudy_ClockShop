package view;

import input.Continue;
import input.InputData;
import model.Account;
import services.LoginService;
import services.UserService;
import services.ClearScreen;

import java.util.Date;
import java.util.Scanner;

public class Login {
    public static UserService userService = new UserService();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            ClearScreen.clearScreen();
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
                    ClearScreen.clearScreen(3);
                    login();
                    break;
                case 2:
                    ClearScreen.clearScreen(3);
                    register();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    System.out.println("Exit!");
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
            username = InputData.loginUsername();
            password = InputData.getPassword();
            if (!LoginService.login(username, password)) {
                ClearScreen.clearScreen(5);
                System.out.println("Wrong Username or Password!");
            } else break;
        } while (true);
    }

    public static void register() {
        String username;
        String password;
        String name;
        Date dob;
        String address;
        String email;
        String phoneNum;
        System.out.println("Nhập 0 nếu muốn thoát tác vụ!");
        do {
            username = InputData.getUsername();
            if (Continue.continueInput(username))
                return;
        } while (username.equals("0"));
        do {
            password = InputData.getPassword();
            if (Continue.continueInput(password))
                return;
        } while (password.equals("0"));
        do {
            name = InputData.getName();
            if (Continue.continueInput(name))
                return;
        } while (name.equals("0"));
        do {
            dob = InputData.getDate();
            if (Continue.continueInput(dob))
                return;
        } while (dob.equals(new Date(0)));
        do {
            address = InputData.getAddress();
            if (Continue.continueInput(address))
                return;
        } while (address.equals("0"));
        do {
            email = InputData.getEmail();
            if (Continue.continueInput(email))
                return;
        } while (email.equals("0"));
        do {
            phoneNum = InputData.getPhoneNum();
            if (Continue.continueInput(phoneNum))
                return;
        } while (phoneNum.equals("0"));
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

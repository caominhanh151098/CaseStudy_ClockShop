package view.client;

import input.EditData;
import input.InputData;
import model.Account;
import services.UserService;
import services.ClearScreen;

import java.util.Date;
import java.util.Scanner;

public class AcountView {
    public static Scanner scanner = new Scanner(System.in);
    public static UserService userService = new UserService();
    public static Account myUser;

    public static void menuAccount(Account myUser) {
        int choice;
        ClearScreen.clearScreen();
        do {
            myUser.showProfile();
            ClearScreen.clearScreen(3);
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 1: Chỉnh sửa thông tin cá nhân");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 0: Quay lại");
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.print("Enter number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    editUser();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (choice != 0);
    }

    public static void editUser() {
        String password = EditData.getPassword(myUser.getPassword());
        String name = EditData.getName(myUser.getName());
        Date dob = EditData.getDate(myUser.getDob());
        String address = EditData.getAddress(myUser.getAddress());
        String email = EditData.getEmail(myUser.getEmail());
        String phoneNum = EditData.getPhoneNum(myUser.getNumPhone());

        Account accountInfo = new Account()
                .setId(myUser.getId())
                .setUsername(myUser.getUsername())
                .setPassword(password)
                .setName(name)
                .setRote(myUser.getRote())
                .setDob(dob)
                .setAddress(address)
                .setEmail(email)
                .setNumPhone(phoneNum);
        System.out.printf("Bạn muốn thay đổi thông tin Username: %s?%nPassword: %s|Name: %s|Role: %s | Date or birth: %s, " +
                        "Address: %s, Email: %s, Phone Number: %s(y/n)",
                myUser.getUsername(), password, name, myUser.getRote(), dob, address, email, phoneNum);
        if (InputData.choice()) {
            userService.editUser(accountInfo);
        }
    }
}

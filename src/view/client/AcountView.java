package view.client;

import input.Continue;
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

    public static void menuAccount(Account myUser) {
        int choice;
        do {
            myUser = userService.findUserByID(myUser.getId());
            myUser.showProfile();
            ClearScreen.clearScreen(3);
            System.out.printf("                                                        ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.printf("                                                        ⚃\t\t%-40s⚃%n", "Chọn trong các mục");
            System.out.printf("                                                        ⚃\t\t%-40s⚃%n", "Nhấn 1: Chỉnh sửa thông tin cá nhân");
            System.out.printf("                                                        ⚃\t\t%-40s⚃%n", "Nhấn 0: Quay lại");
            System.out.printf("                                                        ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.print("Enter number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    ClearScreen.clearScreen(5);
                    editUser(myUser);
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    break;
                default:
                    ClearScreen.clearScreen();
                    System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (choice != 0);
    }

    public static void editUser(Account account) {
        String password;
        String name;
        Date dob;
        String address;
        String email;
        String phoneNum;

        System.out.println("(Nhập \"0\" để hủy bỏ tác vụ)");
        do {
            password = EditData.getPassword(account.getPassword());
            if (Continue.continueInput(password))
                return;
        } while (password.equals("0"));
        do {
            name = EditData.getName(account.getName());
            if (Continue.continueInput(name))
                return;
        } while (name.equals("0"));
        do {
            dob = EditData.getDate(account.getDob());
            if (Continue.continueInput(dob))
                return;
        } while (dob.equals(new Date(0)));
        do {
            address = EditData.getAddress(account.getAddress());
            if (Continue.continueInput(address))
                return;
        } while (address.equals("0"));
        do {
            email = EditData.getEmail(account.getEmail());
            if (Continue.continueInput(email))
                return;
        } while (email.equals("0"));
        do {
            phoneNum = EditData.getPhoneNum(account.getNumPhone());
            if (Continue.continueInput(phoneNum))
                return;
        } while (phoneNum.equals("0"));


        Account accountInfo = new Account()
                .setId(account.getId())
                .setUsername(account.getUsername())
                .setPassword(password)
                .setName(name)
                .setRote(account.getRote())
                .setDob(dob)
                .setAddress(address)
                .setEmail(email)
                .setNumPhone(phoneNum);
        System.out.printf("Bạn muốn thay đổi thông tin Username: %s?%n" +
                        "Password: %s | Name: %s | Date or birth: %s,%n" +
                        "Address: %s | Email: %s | Phone Number: %s (y/n):",
                account.getUsername(), password, name, accountInfo.getDob(), address, email, phoneNum);
        if (InputData.choice()) {
            userService.editUser(accountInfo);
            System.out.println("Sửa thông tin cá nhân thành công!");
        }
    }
}

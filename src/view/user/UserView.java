package view.user;

import input.EditData;
import input.InputData;
import model.Account;
import services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserView {
    static UserService userService = new UserService();
    static ArrayList<Account> accountList;
    static Scanner scanner = new Scanner(System.in);

    public static void user(Account account) {
        if (account.getRote() == 0) {
            adminView();
        } else staffView(account);
    }

    public static void adminView() {
        int choice;
        do {
            userService.getUserList();
            ClearScreen.clearScreen();
            showUser();
            System.out.printf("%n⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.printf("⚃\t\t%-40s⚃%n", "--Menu quản lý Tài khoản--");
            System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 1: Thêm Tài khoản");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 2: Sửa Tài khoản");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 3: Xóa Tài khoản");
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
                    ClearScreen.clearScreen(5);
                    addUser();
                    break;
                case 2:
                    ClearScreen.clearScreen(5);
                    editUser();
                    break;
                case 3:
                    ClearScreen.clearScreen(3);
                    deleteUser();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    break;
                default:
                    System.out.println("Lỗi! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (choice != 0);
    }

    public static void staffView(Account account) {
        int choice;
        ClearScreen.clearScreen();
        do {
            account.showProfile();
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
                    editInfo(account);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (choice != 0);
    }

    public static void editInfo(Account account) {
        String password = EditData.getPassword(account.getPassword());
        String name = EditData.getName(account.getName());
        Date dob = EditData.getDate(account.getDob());
        String address = EditData.getAddress(account.getAddress());
        String email = EditData.getEmail(account.getEmail());
        String phoneNum = EditData.getPhoneNum(account.getNumPhone());

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
        System.out.printf("Bạn muốn thay đổi thông tin Username: %s?%nPassword: %s|Name: %s|Role: %s | Date or birth: %s, " +
                        "Address: %s, Email: %s, Phone Number: %s(y/n)",
                account.getUsername(), password, name, account.getRote(), dob, address, email, phoneNum);
        if (InputData.choice()) {
            userService.editUser(accountInfo);
        }
    }

    public static void showUser() {
        accountList = userService.getUserList();
        System.out.println("╔═══════╦═════════════════════╦══════════════════════╦══════╦═════════════════╦════════════════════════════════╦═══════════════════════════╦══════════════╗");
        System.out.printf("║%-6s ║ %-19s ║ %-20s ║ %4s ║ %-15s ║ %-30s ║ %-25s ║ %-12s ║%n", "  STT", "     Username", "        Name", "Role", "  Day or Birth", "           Address", "         Email", "Phone Number");
        System.out.println("╠═══════╬═════════════════════╬══════════════════════╬══════╬═════════════════╬════════════════════════════════╬═══════════════════════════╬══════════════╣");
        int i = 1;
        for (Account account : accountList) {
            System.out.printf("║\t%-4s║%s║%n", i, account.display());
            i++;
        }
        System.out.println("╚═══════╩═════════════════════╩══════════════════════╩══════╩═════════════════╩════════════════════════════════╩═══════════════════════════╩══════════════╝");
    }

    public static void addUser() {
        String username = InputData.getUsername();
        String password = InputData.getPassword();
        String name = InputData.getName();
        int role = InputData.getRole();
        Date dob = InputData.getDate();
        String address = InputData.getAddress();
        String email = InputData.getEmail();
        String phoneNum = InputData.getPhoneNum();
        Account accountInfo = new Account()
                .setUsername(username)
                .setPassword(password)
                .setName(name)
                .setRote(role)
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

    public static void editUser() {
        accountList = userService.getUserList();
        System.out.print("Nhập STT Tài khoản cần sửa: ");
        int index = InputData.getIndex(accountList);
        Account myAccount = accountList.get(index);
        String password = EditData.getPassword(myAccount.getPassword());
        String name = EditData.getName(myAccount.getName());
        int role = EditData.getRole(myAccount.getRote());
        Date dob = EditData.getDate(myAccount.getDob());
        String address = EditData.getAddress(myAccount.getAddress());
        String email = EditData.getEmail(myAccount.getEmail());
        String phoneNum = EditData.getPhoneNum(myAccount.getNumPhone());

        String username = myAccount.getUsername();
        Long id = myAccount.getId();
        Account accountInfo = new Account()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setName(name)
                .setRote(role)
                .setDob(dob)
                .setAddress(address)
                .setEmail(email)
                .setNumPhone(phoneNum);
        System.out.printf("Bạn muốn thay đổi thông tin Username: %s?%nPassword: %s|Name: %s|Role: %s | Date or birth: %s, " +
                        "Address: %s, Email: %s, Phone Number: %s(y/n)",
                username, password, name, role, dob, address, email, phoneNum);
        if (InputData.choice()) {
            userService.editUser(index, accountInfo);
        }
    }

    public static void deleteUser() {
        accountList = userService.getUserList();
        System.out.print("Nhập STT Tài khoản muốn xóa: ");
        int index = InputData.getIndex(accountList);

        String username = accountList.get(index).getUsername();
        System.out.printf("Bạn muốn xóa Username: %s? (y/n)%n", username);
        if (InputData.choice()) {
            userService.deleteUser(index);
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public static void clearScreen(int line) {
        for (int i = 0; i < line; i++) {
            System.out.println();
        }
    }
}

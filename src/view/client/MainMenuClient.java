package view.client;

import model.Account;
import services.ClearScreen;

import java.util.Scanner;

public class MainMenuClient {
    public static Scanner scanner = new Scanner(System.in);

    public static void mainMenuClient(Account account) {
        int choice;
        do {
            ClearScreen.clearScreen();
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.printf("⚃\t\t%-40s⚃%n", "--Trang chủ web BÁN ĐỒNG HỒ--");
            System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 1: Mở mục quản lý Tài khoản");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 2: Mở mục mua Sản phầm");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 3: Mở mục Giỏ hàng");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 4: Mở mục Đơn hàng");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 0: Đăng xuất");
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.print("Enter number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    ClearScreen.clearScreen();
                    AcountView.menuAccount(account);
                    break;
                case 2:
                    ShopView.viewShop(account.getId());
                    break;
                case 3:
                    ClearScreen.clearScreen();
                    CartView.cartView(account.getId());
                    break;
                case 4:
                    OrderClientView.orderView(account);
                    break;
                case 0:
                    break;
                default:
                    ClearScreen.clearScreen();
                    System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (choice != 0);
    }
}

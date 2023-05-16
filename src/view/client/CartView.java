package view.client;

import input.InputData;
import model.Cart;
import model.CartDetail;
import services.CartService;
import view.user.ClearScreen;

import java.util.ArrayList;
import java.util.Scanner;

public class CartView {
    public static Scanner scanner = new Scanner(System.in);
    public static CartService cartService = new CartService();
    public static Cart myCart = new Cart();

    public static ArrayList<CartDetail> myCartDetail = new ArrayList<>();

    public static void cartView(long idUser) {
        int choice;
        do {
            myCart = cartService.getCartListByID(idUser);
            myCartDetail = myCart.getDetailList();
            if (myCart.getDetailList().isEmpty()) {
                System.out.println("Giỏ hàng đang trống!");
                ClearScreen.clearScreen(3);
                System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
                System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
                System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 0: Quay lại");
                System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
                System.out.print("Enter number: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    choice = -1;
                }
                switch (choice) {
                    case 0:
                        break;
                    default:
                        System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
                }
            } else {
                showCard();
                ClearScreen.clearScreen(3);
                System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
                System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
                System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 1: Xác nhận Đặt hàng");
                System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 2: Hủy mua sản phẩm");
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
                        confirmCard(idUser);
                        break;
                    case 2:
                        removeCard(idUser);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
                }
            }
        }
        while (choice != 0);
    }

        public static void showCard() {
        int i = 1;
        System.out.println("╔═══════╦══════════════════════════════════════════════════════════════╦═════════════════╦══════════════════════╗");
        System.out.printf("║ %-5s ║ %-60s ║ %-15s ║ %-20s ║%n", " STT", "                     Product Name", "    Quantity", "      Price");
        System.out.println("╠═══════╬══════════════════════════════════════════════════════════════╬═════════════════╬══════════════════════╣");
        for (CartDetail cartDetail : myCartDetail)
            System.out.printf("║\t%-4s║ %-60s ║ %15s ║ %20s ║%n", i++, cartDetail.getProductName(), cartDetail.getBuy_quantity(), cartDetail.showPrice());
        System.out.println("╠═══════╩══════════════════════════════════════════════════════════════╩═════════════════╬══════════════════════╣");
        System.out.printf("║ %85s  ║ %20s ║%n", "Total Price:  ", myCart.showTotalPrice());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════╩══════════════════════╝");
    }

    public static void confirmCard(long idUser) {
        System.out.print("Bạn xác nhận sẽ mua Hàng này? (y/n): ");
        if (InputData.choice()) {
            cartService.confirmBuy(idUser);
        }
    }

    public static void removeCard(long idUser) {
        System.out.print("Bạn xác nhận sẽ hủy giỏ Hàng này? (y/n): ");
        if (InputData.choice()) {
            cartService.resetCart(idUser);
            System.out.println("Đã hủy bỏ giỏ hàng!");
        }
    }
}

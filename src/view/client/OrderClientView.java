package view.client;

import input.InputData;
import model.Account;
import model.Order;
import model.OrderDetail;
import services.OrderDetailService;
import services.OrderService;
import view.user.ClearScreen;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderClientView {
    public static Scanner scanner = new Scanner(System.in);
    public static OrderService orderService = new OrderService();
    public static OrderDetailService orderDetailService = new OrderDetailService();
    public static ArrayList<Order> myOrder = new ArrayList<>();


    public static void orderView(Account myUser) {
        myOrder = orderService.findOrderById(myUser.getId());
        int choice;
        if (myOrder.isEmpty()) {
            System.out.println("Bạn không có đơn đặt hàng nào!");
            ClearScreen.clearScreen(3);
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
            System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 0: Quay lại");
            System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
            do {
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
            }
            while (choice != 0);
        } else {
            do {
                ClearScreen.clearScreen(3);
                showOrderDetail();
                System.out.println("⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃");
                System.out.printf("⚃\t\t%-40s⚃%n", "Chọn trong các mục");
                System.out.printf("⚃\t\t%-40s⚃%n", "Nhấn 1: Xem chi tiết");
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
                        viewDetail();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
                }
            }
            while (choice != 0);
        }
    }

    public static void showOrderDetail() {
        int i = 1;
        System.out.println("╔═══════╦══════════════════════╦═══════════════════════════╦════════════╗");
        System.out.printf("║ %-5s ║ %-20s ║ %-25s ║ %-10s ║%n", " STT", "      ID Order", "        Time Order", "  Status");
        System.out.println("╠═══════╬══════════════════════╬═══════════════════════════╬════════════╣");
        for (Order order : myOrder)
            System.out.printf("║\t%-4s║ %-20s ║ %-25s ║ %-10s ║%n", i++, order.getIdOrder(), order.getDateTime(), order.getStatus());
        System.out.println("╚═══════╩══════════════════════╩═══════════════════════════╩════════════╝");
    }

        public static void viewDetail() {
        System.out.print("Nhập STT Đơn hàng cần xem: ");
        int index = InputData.getIndex(myOrder);

        long idOrderDetail = myOrder.get(index).getIdOrderDetail();
        ArrayList<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByID(idOrderDetail);
        System.out.println("╔═══════╦══════════════════════════════════════════════════════════════╦═════════════════╦═════════════════╗");
        System.out.printf("║ %-5s ║ %-60s ║ %-15s ║ %-15s ║%n", " STT", "                     Product Name", "    Quantity", "      Price");
        System.out.println("╠═══════╬══════════════════════════════════════════════════════════════╬═════════════════╬═════════════════╣");
        int i = 1;
        for (OrderDetail orderDetail : orderDetailList)
            System.out.printf("║\t%-4s║%s║%n", i++, orderDetail.display());
        System.out.println("╠═══════╩══════════════════════════════════════════════════════════════╩═════════════════╬═════════════════╣");
        System.out.printf("║ %85s  ║ %15s ║%n", "Total Price:  ", orderDetailService.totalPrice);
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════╩═════════════════╝");
    }
}



package view.client;

import input.InputData;
import model.Account;
import model.Order;
import model.OrderDetail;
import services.OrderService;
import services.ClearScreen;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderClientView {
    public static Scanner scanner = new Scanner(System.in);
    public static OrderService orderService = new OrderService();
    public static ArrayList<Order> myOrder = new ArrayList<>();


    public static void orderView(Account myUser) {
        myOrder = orderService.findOrderById(myUser.getId());
        int choice;
        ClearScreen.clearScreen();
        if (myOrder.isEmpty()) {
            System.out.println("Bạn không có đơn đặt hàng nào!");
            ClearScreen.clearScreen(3);
            System.out.printf("                                                        ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.printf("                                                        ⚃\t\t%-40s⚃%n", "Chọn trong các mục");
            System.out.printf("                                                        ⚃\t\t%-40s⚃%n", "Nhấn 0: Quay lại");
            System.out.printf("                                                        ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            do {
                System.out.print("Enter number: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    choice = -1;
                }
                switch (choice) {
                    case 0:
                        ClearScreen.clearScreen();
                        break;
                    default:
                        ClearScreen.clearScreen();
                        System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
                }
            }
            while (choice != 0);
        } else {
            do {
                showOrderDetail();
                ClearScreen.clearScreen(3);
                System.out.printf("           ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
                System.out.printf("           ⚃%-8s%-40s⚃%n", "", "Chọn trong các mục");
                System.out.printf("           ⚃%-8s%-40s⚃%n", "", "Nhấn 1: Xem chi tiết");
                System.out.printf("           ⚃%-8s%-40s⚃%n", "", "Nhấn 0: Quay lại");
                System.out.printf("           ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
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
                        ClearScreen.clearScreen();
                        break;
                    default:
                        ClearScreen.clearScreen();
                        System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
                }
            }
            while (choice != 0);
        }
    }

    public static void showOrderDetail() {
        int i = 1;
        System.out.printf("╔═══════╦══════════════════════╦═══════════════════════════╦════════════╗%n");
        System.out.printf("║ %-5s ║ %-20s ║ %-25s ║ %-10s ║%n", " STT", "      ID Order", "        Time Order", "  Status");
        System.out.printf("╠═══════╬══════════════════════╬═══════════════════════════╬════════════╣%n");
        for (Order order : myOrder)
            System.out.printf("║\t%-4s║ %-20s ║ %-25s ║ %-10s ║%n", i++, order.getIdOrder(), order.getDateTime(), order.getStatus());
        System.out.printf("╚═══════╩══════════════════════╩═══════════════════════════╩════════════╝%n");
    }

    public static void viewDetail() {
        System.out.print("Nhập STT Đơn hàng cần xem: ");
        int index = InputData.getIndex(myOrder);
        ClearScreen.clearScreen();
        showDetail(index);
    }

    private static void showDetail(int index) {
        Order order = myOrder.get(index);
        ArrayList<OrderDetail> orderDetailList = order.getOrderDetailList();
        int choice;
        do {
            System.out.printf("╔═══════╦══════════════════════════════════════════════════════════════╦═════════════════╦═════════════════╗%n");
            System.out.printf("║ %-5s ║ %-60s ║ %-15s ║ %-15s ║%n", " STT", "                     Product Name", "    Quantity", "      Price");
            System.out.printf("╠═══════╬══════════════════════════════════════════════════════════════╬═════════════════╬═════════════════╣%n");
            int i = 1;
            for (OrderDetail orderDetail : orderDetailList)
                System.out.printf("║\t%-4s║%s║%n", i++, orderDetail.display());
            System.out.printf("╠═══════╩══════════════════════════════════════════════════════════════╩═════════════════╬═════════════════╣%n");
            System.out.printf("║ %85s  ║ %15s ║%n", "Total Price:  ", order.showTotalPrice());
            System.out.printf("╚════════════════════════════════════════════════════════════════════════════════════════╩═════════════════╝%n");
            ClearScreen.clearScreen(3);
            System.out.printf("                           ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.printf("                           ⚃%-8s%-40s⚃%n", "", "Chọn trong các mục");
            System.out.printf("                           ⚃%-8s%-40s⚃%n", "", "Nhấn 0: Quay lại");
            System.out.printf("                           ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.print("Enter number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 0:
                    ClearScreen.clearScreen();
                    break;
                default:
                    ClearScreen.clearScreen();
                    System.out.println("Error! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        }
        while (choice != 0);
    }
}




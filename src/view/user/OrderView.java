package view.user;

import input.InputData;
import model.Order;
import services.ClearScreen;
import services.OrderService;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {
    public static OrderService orderService = new OrderService();
    public static ArrayList<Order> orderList = new ArrayList<>();

    public static void orders() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showOrder();
            System.out.printf("                          ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.printf("                          ⚃%-8s%-40s⚃%n", "", "--Menu quản lý Đơn đặt hàng--");
            System.out.printf("                          ⚃%-8s%-40s⚃%n", "", "Chọn trong các mục");
            System.out.printf("                          ⚃%-8s%-40s⚃%n", "", "Nhấn 1: Xem chi tiết đơn hàng");
            System.out.printf("                          ⚃%-8s%-40s⚃%n", "", "Nhấn 2: Xử lý đơn hàng");
            System.out.printf("                          ⚃%-8s%-40s⚃%n", "", "Nhấn 0: Quay lại");
            System.out.printf("                          ⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃⚃%n");
            System.out.print("Enter number: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    ClearScreen.clearScreen(5);
                    viewDetail();
                    break;
                case 2:
                    ClearScreen.clearScreen(5);
                    processingOrder();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    break;
                default:
                    ClearScreen.clearScreen();
                    System.out.println("Lỗi! Không nằm trong mục lục. Yêu cầu chọn lại:");
            }
        } while (choice != 0);
    }

    public static void showOrder() {
        orderList = orderService.getOrderList();
        System.out.printf("╔═══════╦═════════════════╦═════════════════╦═══════════════════════════╦═════════════════╦═════════════════╗%n");
        System.out.printf("║ %-5s ║ %-15s ║ %-15s ║ %-25s ║ %-15s ║ %-15s ║%n", "  STT", "    ID Order", "    ID User", "       Time Order", "ID OrderDetail", "    Status");
        System.out.printf("╠═══════╬═════════════════╬═════════════════╬═══════════════════════════╬═════════════════╬═════════════════╣%n");
        int i = 1;
        for (Order order : orderList) {
            System.out.printf("║\t%-4s║%s║%n", i, order.display());
            i++;
        }
        System.out.printf("╚═══════╩═════════════════╩═════════════════╩═══════════════════════════╩═════════════════╩═════════════════╝%n");
    }

    public static void viewDetail() {
        System.out.print("Nhập STT Đơn hàng cần xem: ");
        int index = InputData.getIndex(orderList);
        ClearScreen.clearScreen();
        OrderDetailView.orderDetail(index);
    }

    public static void processingOrder() {
        System.out.print("Nhập STT Đơn hàng cần xử lý: ");
        int index = InputData.getIndex(orderList);
        Order order = orderList.get(index);
        if (order.getStatus().equals("Hoàn thành")) {
            System.out.println("Đơn hàng này đã ở trạng thái \"Hoàn thành\"!");
            return;
        }
        System.out.println("Bạn muốn chuyển đơn hàng này thành trạng thái \"Đã hoàn thành\" ? (y/n): ");
        if (InputData.choice()) {
            orderService.finishOrder(order);
            System.out.println("Chuyển trạng thái thành công!");
        }
    }
}

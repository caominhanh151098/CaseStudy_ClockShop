package services;

import model.CartDetail;
import model.OrderDetail;
import model.Product;
import output.WriteFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class OrderDetailService {
    private static ProductService productService = new ProductService();
    private static ArrayList<OrderDetail> orderDetailList = new ArrayList<>();
    private static String path = "data\\orderdetail.csv";
    public static int totalPrice;

    public ArrayList<OrderDetail> getOrderDetailList() {
        orderDetailList.clear();
        ArrayList<Product> productList = productService.getProductList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                String[] order = line.split(",");
                long idOrderDetail = Long.parseLong(order[0]);
                long idProduct = Long.parseLong(order[1]);
                int ordered_quantity = Integer.parseInt(order[2]);
                String productName = null;
                int productPrice = 0;
                for (Product product : productList)
                    if (product.getIdProduct() == idProduct) {
                        productName = product.getProductName();
                        productPrice = product.getPrice();
                    }
                OrderDetail orderDetail = new OrderDetail()
                        .setIdOrderDetail(idOrderDetail)
                        .setIdProduct(idProduct)
                        .setProductName(productName)
                        .setProductPrice(productPrice)
                        .setOrdered_quantity(ordered_quantity);
                orderDetailList.add(orderDetail);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Không thấy file data!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return orderDetailList;
    }

    public ArrayList<OrderDetail> getOrderDetailByID(long idOrderDetail) {
        orderDetailList.clear();
        getOrderDetailList();
        totalPrice = 0;
        ArrayList<OrderDetail> detailList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            if (orderDetail.getIdOrderDetail() == idOrderDetail) {
                totalPrice += orderDetail.getProductPrice() * orderDetail.getOrdered_quantity();
                detailList.add(orderDetail);
            }
        }
        return detailList;
    }

    public long newOrderDetail(ArrayList<CartDetail> cartDetailList) {
        getOrderDetailList();
        long idOrderDetail = System.currentTimeMillis();
        for (CartDetail cartDetail : cartDetailList) {
            OrderDetail newOrderDetail = new OrderDetail()
                    .setIdOrderDetail(idOrderDetail)
                    .setIdProduct(cartDetail.getIdProduct())
                    .setProductName(cartDetail.getProductName())
                    .setOrdered_quantity(cartDetail.getBuy_quantity());
            orderDetailList.add(newOrderDetail);
        }
        WriteFile.editData(orderDetailList,path);
        return idOrderDetail;
    }
}

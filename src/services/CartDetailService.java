package services;



import model.CartDetail;
import model.Product;
import output.WriteFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class CartDetailService {
    public static ProductService productService = new ProductService();
    public static ArrayList<CartDetail> cartDetailList = new ArrayList<>();
    public static long totalPrice;
    public static String path = "data\\cartdetail.csv";

    public ArrayList<CartDetail> getCartDetailList() {
        ArrayList<Product> productList = productService.getProductList();
        cartDetailList.clear();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                String[] order = line.split(",");
                for (Product product : productList) {
                    if (product.getIdProduct() == Long.parseLong(order[1])) {
                        long idCartDetail = Long.parseLong(order[0]);
                        int ordered_quantity = Integer.parseInt(order[2]);
                        CartDetail cartDetail = new CartDetail()
                                .setIdCartDetail(idCartDetail)
                                .setIdProduct(product.getIdProduct())
                                .setProductName(product.getProductName())
                                .setBuy_quantity(ordered_quantity)
                                .setProductPrice(product.getPrice());
                        cartDetailList.add(cartDetail);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! Không thấy file data!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return cartDetailList;
    }

    public ArrayList<CartDetail> getCartDetailByID(long idCart) {
        ArrayList<CartDetail> userCartDetailList = new ArrayList<>();
        getCartDetailList();
        totalPrice = 0;
        for (CartDetail cartDetail : cartDetailList)
            if (cartDetail.getIdCartDetail() == idCart) {
                userCartDetailList.add(cartDetail);
                totalPrice += cartDetail.getBuy_quantity() * cartDetail.getProductPrice();
            }
        return userCartDetailList;
    }

    public void addCartDetail(CartDetail myCartDetail) {
        getCartDetailList();
        for (int i = 0; i < cartDetailList.size(); i++) {
            CartDetail cartDetail = cartDetailList.get(i);
            if (cartDetail.getIdCartDetail() == myCartDetail.getIdCartDetail() &&
                    cartDetail.getIdProduct() == myCartDetail.getIdProduct()) {
                cartDetail.setBuy_quantity(cartDetail.getBuy_quantity() + myCartDetail.getBuy_quantity());
                WriteFile.editData(cartDetailList, path);
                return;
            }
        }
        cartDetailList.add(myCartDetail);
        WriteFile.editData(cartDetailList, path);
    }

    public void deleteCartDetail(long idCart) {
        getCartDetailList();
        for (int i = 0; i < cartDetailList.size(); i++)
            if (cartDetailList.get(i).getIdCartDetail() == idCart)
//                cartDetailList.get(i).
                cartDetailList.remove(cartDetailList.get(i--));
        WriteFile.editData(cartDetailList, path);
    }
}

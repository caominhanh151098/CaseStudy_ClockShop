package services;


import model.Cart;
import model.CartDetail;
import output.WriteFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class CartService {
    public static OrderService orderService = new OrderService();
    public static CartDetailService cartDetailService = new CartDetailService();
    public static ProductService productService = new ProductService();
    public static ArrayList<Cart> cartList = new ArrayList<>();
    public static String path = "data\\cart.csv";

    public ArrayList<Cart> getCartList() {
        cartList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                String[] order = line.split(",");
                long idCart = Long.parseLong(order[0]);
                long idUser = Long.parseLong(order[1]);
                ArrayList<CartDetail> detailList = cartDetailService.getCartDetailByID(idCart);
                long totalPrice = cartDetailService.totalPrice;
                Cart cartInfo = new Cart()
                        .setIdCart(idCart)
                        .setIdUser(idUser)
                        .setDetailList(detailList)
                        .setTotalPrice(totalPrice);
                cartList.add(cartInfo);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error! Không thấy file data!");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return cartList;
    }

    public Cart getCartListByID(long idUser) {
        getCartList();
        Cart getCart = new Cart();
        for (Cart cart : cartList)
            if (idUser == cart.getIdUser())
                getCart = cart;
        return getCart;
    }

    public Cart newCart(long idUser) {
        getCartList();
        for (Cart cart : cartList)
            if (cart.getIdUser() == idUser)
                return cart;
        Cart cart = new Cart().setIdUser(idUser);
        cartList.add(cart);
        WriteFile.editData(cartList, path);
        return cart;
    }

    public void confirmBuy(long idUser) {
        Cart userCart = getCartListByID(idUser);
        ArrayList<CartDetail> cartDetailList = userCart.getDetailList();
        for (CartDetail cartDetail : cartDetailList)
            if (!productService.checkQuantityProduct(cartDetail)) {
                System.out.println("Lỗi! Số lượng sản phẩm đặt hàng đã lớn hơn sản phẩm trong kho!");
                return;
            }
        orderService.newOrderForUser(idUser);
        for (CartDetail cartDetail : cartDetailList)
            productService.buyProduct(cartDetail.getIdProduct(), cartDetail.getBuy_quantity());
        resetCart(idUser);
        userCart.setDetailList(new ArrayList<>());
        System.out.println("Cám ơn bạn đã mua hàng!");
    }

    public void resetCart(long idUser) {
        getCartList();
        for (Cart cart : cartList)
            if (idUser == cart.getIdUser()) {
                cartDetailService.deleteCartDetail(cart.getIdCart());
                return;
            }
    }
}

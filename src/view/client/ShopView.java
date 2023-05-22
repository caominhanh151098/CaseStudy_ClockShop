package view.client;

import input.InputData;
import model.Cart;
import model.CartDetail;
import model.Product;
import services.CartDetailService;
import services.CartService;
import services.ProductService;
import services.ClearScreen;

import java.util.ArrayList;

public class ShopView {
    static ProductService productService = new ProductService();
    static CartDetailService cartDetailService = new CartDetailService();
    static CartService cartService = new CartService();
    static ArrayList<Product> shopProductList = new ArrayList<>();

    public static void viewShop(long idUser) {

        Cart myCart = cartService.newCart(idUser);
        do {
            ClearScreen.clearScreen();
            showProduct();
            System.out.println("Chọn STT sản phẩm bạn muốn mua: ");
            System.out.print("Enter number: ");
            int productIndex = InputData.getIndex(shopProductList);
            Product choiceProduct = shopProductList.get(productIndex);
            int quantityBuy = InputData.getQuantityBuy(choiceProduct);
            System.out.printf("Bạn muốn mua sản phẩm này [Sản phẩm: %s | số lượng: %s]  (y/n): ", choiceProduct.getProductName(), quantityBuy);
            if (InputData.choice()) {
                CartDetail cartDetail = new CartDetail()
                        .setIdCartDetail(myCart.getIdCart())
                        .setIdProduct(choiceProduct.getIdProduct())
                        .setBuy_quantity(quantityBuy);
                cartDetailService.addCartDetail(cartDetail);
                System.out.println("Đã thêm vào giỏ hàng!");
            } else System.out.println("Đã bỏ sản phẩm!");
            System.out.print("Bạn có muốn tiếp tục mua?  (y/n): ");
            if (!InputData.choice()) {
                ClearScreen.clearScreen();
                break;
            }
        } while (true);
    }

    public static void showProduct() {
        shopProductList.clear();
        ArrayList<Product> productList = productService.getProductList();
        System.out.println("╔═══════╦══════════════════════════════════════════════════════════════╦══════════════════════╦════════════╦══════════════════════╗");
        System.out.printf("║%-7s║ %-60s ║ %-20s ║ %-10s ║ %-20s ║%n", "  STT", "                      Product name", "       Brand", " Quantity", "    Price");
        System.out.println("╠═══════╬══════════════════════════════════════════════════════════════╬══════════════════════╬════════════╬══════════════════════╣");
        int i = 1;
        for (Product product : productList)
            if (product.getQuantity() > 0) {
                shopProductList.add(product);
                System.out.printf("║\t%-4s║%s║%n", i++, product.display());
            }
        System.out.println("╚═══════╩══════════════════════════════════════════════════════════════╩══════════════════════╩════════════╩══════════════════════╝");
    }
}

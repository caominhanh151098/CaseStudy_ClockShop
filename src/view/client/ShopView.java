package view.client;

import input.InputData;
import model.Cart;
import model.CartDetail;
import model.Product;
import services.CartDetailService;
import services.CartService;
import services.ProductService;
import view.user.ClearScreen;

import java.util.ArrayList;

public class ShopView {
    static ProductService productService = new ProductService();
    static CartDetailService cartDetailService = new CartDetailService();
    static CartService cartService = new CartService();
    static ArrayList<Product> productList = new ArrayList<>();

    public static void viewShop(long idUser) {
        ClearScreen.clearScreen();
        Cart myCart = cartService.newCart(idUser);
        do {
            showProduct();
            System.out.println("Chọn STT sản phẩm bạn muốn mua: ");
            System.out.print("Enter number: ");
            int productIndex = InputData.getIndex(productList);
            Product choiceProduct = productList.get(productIndex);
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
            System.out.println("Bạn có muốn tiếp tục mua?  (y/n): ");
            if (!InputData.choice())
                break;
        } while (true);
    }

        public static void showProduct() {
        productList = productService.getProductList();
            System.out.println("╔═══════╦══════════════════════════════════════════════════════════════╦══════════════════════╦════════════╦══════════════════════╗");
            System.out.printf("║%-7s║ %-60s ║ %-20s ║ %-10s ║ %-20s ║%n", "  STT", "                      Product name", "       Brand", " Quantity", "    Price");
            System.out.println("╠═══════╬══════════════════════════════════════════════════════════════╬══════════════════════╬════════════╬══════════════════════╣");
        int i = 1;
        for (Product product : productList)
            if (product.getQuantity() > 0)
                System.out.printf("║\t%-4s║%s║%n", i++, product.display());
            System.out.println("╚═══════╩══════════════════════════════════════════════════════════════╩══════════════════════╩════════════╩══════════════════════╝");
    }
}

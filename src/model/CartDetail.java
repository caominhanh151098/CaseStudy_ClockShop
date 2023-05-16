package model;

import java.text.DecimalFormat;

public class CartDetail {
    private long idCartDetail = System.currentTimeMillis();
    private long idProduct;
    private String productName;
    private int buy_quantity;
    private int productPrice;

    public CartDetail() {
    }

    public long getIdCartDetail() {
        return idCartDetail;
    }

    public CartDetail setIdCartDetail(long idCartDetail) {
        this.idCartDetail = idCartDetail;
        return this;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public CartDetail setIdProduct(long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public CartDetail setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getBuy_quantity() {
        return buy_quantity;
    }

    public CartDetail setBuy_quantity(int buy_quantity) {
        this.buy_quantity = buy_quantity;
        return this;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String showPrice() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(productPrice) + " VND";
    }

    public CartDetail setProductPrice(int productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String toString() {
        return String.format("%s,%s,%s", getIdCartDetail(), getIdProduct(), getBuy_quantity());
    }
}

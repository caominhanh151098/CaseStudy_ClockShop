package model;

import java.text.DecimalFormat;

public class OrderDetail {
    private long idOrderDetail = System.currentTimeMillis();
    private long idProduct;
    private String productName;
    private int ordered_quantity;
    private int productPrice;

    public OrderDetail() {
    }

    public OrderDetail(long idOrderDetail, String productName, int ordered_quantity, int productPrice) {
        this.idOrderDetail = idOrderDetail;
        this.productName = productName;
        this.ordered_quantity = ordered_quantity;
        this.productPrice = productPrice;
    }

    public long getIdOrderDetail() {
        return idOrderDetail;
    }

    public OrderDetail setIdOrderDetail(long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
        return this;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public OrderDetail setIdProduct(long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public OrderDetail setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getOrdered_quantity() {
        return ordered_quantity;
    }

    public OrderDetail setOrdered_quantity(int ordered_quantity) {
        this.ordered_quantity = ordered_quantity;
        return this;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String showProductPrice() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(productPrice) + " VND";
    }

    public OrderDetail setProductPrice(int productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String display() {
        return String.format(" %-60s ║ %15s ║ %15s ", getProductName(), getOrdered_quantity(), showProductPrice());
    }

    public String toString() {
        return String.format("%s,%s,%s", getIdOrderDetail(), getIdProduct(), getOrdered_quantity());
    }
}

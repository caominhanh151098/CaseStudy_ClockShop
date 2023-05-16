package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {
    private long idCart = System.currentTimeMillis();
    private long idUser;
    private ArrayList<CartDetail> detailList = new ArrayList<>();
    private long totalPrice;

    public Cart() {
    }

    public long getIdCart() {
        return idCart;
    }

    public Cart setIdCart(long idCart) {
        this.idCart = idCart;
        return this;
    }

    public long getIdUser() {
        return idUser;
    }

    public Cart setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }

    public ArrayList<CartDetail> getDetailList() {
        return detailList;
    }

    public Cart setDetailList(ArrayList<CartDetail> detailList) {
        this.detailList = detailList;
        return this;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public String showTotalPrice() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(totalPrice) + " VND";
    }

    public Cart setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", getIdCart(), getIdUser());
    }
}

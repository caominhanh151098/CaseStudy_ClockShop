package model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private long idOrder = System.currentTimeMillis();
    private long idUser;
    private Date orderDate = new Date(idOrder);
    private long idOrderDetail;
    private String status = "Chưa xử lý";
    private ArrayList<OrderDetail> orderDetailList;
    private long totalPrice;

    public Order() {
    }

    public long getIdOrder() {
        return idOrder;
    }

    public Order setIdOrder(long idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public long getIdUser() {
        return idUser;
    }

    public Order setIdUser(long idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getOrderDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(orderDate);
    }

    public String getDateTime() {
        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").format(orderDate);
    }

    public Order setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public long getIdOrderDetail() {
        return idOrderDetail;
    }

    public Order setIdOrderDetail(long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public Order setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
        return this;
    }

    public long getTotalPrice() {
        return totalPrice;
    }
    public String showTotalPrice() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(totalPrice) + " VND";
    }

    public Order setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String display() {
        return String.format(" %15s ║ %15s ║ %-25s ║ %15s ║ %15s ", getIdOrder(), getIdUser(), getDateTime(), getIdOrderDetail(), getStatus());
    }

    public String toString() {
        return String.format("%s,%s,%s,%s",getIdOrder(),getIdUser(),getIdOrderDetail(),getStatus());
    }
}

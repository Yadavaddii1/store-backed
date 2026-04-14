package com.wheat_store.demo.DTO;

import java.util.List;



public class OrderResponse {

    private String orderId;

    private String address;

    private double latitude;

    private double longitude;

    private double totalAmount;

    private String status;
    private List<OrderItemDTO> items;

    public String getOrderId() { return orderId;}
    public void setOrderId(String orderId) {this.orderId = orderId;}

    public String getAddress() {return address;}
    public void setAddress(String address) { this.address = address;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude) { this.latitude = latitude;}

    public double getLongitude() {return longitude;}
    public void setLongitude(double longitude) { this.longitude = longitude;}

    public double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public List<OrderItemDTO> getItems() {return items;}
    public void setItems(List<OrderItemDTO> items) {this.items = items;}
    // getters & setters
}
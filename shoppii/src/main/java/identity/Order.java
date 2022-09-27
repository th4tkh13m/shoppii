package identity;

import java.sql.Time;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private String paymentMethod, status;
    private Time time;
    private Address address;
    private ArrayList<OrderItem> orderItems;
    
    public Order(int orderId, Customer customer, String paymentMethod, String status, Time time, Address address) {
        this.orderId = orderId;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.time = time;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }
    
}

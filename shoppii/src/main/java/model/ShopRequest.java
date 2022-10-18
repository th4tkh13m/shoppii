package model;

import java.sql.Time;

public class ShopRequest {
    private Customer customer;
    private String name, address, description, status;
    private Time time;
    
    public ShopRequest(Customer customer, String name, String address, String description, String status, Time time) {
        this.customer = customer;
        this.name = name;
        this.address = address;
        this.description = description;
        this.status = status;
        this.time = time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "ShopRequest [address=" + address + ", customer=" + customer + ", description=" + description + ", name="
                + name + ", status=" + status + ", time=" + time + "]";
    }

    
}

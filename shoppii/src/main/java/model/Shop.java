package model;

public class Shop {
    private int shopId;
    private String name, address, description;
    private boolean status;

    public Shop(String name, String address, String description, boolean status) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.status = status;
    }

    public int getShopId() {
        return shopId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    } 
}

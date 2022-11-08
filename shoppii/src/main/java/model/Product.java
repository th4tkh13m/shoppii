package model;

import java.util.ArrayList;

public class Product {
    private int productId;
    private String name;
    private int price, quantity;
    private String description;
    private ArrayList<String> images;
    private Shop shop;
    private Category category;
    private boolean isAvailable;


    public Product(int productId, String name, int price, int quantity,
            String description, Shop shop, Category category, boolean isAvailable) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.shop = shop;
        this.category = category;
        this.isAvailable = isAvailable;
    }

   

    public Product(String name, int price, int quantity, String description, Shop shop, Category category, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.shop = shop;
        this.category = category;
        this.isAvailable = isAvailable;
    }



    public Product(int productId, String name, int price, int quantity, boolean isAvailable) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }



    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }



    public int getProductId() {
        return productId;
    }

  
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getDescription() {
        return description;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

  
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

  

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }


    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Shop getShop() {
        return shop;
    }

    public Category getCategory() {
        return category;
    }
}

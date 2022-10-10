package model;

import java.util.ArrayList;

public class Product {
    private int productId, shopId;
    private String name;
    private int price, quantity;
    private int categoryId;
    private String description;
    private ArrayList<String> images;

    public Product(int productId, int shopId, String name, int price, int quantity, int categoryId,
            String description) {
        this.productId = productId;
        this.shopId = shopId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Product(int shopId, String name, int price, int quantity, int categoryId,
            String description) {
        this.shopId = shopId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public int getShopId() {
        return shopId;
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

    public int getcategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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

    public void setcategory_id(int categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", shopId=" + shopId + ", name=" + name + ", price=" + price
                + ", quantity=" + quantity + ", category_id=" + categoryId + ", description=" + description + ", images="
                + images + "]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (productId != other.productId)
            return false;
        if (shopId != other.shopId)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price != other.price)
            return false;
        if (quantity != other.quantity)
            return false;
        if (categoryId == 0) {
            if (other.categoryId != 0)
                return false;
        } else if (!(categoryId == other.categoryId))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

        
}

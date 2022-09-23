package net.codejava.aws;

import java.util.ArrayList;

public class ProductExample {
    String title;
    ArrayList<String> listPhotos;

    public ProductExample(String title, ArrayList<String> listPhotos) {
        this.title = title;
        this.listPhotos = listPhotos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getListPhotos() {
        return listPhotos;
    }

    public void setListPhotos(ArrayList<String> listPhotos) {
        this.listPhotos = listPhotos;
    }
}

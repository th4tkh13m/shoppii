package controllers.shop;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import dao.CartDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.ShopDAO;
import dbconnect.DBConnect;
import dbconnect.S3Util;
import errors.ErrorHandle;
import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class ProductsShopServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        try {
            String keyword = null;
            if (req.getParameter("keyword") != null) {
                keyword = req.getParameter("keyword");
            }
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int shopId = Integer.parseInt(req.getParameter("shopId"));
            ArrayList<Product> products = new ArrayList<>();
            products = ProductDAO.getProductByShopId(shopId, keyword, connection);
            for (Product product : products) {
                ArrayList<String> images =
                    S3Util.listPhotos("products/" + product.getProductId() + "/");
                    System.out.println(images);
                ArrayList<String> imagesUrl = new ArrayList<>();
                if (images.size() > 0) {
                    
                    imagesUrl.add(images.get(0));
                }
                product.setImages(imagesUrl);
            }
            System.out.println(products);
            String json = gson.toJson(products);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream()
                    .println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            int shopId = Integer.parseInt(req.getParameter("shopId"));
            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int cat = Integer.parseInt(req.getParameter("categoryId"));
            String des = req.getParameter("description");
            ArrayList<Part> files = (ArrayList<Part>) req.getParts();
            Product product = ProductDAO.addProduct(new Product(name, price, quantity, des,
                    ShopDAO.getShopFromId(shopId, connection),
                    CategoryDAO.getCategoryFromId(cat, connection), true) , connection);

            for (Part part : files) {
                if (part.getName().equalsIgnoreCase("files") && part.getSize() > 0 && part.getSubmittedFileName().matches(".*\\.(jpg|png|jpeg|gif)")) {
                    S3Util.uploadObject(
                            "products/" + product.getProductId() + "/" + part.getSubmittedFileName(),
                            part.getInputStream());

                }
            }

            ArrayList<String> images =
                    S3Util.listPhotos("products/" + product.getProductId() + "/");
            
            product.setImages(images);
            String json = gson.toJson(product);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            int productId = Integer.parseInt(req.getParameter("productId"));
            int shopId = Integer.parseInt(req.getParameter("shopId"));
            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int cat = Integer.parseInt(req.getParameter("categoryId"));
            String des = req.getParameter("description");
            String[] imageDeleleted = req.getParameterValues("images");
            boolean isAvailable = Boolean.parseBoolean(req.getParameter("isAvailable"));
            System.out.println(imageDeleleted);
            Product product = new Product(productId, name, price, quantity, des,
                    ShopDAO.getShopFromId(shopId, connection),
                    CategoryDAO.getCategoryFromId(cat, connection), isAvailable);
            ProductDAO.updateProduct(product, imageDeleleted, connection);
            
            ArrayList<Part> files = (ArrayList<Part>) req.getParts();
            for (Part part : files) {
                if (part.getName().equalsIgnoreCase("imageAdded") && part.getSize() > 0 && part.getSubmittedFileName().matches(".*\\.(jpg|png|jpeg|gif)")) {
                    S3Util.uploadObject(
                            "products/" + product.getProductId() + "/" + part.getSubmittedFileName(),
                            part.getInputStream());

                }
            }
            ArrayList<String> images =
                S3Util.listPhotos("products/" + product.getProductId() + "/");
    
            product.setImages(images);
            System.out.println(product);
            String json = gson.toJson(product);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.getConnection();
            int productId = Integer.parseInt(req.getParameter("productId"));
            String json = gson.toJson(ProductDAO.deleteProduct(productId, connection));
            CartDAO.removeAfterDeleteProduct(connection);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream()
                    .println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

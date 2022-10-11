package controllers.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CartDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Product;
import model.Shop;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            resp.setContentType("application/json");
            int userId = Integer.parseInt(req.getParameter("userId"));

            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.getCartOfCustomer(userId, connection);
            String json = gson.toJson(cart);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            resp.setContentType("application/json");
            int userId = Integer.parseInt(req.getParameter("userId"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.addProductToCart(userId, productId, quantity, connection);
            String json = gson.toJson(cart);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            resp.setContentType("application/json");
            int userId = Integer.parseInt(req.getParameter("userId"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.modifyProductQuantity(userId, productId, quantity, connection);
            String json = gson.toJson(cart);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            resp.setContentType("application/json");
            int userId = Integer.parseInt(req.getParameter("userId"));
            int productId = Integer.parseInt(req.getParameter("productId"));

            HashMap<Shop, HashMap<Product, Integer>> cart = CartDAO.deleteProductFromCart(userId, productId, connection);
            String json = gson.toJson(cart);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

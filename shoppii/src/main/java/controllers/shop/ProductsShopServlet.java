package controllers.shop;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Product;

public class ProductsShopServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        Connection connection = db.getConnection();
        resp.setContentType("application/json");
        try {
            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String cat = req.getParameter("category");
            String des = req.getParameter("description");
            // Product product = ProductDAO.addProduct(new Product(name, price, quantity, cat, des), connection);
            String json = gson.toJson(new Product(name, price, quantity, cat, des));
            resp.setStatus(201);
            resp.getOutputStream().println(json);
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

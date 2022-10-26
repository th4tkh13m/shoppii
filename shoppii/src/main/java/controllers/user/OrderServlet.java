package controllers.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dao.OrderDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Order;
import model.Product;
import model.Shop;
import utils.ProductMap;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int userId = Integer.parseInt(req.getParameter("userId"));         
            String status = req.getParameter("status");;
            // ArrayList<Order> orders = OrderDAO.getOrderFromId(1, connection);
            Order order = OrderDAO.getOrderFromId(1, connection);
            String json = gson.toJson(order);
            resp.setStatus(201);
            resp.getOutputStream().println(json);
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500, e)));
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Type type = new TypeToken<HashMap<Shop, HashMap<Product, Integer>>>() {}.getType();
        gsonBuilder.registerTypeAdapter(type, new ProductMap());
        Gson gson = gsonBuilder.create();
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int userId = Integer.parseInt(req.getParameter("userId"));
            String orderJson = req.getParameter("orders");
            int addressId = Integer.parseInt(req.getParameter("addressId"));
            String paymentMethod = req.getParameter("paymentMethod");
            HashMap<Shop, HashMap<Product, Integer>> orders = gson.fromJson(orderJson, type);
            OrderDAO.addOrder(orders, userId, paymentMethod, addressId, connection);
            
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

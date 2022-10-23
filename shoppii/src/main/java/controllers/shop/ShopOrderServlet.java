package controllers.shop;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CustomerDAO;
import dao.OrderDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Order;
import model.Shop;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class ShopOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");

        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int shopId = Integer.parseInt(req.getParameter("shopId"));
            String status = req.getParameter("status");
            Shop shop = CustomerDAO.getShopFromId(shopId, connection);
            ArrayList<Order> orders = OrderDAO.getOrdersByShop(shop, status, connection);
            System.out.println(orders);
            String json = gson.toJson(orders);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");

        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            String status = req.getParameter("status");
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Order order = null;
            if (status.equalsIgnoreCase("Accepted")) {
                order = OrderDAO.acceptOrder(orderId, connection);
            } else 
                order = OrderDAO.rejectOrder(orderId, connection);
            String json = gson.toJson(order);
            resp.setStatus(200);
            resp.getOutputStream().println(json);
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

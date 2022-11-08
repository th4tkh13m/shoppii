package controllers.shop;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dao.OrderDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Order;
import model.Product;
import utils.OrderMap;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class ShopOrderServlet extends HttpServlet {
    GsonBuilder gsonBuilder = new GsonBuilder();
    Type type = new TypeToken<HashMap<Order, HashMap<Product, Integer>>>() {
    }.getType();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gsonBuilder.registerTypeAdapter(type, new OrderMap());
        Gson gson = gsonBuilder.create();
        resp.setContentType("application/json");

        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int shopId = Integer.parseInt(req.getParameter("shopId"));
            String status = req.getParameter("status");
            if (status != null) {
                status = status.toLowerCase();
            }
            HashMap<Order, HashMap<Product, Integer>> orders = OrderDAO.getOrderWithItemsofShop(shopId, status,
                    connection);
            System.out.println(orders);
            String json = gson.toJson(orders, type);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
            e.printStackTrace();
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
            if (status.equalsIgnoreCase("Accepted")) {
                OrderDAO.acceptOrder(orderId, connection);
            } else
                OrderDAO.rejectOrder(orderId, connection);

            resp.setStatus(200);
            resp.getOutputStream().println(gson.toJson("OK"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.ShopDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class ShopStatistics extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            int shopId = Integer.parseInt(req.getParameter("shopId"));
            // String filter = req.getParameter("filter");
            // ArrayList<Integer> incomes = ShopDAO.getTotalIncomes7Days(shopId, filter,
            // connection);
            System.out.println("BEFORE");
            int totalOrders = ShopDAO.getTotalAllTime(shopId, connection);
            System.out.println("DFD");
            ArrayList<Product> mostSalesProducts = ShopDAO.getMostSaledProduct(shopId, connection);
            int numberAcceptedOrders = ShopDAO.getNumberOfAcceptedOrder(shopId, connection);
            int numberRejectedOrders = ShopDAO.getNumberOfRejectedOrder(shopId, connection);
            int numberPendingOrders = ShopDAO.getNumberOfPendingOrder(shopId, connection);
            System.out.println("AFTER");
            JsonObject json = new JsonObject();
            // json.addProperty("incomes", gson.toJson(incomes));
            json.addProperty("totalOrders", totalOrders);
            JsonArray mostSalesProductsJson = new JsonArray();
            for (Product product : mostSalesProducts) {
                mostSalesProductsJson.add(gson.toJsonTree(product));
            }
            json.add("mostSalesProducts", mostSalesProductsJson);
            json.addProperty("numberAcceptedOrders", numberAcceptedOrders);
            json.addProperty("numberRejectedOrders", numberRejectedOrders);
            json.addProperty("numberPendingOrders", numberPendingOrders);

            String jsonStr = gson.toJson(json);
            resp.getOutputStream().write(jsonStr.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }
}

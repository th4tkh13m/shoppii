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

import dao.ShopDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Shop;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)

public class GetLocationShop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            ArrayList<Shop> locations = ShopDAO.getLocationsShop(connection);
            String json = gson.toJson(locations);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));

        }

    }

}

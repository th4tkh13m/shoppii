package controllers.authentication;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.CustomerDAO;
import dao.ShopDAO;
import dbconnect.DBConnect;
import dbconnect.S3Util;
import errors.ErrorHandle;
import model.Customer;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            String info = req.getParameter("info");
            String email = null, phone = null;
            if (info.contains("@")) {
                email = info;
            } else {
                phone = info;
            }
            String password = req.getParameter("password");
            Customer customer = CustomerDAO.checkLogin(email, phone, password, connection);
            if (customer != null) {
                ArrayList<String> images = S3Util.listPhotos("profile/" + customer.getUserId()
                + "/user/avatar/");
                System.out.println("profile/" + customer.getUserId()
                + "/user/avatar/");
                JsonElement jsonElement = gson.toJsonTree(customer);
                JsonObject object = jsonElement.getAsJsonObject();
                for (String avatar : images) {
                    object.addProperty("avatar", avatar);
                }
                boolean hasShop = ShopDAO.getShopFromId(customer.getUserId(), connection) != null;
                object.addProperty("hasShop", hasShop);
                String json = gson.toJson(jsonElement);
                resp.setStatus(200);
                resp.getOutputStream().write(json.getBytes("UTF-8"));

            }
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }

    }
}

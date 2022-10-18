package controllers.authentication;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import dao.CustomerDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Customer;
import utils.Utils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class LoginGoogle extends HttpServlet {
    private String code = Utils.generateCode();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String rePassword = req.getParameter("rePassword");
            System.out.println(name + " " + email + " " + password + " " + rePassword);
            if (password.equals(rePassword)) {
                Boolean isRegistered = CustomerDAO.checkEmailExist(email, connection);
                Customer customer = null;
                String json = null;
                if (!isRegistered) {
                    customer = CustomerDAO.register(email, "123", password, code, connection);
                    customer.setName(name);
                    JsonElement jsonElement = gson.toJsonTree(customer);
                    jsonElement.getAsJsonObject().addProperty("securityCode", code);
                    json = gson.toJson(jsonElement);
                    resp.setStatus(201);
                    resp.getOutputStream().write(json.getBytes("UTF-8"));
                } else {
                    customer = CustomerDAO.getCustomerFromMail(email, connection);
                    boolean hasShop = CustomerDAO.getShopFromId(customer.getUserId(), connection) != null;
                    JsonElement jsonElement = gson.toJsonTree(customer);
                    jsonElement.getAsJsonObject().addProperty("hasShop", hasShop);
                    json = gson.toJson(jsonElement);
                    resp.setStatus(201);
                    resp.getOutputStream().write(json.getBytes("UTF-8"));
                }
            } else {
                resp.setStatus(400);
                resp.getOutputStream().println(gson.toJson(new ErrorHandle("Password not match", 400)));
            }
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

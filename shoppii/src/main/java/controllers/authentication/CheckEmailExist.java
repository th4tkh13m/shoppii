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
public class CheckEmailExist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            String email = req.getParameter("email");
            Boolean isRegistered = CustomerDAO.checkEmailExist(email, connection);
            Customer customer = null;
            String json = null;
            if (!isRegistered) {
                resp.setStatus(404);
                resp.getOutputStream().println(gson.toJson(new ErrorHandle("Not found this email address", 404)));
            } else {
                customer = CustomerDAO.getCustomerFromMail(email, connection);
                boolean hasShop = CustomerDAO.getShopFromId(customer.getUserId(), connection) != null;
                JsonElement jsonElement = gson.toJsonTree(customer);
                jsonElement.getAsJsonObject().addProperty("hasShop", hasShop);
                json = gson.toJson(jsonElement);
                resp.setStatus(200);
                resp.getOutputStream().write(json.getBytes("UTF-8"));
            }

        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

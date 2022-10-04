package controllers.authentication;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CustomerDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Customer;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        Connection connection = db.getConnection();
        resp.setContentType("application/json");
        try {
            String email = null;
            String phone = null;
            if (req.getParameter("email") != null) {
                email = req.getParameter("email");
            }
            if (req.getParameter("phone") != null) {
                phone = req.getParameter("phone");
            }
            String password = req.getParameter("password");
            String rePassword = req.getParameter("rePassword");
                
            if (password.equals(rePassword)) {
                // db here
                Customer customer = CustomerDAO.register(email, phone, password, connection);
                // db here
                String json = gson.toJson(customer);
                resp.setStatus(201);
                resp.getOutputStream().println(json);
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

package controllers.authentication;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
public class ResetPassword extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");

        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            String email = null;
            String phone = null;
            String info = req.getParameter("info");
            System.out.println(info);
            if (info.contains("@")) {
                email = info;
            } else {
                phone = info;
            }

            String code = req.getParameter("code");

            Customer customer = CustomerDAO.checkResetPasswordInfo(email, phone, code, connection);
            String json = gson.toJson(customer);
            resp.setStatus(200);
            resp.getOutputStream().println(json);
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }
}

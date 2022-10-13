package controllers.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import dao.CustomerDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Customer;
import utils.Utils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class InforServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        Connection connection = db.getConnection();
        resp.setContentType("application/json");
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            boolean sex = Boolean.parseBoolean(req.getParameter("sex"));
            Date dob = Date.valueOf(req.getParameter("dob"));
            Part filePart = req.getPart("file");
            String fileName = Utils.getFileName(filePart);
            Customer updateCustomer = new Customer(userId,name, email, phone,dob, sex);
            Customer customer =   CustomerDAO.updateInfo(updateCustomer, connection, fileName,filePart.getInputStream() );
            String json = gson.toJson(customer);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }

    }
}

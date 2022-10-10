package controllers.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbconnect.DBConnect;
import errors.ErrorHandle;


public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            String payment_method = req.getParameter("payment_method");
            String status = req.getParameter("status");
            int addressId = Integer.parseInt(req.getParameter("address_id")) ;
            
            
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500, e)));
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500, e)));
        }
    }
}

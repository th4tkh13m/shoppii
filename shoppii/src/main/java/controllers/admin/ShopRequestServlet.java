package controllers.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.RequestDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.ShopRequest;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class ShopRequestServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");

        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();

            String status = req.getParameter("status");
            ArrayList<ShopRequest> requests = RequestDAO.getRequestsByStatus(status, connection);
            String json = gson.toJson(requests);
            resp.setStatus(200);
            resp.getOutputStream().println(json);
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
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
            int customerId = Integer.parseInt(req.getParameter("userId"));
            ShopRequest request = null;
            if (status.equals("Accepted")) {
                request = RequestDAO.acceptRequest(customerId, connection);
            } else {
                request = RequestDAO.rejectRequest(customerId, connection);
            }
            String json = gson.toJson(request);
            resp.setStatus(200);
            resp.getOutputStream().println(json);
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}

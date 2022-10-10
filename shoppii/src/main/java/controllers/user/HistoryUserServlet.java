package controllers.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbconnect.DBConnect;
import errors.ErrorHandle;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class HistoryUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            

        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500, e)));
        }
    }
}

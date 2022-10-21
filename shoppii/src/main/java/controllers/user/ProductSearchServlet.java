package controllers.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProductDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class ProductSearchServlet extends HttpServlet {
    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     Gson gson = new Gson();
    //     DBConnect db = new DBConnect();
    //     Connection connection = db.getConnection();
    //     resp.setContentType("application/json");
    //     try {
    //         String theCommand = req.getParameter("command");
    //         switch (theCommand) {
    //             case "Search":
    //                 searchProduct(req, resp);
    //                 break;
    //         }
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         resp.setStatus(500);
    //         resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
    //     }

    // }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            resp.setContentType("application/json");
            String keyword = req.getParameter("keyword");
            System.out.println(keyword);
            ArrayList<Product> products = ProductDAO.searchProduct(keyword, connection);
            String json = gson.toJson(products);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }

    }
}
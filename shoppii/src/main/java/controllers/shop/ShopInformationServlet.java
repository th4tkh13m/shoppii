package controllers.shop;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ShopDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Shop;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
                maxFileSize = 1024 * 1024 * 1, // 1 MB
                maxRequestSize = 1024 * 1024 * 1 // 1 MB
)

public class ShopInformationServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                Gson gson = new Gson();
                resp.setContentType("application/json");
                try {
                        DBConnect db = new DBConnect();
                        Connection connection = db.getConnection();
                        int shopId = Integer.parseInt(req.getParameter("shopId"));
                        Shop shop = ShopDAO.getShopFromId(shopId, connection);
                        String json = gson.toJson(shop);
                        resp.setStatus(200);
                        resp.getOutputStream().write(json.getBytes("UTF-8"));
                } catch (Exception e) {
                        resp.setStatus(500);
                        resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));

                }

        }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                Gson gson = new Gson();
                resp.setContentType("application/json");
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");
                try {
                        DBConnect db = new DBConnect();
                        Connection connection = db.getConnection();
                        int shopId = Integer.parseInt(req.getParameter("shopId"));
                        String shopName = req.getParameter("name");
                        String shopAddress = req.getParameter("address");
                        String description = req.getParameter("description");
                        Shop shop = ShopDAO.updateInformation(shopId, shopName, shopAddress, description, connection);
                        String json = gson.toJson(shop);
                        resp.setStatus(201);
                        resp.getOutputStream().write(json.getBytes("UTF-8"));
                } catch (Exception e) {
                        resp.setStatus(500);
                        resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
                }
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                Gson gson = new Gson();
                resp.setContentType("application/json");
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");

                try {
                        DBConnect db = new DBConnect();
                        Connection connection = db.getConnection();
                        int shopId = Integer.parseInt(req.getParameter("shopId"));
                } catch (Exception e) {
                        // TODO: handle exception
                }
        }
}

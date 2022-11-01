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

import dao.AddressDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Address;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class AddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            int userId = Integer.parseInt(req.getParameter("userId"));
            String receiverAddress = req.getParameter("receiverAddress");
            String province = req.getParameter("province");
            String ward = req.getParameter("ward");
            String district = req.getParameter("district");
            String receiverName = req.getParameter("receiverName");
            String receiverPhone = req.getParameter("receiverPhone");

            Address address = AddressDAO.addAddress(userId, receiverAddress, receiverName, receiverPhone, province,
                    ward, district,
                    connection);
            String json = gson.toJson(address);
            resp.setStatus(201);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        Connection connection = db.getConnection();
        resp.setContentType("application/json");
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            ArrayList<Address> address = AddressDAO.getAddressOfUser(userId, connection);
            String json = gson.toJson(address);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        Connection connection = db.getConnection();
        resp.setContentType("application/json");
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            int addressId = Integer.parseInt(req.getParameter("addressId"));
            AddressDAO.deleteAddress(addressId, userId, connection);
            doGet(req, resp);
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DBConnect db = new DBConnect();
        Connection connection = db.getConnection();
        resp.setContentType("application/json");
        try {

            int userId = Integer.parseInt(req.getParameter("userId"));
            int addressId = Integer.parseInt(req.getParameter("addressId"));
            String receiverAddress = req.getParameter("receiverAddress");
            String receiverName = req.getParameter("receiverName");
            String receiverPhone = req.getParameter("receiverPhone");
            String province = req.getParameter("province");
            String ward = req.getParameter("ward");
            String district = req.getParameter("district");
            boolean isDefault = Boolean.parseBoolean(req.getParameter("isDefault"));

            Address address = AddressDAO.updateAddress(addressId, userId, receiverAddress, receiverName, receiverPhone,
                    province, ward, district, isDefault,
                    connection);
            String json = gson.toJson(address);
            resp.setStatus(200);
            resp.getOutputStream().write(json.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle("Something went wrong", 500)));
        }
    }
}
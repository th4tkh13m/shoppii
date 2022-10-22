package controllers.authentication;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.CustomerDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Customer;
import utils.Utils;

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
            

            if (customer != null) {
                int tokenId = 1;
                String token = Utils.generateToken();

                Utils.writeTokenInfoToFile(tokenId, customer.getUserId(), token);
                
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("tokenId", tokenId);
                jsonObject.addProperty("token", token);
                String json = gson.toJson(jsonObject);
                resp.setStatus(200);
                resp.getOutputStream().println(json);
            } else {
                throw new Exception("Error!");
            }       
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(), 500)));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}

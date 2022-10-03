package controllers.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CustomerDAO;
import model.Customer;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String email = null;
        String phone = null;
        if (req.getParameter("email") != null) {
            email = req.getParameter("email");
        }
        if (req.getParameter("phone") != null) {
            phone = req.getParameter("phone");
        }
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String dob = req.getParameter("dob");
        String sex = req.getParameter("sex");
        // Customer customer = CustomerDAO.createCustomer(name, email, phone, null,
        // false, password);
        Customer customer = new Customer(name, email, phone, null, false, password);
        // db here
        String json = gson.toJson(customer);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}

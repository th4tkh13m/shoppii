package controllers.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.ProductDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Filters;
import model.Product;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class GetProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        System.out.println(1);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        try {
            DBConnect db = new DBConnect();
            Connection connection = db.getConnection();
            String[] locations = req.getParameterValues("location");
            String[] categoriesId = req.getParameterValues("categoryId");
            String keyword = null, startPrice = null, endPrice = null, sort = null, location = null;
            int page = 1;
            int limit = 12;
            if (req.getParameter("keyword") != null) {
                keyword = req.getParameter("keyword");
            }
            System.out.println(categoriesId);
            if (req.getParameter("startPrice") != null) {
                startPrice = req.getParameter("startPrice");
            }
            if (req.getParameter("endPrice") != null) {
                endPrice = req.getParameter("endPrice");
            }
            if (req.getParameter("sort") != null) {
                sort = req.getParameter("sort");
            }
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            if (req.getParameter("limit") != null) {
                limit = Integer.parseInt(req.getParameter("limit"));
            }
            ;
            Filters filter = new Filters(keyword, sort, startPrice, endPrice,
                    locations, categoriesId, page, limit);
            System.out.println(filter.toString());

            HashMap<Integer, ArrayList<Product>> map = ProductDAO.getProducts(filter, connection);
            int totalPage = (int) map.keySet().toArray()[0];
            ArrayList<Product> products = map.get(totalPage);
            System.out.println(products);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("products", gson.toJsonTree(products).getAsJsonArray());
            jsonObject.addProperty("totalPage", totalPage);
           
            String rs = gson.toJson(jsonObject);
            // JsonObject jsonObject = new JsonObject();
            // jsonObject.addProperty("totalPage", totalPage);

            // JsonElement jsonElement = gson.toJsonTree(products);
            // jsonElement.getAsJsonArray().add;;
            // String json = gson.toJson(jsonElement);
            resp.setStatus(200);
            resp.getOutputStream().write(rs.getBytes("UTF-8"));
        } catch (Exception e) {
            // TODO: handle exception
            resp.setStatus(500);
            resp.getOutputStream().println(gson.toJson(new ErrorHandle(e.toString(),
                    500)));
        }

    }

}
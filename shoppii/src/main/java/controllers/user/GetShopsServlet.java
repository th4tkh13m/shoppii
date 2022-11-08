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
import com.google.gson.JsonObject;
import dao.ShopDAO;
import dbconnect.DBConnect;
import errors.ErrorHandle;
import model.Filters;
import model.Shop;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 1, // 1 MB
        maxRequestSize = 1024 * 1024 * 1 // 1 MB
)
public class GetShopsServlet extends HttpServlet {

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
            String keyword = null;
            int page = 1;
            int limit = 12;
            if (req.getParameter("keyword") != null) {
                keyword = req.getParameter("keyword");
            }
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            if (req.getParameter("limit") != null) {
                limit = Integer.parseInt(req.getParameter("limit"));
            }
            ;
            Filters filter = new Filters(keyword, locations, page, limit);

            HashMap<Integer, ArrayList<Shop>> map = ShopDAO.getShops(filter, connection);
            int totalPage = (int) map.keySet().toArray()[0];
            ArrayList<Shop> shops = map.get(totalPage);
            System.out.println(shops);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("shops", gson.toJsonTree(shops).getAsJsonArray());
            jsonObject.addProperty("totalPage", totalPage);

            String rs = gson.toJson(jsonObject);

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
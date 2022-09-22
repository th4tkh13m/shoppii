package net.codejava.aws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FileUploadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(gson.toJson("OK"));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // String description = request.getParameter("description");
        // System.out.println("Description: " + description);

        // Part filePart = request.getPart("file");

        // String fileName = getFileName(filePart);

        // System.out.println("File name = " + fileName);

        // String message = "";

        // try {
        // S3Util.uploadFile(fileName, filePart.getInputStream());
        // message = "The file has been uploaded successfully";
        // } catch (Exception ex) {
        // message = "Error uploading file: " + ex.getMessage();
        // }

        // request.setAttribute("message", message);
        // request.getRequestDispatcher("message.jsp").forward(request, response);

        Gson gson = new Gson();
        // String uri = request.getRequestURI();
        // BufferedReader json = request.getReader();
        // String line = null;
        // StringBuffer sb = new StringBuffer();
        // while ((line = json.readLine()) != null) {
        // sb.append(line);
        // }
        String desc = request.getParameter("desc");
        System.out.println(desc);
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().println(gson.toJson(desc));
        // response.getWriter().println(gson.toJson(desc));
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;

        return contentDisposition.substring(beginIndex, endIndex);
    }
}
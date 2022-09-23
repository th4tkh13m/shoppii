package net.codejava.aws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<String> listPhotos = S3Util.listPhotos("dao-test-2");
        String title = "test title";
        ProductExample productExample = new ProductExample(title, listPhotos);
        String json = gson.toJson(productExample);
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getOutputStream().println(json);

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
        String desc = request.getParameter("desc");
        String name = request.getParameter("name");
        List<Part> parts = (List<Part>) request.getParts();
        // Here to get id of the product for folder name
        String folderAWS = "dao-test-2";
        for (Part part : parts) {
            if (part.getName().equalsIgnoreCase("file")) {
                S3Util.uploadFile(folderAWS + "/" + part.getSubmittedFileName(), part.getInputStream());
            }
        }
        System.out.println(desc);
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        response.getOutputStream().println(gson.toJson("upload success"));
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;

        return contentDisposition.substring(beginIndex, endIndex);
    }
}
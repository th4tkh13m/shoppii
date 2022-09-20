package net.codejava.aws;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/upload")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*2, // 2MB
        maxFileSize = 1024*1024*10, // 10MB
        maxRequestSize = 1024*1024*11   // 11MB
        )
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    public FileUploadServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String description = request.getParameter("description");
        System.out.println("Description: " + description);
         
        Part filePart = request.getPart("file");
         
        String fileName = getFileName(filePart);
         
        System.out.println("File name = " + fileName);
 
        String message = "";
         
        try {
            S3Util.uploadFile(fileName, filePart.getInputStream());
            message = "The file has been uploaded successfully";
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }
         
        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
 
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;
         
        return contentDisposition.substring(beginIndex, endIndex);
    }
}
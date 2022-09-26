package net.codejava.aws;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
// @WebServlet("/delete")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*2, // 2MB
        maxFileSize = 1024*1024*10, // 10MB
        maxRequestSize = 1024*1024*11   // 11MB
        )
public class FileDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    public FileDeleteServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String objectName = request.getParameter("description");
         
        System.out.println("File name = " + objectName);
 
        String message = "";
         
        try {
            S3Util.deleteBucketObjects(objectName);
            message = "The file has been deleted successfully";
        } catch (Exception ex) {
            message = "Error deleting file file: " + ex.getMessage();
        }
         
        request.setAttribute("message", message);
        request.getRequestDispatcher("message.jsp").forward(request, response);
    }
 
}
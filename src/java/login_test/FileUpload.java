package login_test;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class FileUpload extends HttpServlet implements Serializable {
    private  String user_file_path ;
   String n;
   File f2;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   user_file_path=((String)request.getSession().getAttribute("file_path"));
         File Dir = new File(user_file_path);
         if(!Dir.exists())
        Dir.mkdir();

        //process only 1if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                      String  name = new File(item.getName()).getName();
                        item.write( new File(user_file_path + File.separator +name));
                       File f1= new File(user_file_path + File.separator +name);
                       
                       n=name;
                       f2=f1;
                    }
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully"+" "+n+" "+f2);
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
  //      System.out.println("u_name1 in servlet::: "+request.getSession().getAttribute("u_name"));
  //      System.out.println("u_name2 in servlet::: "+request.getParameter("u_name"));
  int x=new TestDAO().ins_file((String)request.getSession().getAttribute("u_name"),n);
  System.out.println("x = "+x);

        response.sendRedirect("login_process.jsp");
//HttpServletResponse.sendRedirect("/login_process.jsp");
        /*request.getRequestDispatcher("login_process.jsp").forward(request, response);
  String nextJSP = "/login_process.jsp";
RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
System.out.println("before dispatcher");
dispatcher.forward(request,response);   
System.out.println("after dispatcher");
  */  }
  
}
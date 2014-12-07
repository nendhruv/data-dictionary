<%@page import="java.sql.ResultSet"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="login_test.TestDAO"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.io.*" %>
 


<% /*
DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
       
        // constructs the directory path to store upload file
        // this path is relative to application's directory
         
       
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
 
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message",
                            "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
} 
 */
    
    
     System.out.println("request::: "+request);
   
    
    
    
String file_name;
String s=(String)session.getAttribute("u_name");
String p=(String)session.getAttribute("pwd");
String user_file_path=(String)session.getAttribute("file_path");
out.println("n input file"+request.getParameter("input_file" ));

if(request.getParameter("input_file")!=null)
{
    file_name=request.getParameter("input_file");
    String code=request.getParameter("t1");
    out.println("input file:"+file_name) ;   
 File Dir = new File(user_file_path);
 if(!Dir.exists())
 Dir.mkdir();
    PrintWriter o = new PrintWriter("D:/minor_final/user_files/"+ s +"/"+file_name+".txt");
    o.println(code);
    o.close();
int x=new TestDAO().ins_file(s,file_name);
  System.out.println("x = "+x);

}
response.sendRedirect("login_process.jsp");

/*
if(request.getParameter("upload_file")!= null)
{
//    System.out.println("nn uploaded file:::: "+request.getParameter("upload_file"));
   
    
    file_name=request.getParameter("upload_file");
    out.println("uploaded file:"+file_name) ; 
    String path="d:/minor_final/user_files";
    MultipartRequest m1= new MultipartRequest(request,path);  
    
    
    out.print("successfully uploaded");  
int x=new TestDAO().ins_file(s,file_name);
  System.out.println("x = "+x);

}
response.sendRedirect("login_process.jsp");
*/
%>










<%

 // New location to be redirected
   /*String site = new String("login_process.jsp");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", site); 
*/

/*
String u_name=request.getParameter("u_name");
out.println("u_name:"+u_name+"<br>");
String code=request.getParameter("t1");
out.println("code submitted:"+code+"<br>");


PrintWriter o = new PrintWriter(u_name+".txt");
o.println(code);
o.close();

File file=new File(u_name+".txt");

String path = file.getAbsolutePath();

out.println("path:-"+path+"<br>");
out.println("file created!!!!!!!!");
*/
//  out.println("file name::"+ file_name );
//int x=new TestDAO().ins_file(s,file_name);
//  System.out.println("x = "+x);
%>

       <% /* <jsp:useBean id="obj1" class="login_test.FileBean" scope="request">
            <jsp:setProperty name="obj1" property="u_name" value="s"></jsp:setProperty>
            <jsp:setProperty name="obj1" property="file" value="def"></jsp:setProperty>
        </jsp:useBean>
*/%>

  





<!--<form action="gen_dic.jsp">

<input type="hidden" name="u_name" value="<%//=u_name%>">
 <input type="submit" value="gen_dictionary"/></center>

</form>-->
<br>
<a href="login_process.jsp">Return</a>
<a href="gen_dic.jsp">GENERATE DICTIONARY</a>


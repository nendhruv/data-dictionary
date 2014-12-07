<%-- 
    Document   : newjsp
    Created on : 01-Dec-2014, 12:55:46
    Author     : user
--%>

<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%            
          System.out.println("nnnn uploaded file::::"+request.getParameter("upload_file"));
            out.println("nnnn uploaded file::::"+request.getParameter("upload_file"));
  String path="d:/test_new";
MultipartRequest m1= new MultipartRequest(request,path);  
            %>        
        <h1>Hello World!</h1>
    </body>
</html>

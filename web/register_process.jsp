<%@page import="login_test.UserBean"%>
<%@page import="login_test.TestDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
  <link rel="stylesheet" type="text/css" href="bower/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="bower/css/styles.css">
  <link href='http://fonts.googleapis.com/css?family=Fredoka+One|Sigmar+One|Bree+Serif' rel='stylesheet' type='text/css'>
 
  <link rel="stylesheet" type="text/css" href="bower/css/codemirror.css">
<script type="text/javascript" src="bower/angular/codemirror.js"></script>
</head>
    <body>
        
        <jsp:useBean id="obj" class="login_test.UserBean" scope="request">
            <jsp:setProperty name="obj" property="f_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="l_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="u_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="pwd"></jsp:setProperty>
        </jsp:useBean>



<header class="head-shadow">
 <div class="row">
  <div class="col-sm-2 col-sm-offset-1">
   <img src="img/logo.png">
  </div>
 </div>
     
     

</header>

<section class="col-sm-9 col-sm-offset-3">
    <%            int x=new TestDAO().ins_data(obj);
       // out.println(obj.f_name);
      //  String ff_name=request.getParameter(f_name);
      //  out.println("request:::::: "+request);
//        request.getParameter(f_name);
    //    out.println("x="+x);
            if(x!=0)
            out.println("<br/><br/><div class=\"row ok-done\"><div class=\"col-sm-4 col-sm-offset-3\">Your account has been created.<br>Thanks for registering!</div></div><br/><br/><form action=\"login_process.jsp\" method=\"POST\"><div class=\"row\"><div class=\"col-sm-offset-3 col-sm-4 login-marg\"><input placeholder=\"username\" name=\"u_name\" class=\"login-inp\" type=\"text\" /></div></div><div class=\"row\"><div class=\"col-sm-4 col-sm-offset-3 login-marg\"><input placeholder=\"password\" name=\"pwd\" class=\"login-inp\" type=\"password\" /></div></div><div class=\"row\"><div class=\"col-sm-1 col-sm-offset-4 login-marg\"><button class=\"login-btn\">Log</button></div></form>");
            else 
            out.println("Sorry ...your data has not been saved! <center>         <br/><br/><br/><a href=\"index.html\" >Return to Login</a></center>");
                      
%>
    
</section>

   
    
    </body>
</html>

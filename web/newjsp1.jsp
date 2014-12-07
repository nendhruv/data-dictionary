<%-- 
    Document   : newjsp1
    Created on : 04-Dec-2014, 15:47:19
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <header>
 <div class="row">
  <div class="col-sm-2 col-sm-offset-1">
   <img src="img/logo.png">
  </div>
     <form action="register_process.jsp" method="POST">
     <div class="col-sm-offset-3 col-sm-2 login-marg">
         <input placeholder="username" name="u_name" class="login-inp" type="text" />
     </div>
     
     
     <div class="col-sm-2 login-marg">
         <input placeholder="password" name="pwd" class="login-inp" type="password" />
     </div>
     
     <div class="col-sm-1 login-marg">
         <button class="login-btn">Log</button>
     </div>
     </form>
 </div>

</header>

    </body>
</html>

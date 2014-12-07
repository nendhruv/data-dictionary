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
            <jsp:setProperty name="obj" property="u_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="pwd"></jsp:setProperty>
            <jsp:setProperty name="obj" property="f_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="l_name"></jsp:setProperty>
  </jsp:useBean>

  <%
     String referer_name= request.getHeader("Referer");
   //  out.println ( "you came from: " + request.getHeader("Referer") +"<br>");
     boolean t=referer_name.contains("login_process.jsp");
    boolean y=referer_name.contains("FileUpload");
  //  out.println("yb="+y);
     //out.println("obj u_name test:::: "+obj.getU_name());
   //   out.println("before::::::   "+req);
      //out.println("session get u_name::::: "+session.getAttribute("u_name));
      boolean x=new TestDAO().validate(obj);
  if(x||t/*||((String)session.getAttribute("a")).equalsIgnoreCase("hello")*/)
      {
         // out.println("<br><br>hello equals::::::: "+session.getAttribute("a").equals("hello"));
          
       //   out.println("<br><br>validate::::::: "+x);
        //  out.println("Servlet info:::::: "+new FileUpload().getServletInfo());
          HttpSession ssn=request.getSession(true);
         //out.println("\n after session::: "+session+"\n");
 //out.println("Attribute names:::: "+session.getAttributeNames());
         String id=ssn.getId();
         if(t==false)
         {
          session.setAttribute("u_name", obj.getU_name());
          session.setAttribute("pwd", obj.getPwd());
          session.setAttribute("a","hello");
          session.setAttribute("file_path", "D:/minor_final/user_files/"+obj.getU_name()+"/");
          session.setAttribute("dictionary_path","D:/minor_final/dictionary_files/"+obj.getU_name()+"/");
         }
String u_name=  (String)session.getAttribute("u_name");
//out.println("Hello  "+u_name);
         // out.println("Hello  "+obj.getPwd());
          
   //       out.println("id:"+id);
          //response.sendRedirect("input_code.jsp");
          
 ResultSet rs=new TestDAO().showFiles((String)session.getAttribute("u_name"));
 //out.println("new abcd");
 //System.out.println("rs received:::: "+rs);
 //out.println("file 222::::: "+rs.getString("file"));
//out.println(rs);
/* while(rs.next())  
 {
 out.println("file111::: "+rs.getString("file")+"<br>");
 //out.println("abcd");
 }*/        
 
 
%>
<header class="head-shadow">
 <div class="row">
  <div class="col-sm-2 col-sm-offset-1">
   <img src="img/logo.png">
  </div>
     <div class="col-sm-2 col-sm-offset-5">
         <span class="user-name"> Hello, <%= u_name %></span>
     </div>
     
     <div class="col-sm-2">
       <a href="logout.jsp">Logout</a>
     </div>
 </div>
     
     

</header>

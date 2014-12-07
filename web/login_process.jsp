
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 
<%@ page  import="java.util.*,java.sql.*" %>
<%@page import="login_test.UserBean" %>
<%@page import="login_test.TestDAO" %>
<%@page import="login_test.FileUpload" %>
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
//   out.println ( "you came from: " + request.getHeader("Referer") +"<br>");
     boolean t=referer_name.contains("login_process.jsp");
      boolean k=referer_name.contains("compare2.jsp");
      boolean z=referer_name.contains("gen_dic.jsp");
    boolean y=referer_name.contains("FileUpload");
  //  out.println("yb="+y);
     //out.println("obj u_name test:::: "+obj.getU_name());
   //   out.println("before::::::   "+req);
      //out.println("session get u_name::::: "+session.getAttribute("u_name));
      boolean x=new TestDAO().validate(obj);
  if(x||t||k||z/*||((String)session.getAttribute("a")).equalsIgnoreCase("hello")*/)
      {
         // out.println("<br><br>hello equals::::::: "+session.getAttribute("a").equals("hello"));
          
       //   out.println("<br><br>validate::::::: "+x);
        //  out.println("Servlet info:::::: "+new FileUpload().getServletInfo());
          HttpSession ssn=request.getSession(true);
         //out.println("\n after session::: "+session+"\n");
 //out.println("Attribute names:::: "+session.getAttributeNames());
         String id=ssn.getId();
         if(t==false&&k==false&&z==false)
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
     <div class="col-sm-2 col-sm-offset-5 marg2">
         <span class="hello">Hello,</span> <span class="user-name"> <%= u_name %></span>
     </div>
     
     <div class="col-sm-1 col-sm-offset-1 log-out">
       <span class="glyphicon glyphicon-share-alt"></span>  <a href="logout.jsp">Logout</a>
     </div>
 </div>
     
     
<%
         // request.getRe;
%>
</header>
    
     <div class="row marg2">   
         <aside class="col-sm-3">
         
         <div>
     
     <ul class="user-files col-sm-12">
        <!--<li ng-repeat="file in files | filter:query" class="files">
            <span class="glyphicon glyphicon-book file-icon"></span><span><b>{{file.name}}</b></span><br>
          <span class="file-date">Uploaded on: {{file.date}}</span><span class="glyphicon glyphicon-trash trash"></span>
         </li>-->
        
        <li class="gen-box">
            
            Enter File to Generate Dictionary or <a class="comp" href="compare1.jsp">Compare</a> two files.<br/>
                <form class="row" action="gen_dic.jsp" method="post" >
 <input type="text" class="gen-input col-sm-9 inh" name="gen_dic_file"/>
 <button type="submit" class="col-sm-3 gen-btn inh"><span class='glyphicon glyphicon-collapse-up'></span></button>
</form> 
<br/><br/>
       </li>
            <% 
                  String[] a=new String[200];
 //String glyph ="glyphicon glyphicon-list-alt";
 int i=0;
 while(rs.next())
 {
     a[i++]=(rs.getString("file"));
 }
 for(String a1:a)
 {
     if(a1!=null)

       out.println("<li><span class=\"glyphicon glyphicon-list-alt marg\"></span> "   +a1+"</li>");
 

}      %>
       

     </ul>
     



      <div class="col-sm-12 zero-pads">
       
  
      </div>

    </div>
    </aside>
        <section class="col-sm-9">

           
<form action="./receiver.jsp"  method="post" class="row">
  
 

  <div  id="coder"></div>
  <textarea  id="myTextArea" rows="20" cols="50" name="t1">
  </textarea><br/>
  <div class="row">
  <input type="text" name="input_file" class="gen-files col-sm-6 col-sm-offset-3" placeholder=" File name"><br><br>
  </div> 
  <button class="dictionary-btn col-sm-3 col-sm-offset-5" type="submit">Save File</button>     
  </form>
       
            <br/>
            <div class="row">
            <div class="col-sm-3 col-sm-offset-6 or">OR</div>
            </div>
            <br/>
            <div class="row">
<form action="FileUpload" enctype="multipart/form-data" method="post" class="col-sm-3 col-sm-offset-5">
  <input type="file" name="file" class="custom-file-input"/><br>
  <input type="hidden" name="u_name" value=<%=(String)session.getAttribute("u_name")%>>
  <button type="submit" class="upload-btn"><span class="glyphicon glyphicon-open"></span></button>
</form>
</div>
     </section>
     </div>
    <%               
    
         //out.println("Hello"+obj.getU_name());
    //      response.setHeader("sid",id);
       //   response.sendRedirect("input_code.jsp");
          //session.setAttribute("session","TRUE"); 
          //out.println("session"+session);
    %>
      
      
          
     <%
}else
      {
          out.println("<center>Invalid email or password</center><br>");
          out.println("<center><a href='index.html'>Return to login</a></center>");

      }
  
  %>
  
   <script>
       var codemirror = document.getElementById("coder");
    var myCodeArea =document.getElementById(myTextArea);
//var myCodeMirror = CodeMirror.fromTextArea(myCodeArea);

var myCodeMirror = CodeMirror.fromTextArea(myTextArea, {value: myTextArea.innerHTML, lineNumbers: true, theme: 'paraiso-light'});
            </script>
    </body>
  </html>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 
<%@ page  import="java.util.*,java.sql.*" %>
<%@page import="login_test.UserBean" %>
<%@page import="login_test.TestDAO" %>
<%@page import="login_test.FileUpload" %>
<html>
    <body>
  <jsp:useBean id="obj" class="login_test.UserBean" scope="request">
            <jsp:setProperty name="obj" property="u_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="pwd"></jsp:setProperty>
            <jsp:setProperty name="obj" property="f_name"></jsp:setProperty>
            <jsp:setProperty name="obj" property="l_name"></jsp:setProperty>
  </jsp:useBean>

  <%
    obj.
     String referer_name= request.getHeader("Referer");
     out.println ( "you came from: " + request.getHeader("Referer") +"<br>");
     boolean t=referer_name.contains("login_process.jsp");
    boolean y=referer_name.contains("FileUpload");
    out.println("yb="+y);
     out.println("obj u_name test:::: "+obj.getU_name());
   //   out.println("before::::::   "+req);
      //out.println("session get u_name::::: "+session.getAttribute("u_name));
      boolean x=new TestDAO().validate(obj);
  if(x||t/*||((String)session.getAttribute("a")).equalsIgnoreCase("hello")*/)
      {
         // out.println("<br><br>hello equals::::::: "+session.getAttribute("a").equals("hello"));
          
          out.println("<br><br>validate::::::: "+x);
        //  out.println("Servlet info:::::: "+new FileUpload().getServletInfo());
          HttpSession ssn=request.getSession(true);
         out.println("\n after session::: "+session+"\n");
 out.println("Attribute names:::: "+session.getAttributeNames());
         String id=ssn.getId();
         if(t==false)
         {
          session.setAttribute("u_name", obj.getU_name());
          session.setAttribute("pwd", obj.getPwd());
          session.setAttribute("a","hello");
          session.setAttribute("file_path", "D:/minor_final/user_files/"+obj.getU_name()+"/");
          session.setAttribute("dictionary_path","D:/minor_final/dictionary_files/"+obj.getU_name()+"/");
         }out.println("Hello  "+(String)session.getAttribute("u_name"));
         // out.println("Hello  "+obj.getPwd());
          
          out.println("id:"+id);
          //response.sendRedirect("input_code.jsp");
          
 ResultSet rs=new TestDAO().showFiles((String)session.getAttribute("u_name"));
 out.println("new abcd");
 System.out.println("rs received:::: "+rs);
 //out.println("file 222::::: "+rs.getString("file"));
 while(rs.next())  
 {
 out.println("file111::: "+rs.getString("file")+"<br>");
 //out.println("abcd");
 }        
          
%>
<<form action="gen_dic.jsp" method="post" >
Enter name of file for which dictionary to be generated<input type="text" name="gen_dic_file"/>
<input type="submit" value="generate dictionary"/>
</form>

<center><h1>DATA DICTIONARY</h1></center>
<%
      
%>
<center><form action="./receiver.jsp"  method="post">
  
  <br><br>
  File name<input type="text" name="input_file" ><br><br>
  <textarea rows="20" cols="50" name="t1">
  </textarea><br/>
  <input type="submit" value="submit"/>      
  </form>
<form action="FileUpload" enctype="multipart/form-data" method="post">
  Select file <input type="file" name="file" /><br>
  <input type="hidden" name="u_name" value=<%=(String)session.getAttribute("u_name")%>>
  <input type="submit" value="submit_new"/>
</form></center>
  <a href="logout.jsp">Logout</a>
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

<heady class="rest-head"></heady>

<div class="">
<div class="row">
  <div class="col-sm-3 file-col">


  <files></files>
 

</div>


   <div class="col-sm-9" ng-if="editor">
     
     <div class="row">
      <div class="col-sm-12" style="background-color: #ecf0d6; height:330px;">

       <ui-codemirror ui-codemirror-opts="editorOptions" ng-model="code" ui-refresh='isSomething' style="padding:10%;"></ui-codemirror>

      </div>

     </div>


     <div class="row">

      <div class="col-sm-4 col-sm-offset-4">

     <button class="dictionary-btn" ng-click="editor()">Generate Dictionary</button>
   </div>
 </div>
  </div>


  <div class="col-sm-9" ng-if="!editor">
    <div class="row">
    <div class="col-sm-12">
 <table border="1" class="col-sm-3 col-sm-offset-3">
  <tr>
    <th>Var</th>
    <th>Val</th>
  </tr>
  <tr>
    <td>i</td>
    <td>0</td>
  </tr>
  <tr>
    <td>l</td>
    <td>9</td>
  </tr>
  <tr>
    <td>k</td>
    <td>30</td>
  </tr>

</table>


 <table border="1" class="col-sm-3 col-sm-offset-1">
  <tr>
    <th>function</th>
    <th>return type</th>
  </tr>
  <tr>
    <td>get_value</td>
    <td>int</td>
  </tr>
  <tr>
    <td>find_route</td>
    <td>string</td>
  </tr>
  <tr>
    <td>day_bill</td>
    <td>List</td>
  </tr>

</table>

    </div>

    </div>


      <div class="row">

      <div class="col-sm-4 col-sm-offset-4">

     <button class="dictionary-btn" ng-click="editor()">Download Dictionary</button>
   </div>
 </div>

  </div>




</div>

</div>

</body>
</html>

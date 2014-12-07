<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="login_test.TestDAO" %>
<%@page import="login_test.UserBean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.html' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <jsp:useBean id="obj" class="login_test.UserBean" scope="request">
      <jsp:getProperty name="obj" property="l_name"></jsp:getProperty>
      <jsp:getProperty name="obj" property="u_name"></jsp:getProperty> 
  </jsp:useBean>

  <%
      String[][] arr = new String[2][2];

      out.println("Hello::   "+obj.getU_name());
//arr[0][0]=obj.getF_name();arr[0][1]=obj.getL_name();
/*for(int i=0;i<2;i++)
{
    for(int j=0;j<2;j++)
      {
        System.out.println("a::: "+arr[i][j]+"\n");
    }
    
}*/
      // String sid=response.getHeader("sid");
     // out.println("sid:::"+sid);
      //String sid=request.getParameter(id);
  %>
  <body>
  <center><h1>DATA DICTIONARY</h1></center>
  <form action="./receiver.jsp">
  <center>
  <br><br>
   File name<input type="text" name="u_name"><br><br>
   <textarea rows="20" cols="50" name="t1">  </textarea><br/>
 <input type="submit" value="submit"/></center>
  </form>
  
  <a href="logout.jsp">Logout</a>
    
  </body>
</html>

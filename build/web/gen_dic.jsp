<%-- 
    Document   : gen_dic
    Created on : 01-Nov-2014, 00:04:31
    Author     : user
--%>

<%@page import="java.io.File"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.List"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="diction.func"%>
<%@page import="diction.func_test"%>
<%@page import="diction.Var"%>
<%@page import="diction.Var_test"%>
<%@page import="java.util.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <a href="login_process.jsp" ><h1>HOME</h1></a> 
  <%  
      new func_test();
      String d_name=(String)request.getParameter("gen_dic_file");
      String dic_name=session.getAttribute("file_path")+"/" + (String)request.getParameter("gen_dic_file");
     Var.getF1().removeAll(Var.getF1());
     func.getF1().removeAll(func.getF1());

      System.out.println("dic_name :::"+dic_name); 
      diction.Var_test v=new diction.Var_test();
                List<String> li= v.fetch(dic_name);
                System.out.println("li::::"+li.toString());
                
                v.functionality(li);
       diction.func_test fu1=new diction.func_test();              
                List<String> li1= fu1.fetch(dic_name);
               fu1.functionality(li1);
               String dictionary_path = (String)session.getAttribute("dictionary_path");
/*
 *  File Dir = new File(user_file_path);
 if(!Dir.exists())
 Dir.mkdir();
    PrintWriter o = new PrintWriter("D:/minor_final/user_files/"+ s +"/"+file_name+".txt");
    o.println(code);
    o.close();

* 
* +d_name+"_dic.txt"
 */
              File Dir = new File(dictionary_path);
                 if(!Dir.exists())
                    Dir.mkdir();
 
          /*    System.out.println("parent file::::: "+f.getParentFile());
              System.out.println("parent ::::: "+f.getParent());
              f.getParentFile().mkdirs();
              f.createNewFile();
          */    
             String  path=dictionary_path+d_name+"_dic.txt";
              v.write(path);
               fu1.write(path);
                String s=null;
                BufferedReader br1 = new BufferedReader(new FileReader("D:/minor_final/dictionary_files/"+((String)session.getAttribute("u_name"))+"/"+d_name+"_dic.txt"));
		 /*  while ((s = br1.readLine()) != null)
                   {
                     out.print("s:  "+s+"<br>");
                   }*/
                
   out.println("<center><table border='3' cellpadding='10' >");
String p=null;
int x=0;
while ((s = br1.readLine()) != null) 
			    /*&&(br1.readLine().startsWith("functions:")))*/
{
    out.println("<tr>" );
    //out.println("s::::: "+s);
    String[] data = s.split(",");

    for (String val : data)
    {
        
        if(x==0)
            out.println("<th><center><h1>" +val + "</h1><center></th>");
        else if(val.equals("Functions:"))
            out.println("<th><center><h1>" +val + "</h1><center></th>");
       /* String[] data1=val.split(",");
        for(String a:data1)
        {
            out.println("a:::: "+a);
        }*/
        else
          out.println("<td >" +val + "</td>");
        //out.println("val:::: "+val);
    }

    out.println("</tr>" );
    x++;
}
//Var.getF1().clear();
//Var_test.get;
//Var.getF1().
out.println("</table><center>");
           /*
            * out.println("<table>")

for (int i=0; i<lines.length; ++i) {
    out.println("<tr>" )
    String[] data = line[i].split(",");

    for (String val : data) {
        out.println("<td>" + val + "</td>")
    }

    out.println("</tr>" )
}

out.println("</table>")
            */
  %>
    </body>
</html>

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import diction.func;
import diction.func_test;
import diction.Var;
import diction.Var_test;
import java.util.*;

public final class gen_005fdic_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Hello World!</h1>\n");
      out.write("  ");
  String d_name=(String)request.getParameter("gen_dic_file");
      String dic_name=session.getAttribute("file_path")+"/" + (String)request.getParameter("gen_dic_file");
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
		   while ((s = br1.readLine()) != null)
                   {
                     out.print("s:  "+s+"<br>");
                   }
           
  
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

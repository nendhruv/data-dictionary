package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import login_test.TestDAO;
import java.sql.Connection;
import java.io.*;

public final class receiver_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write(" \n");
      out.write("\n");
      out.write("\n");
 
    
if(request.getParameter("upload_file")==null)
{
    String file_name=request.getParameter("input_file");
    String code=request.getParameter("t1");
    out.println("input file:"+file_name) ;   
 
    PrintWriter o = new PrintWriter(file_name+".txt");
    o.println(code);
    o.close();

}

else
{   String file_name=request.getParameter("upload_file");
  out.println("uploaded file:"+file_name) ;   
}

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
int g=0;

      out.write("  \n");
      out.write("        ");
      login_test.UserBean obj = null;
      synchronized (request) {
        obj = (login_test.UserBean) _jspx_page_context.getAttribute("obj", PageContext.REQUEST_SCOPE);
        if (obj == null){
          obj = new login_test.UserBean();
          _jspx_page_context.setAttribute("obj", obj, PageContext.REQUEST_SCOPE);
          out.write("\n");
          out.write("        ");
          out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((login_test.UserBean)_jspx_page_context.findAttribute("obj")).getU_name())));
          out.write("\n");
          out.write("        ");
        }
      }
      out.write("\n");
      out.write("        ");
      login_test.FileBean obj1 = null;
      synchronized (request) {
        obj1 = (login_test.FileBean) _jspx_page_context.getAttribute("obj1", PageContext.REQUEST_SCOPE);
        if (obj1 == null){
          obj1 = new login_test.FileBean();
          _jspx_page_context.setAttribute("obj1", obj1, PageContext.REQUEST_SCOPE);
          out.write("\n");
          out.write("            ");
          org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("obj1"), "u_name",
obj.getU_name());
          out.write("\n");
          out.write("            ");
          org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("obj1"), "file", "abc", null, null, false);
          out.write("\n");
          out.write("            ");
          org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("obj1"), "file_id",
++g);
          out.write("\n");
          out.write("        ");
        }
      }
      out.write('\n');
      out.write('\n');

    int x=new TestDAO().ins_file(obj1);
    System.out.println("x = "+x);

      out.write("\n");
      out.write("\n");
      out.write("<!--<form action=\"gen_dic.jsp\">\n");
      out.write("\n");
      out.write("<input type=\"hidden\" name=\"u_name\" value=\"");
//=u_name
      out.write("\">\n");
      out.write(" <input type=\"submit\" value=\"gen_dictionary\"/></center>\n");
      out.write("\n");
      out.write("</form>-->\n");
      out.write("<br>\n");
      out.write("<a href=\"gen_dic.jsp\">GENERATE DICTIONARY</a>");
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

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import login_test.TestDAO;
import login_test.UserBean;

public final class input_005fcode_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=ISO-8859-1");
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

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\n");
      out.write("    \n");
      out.write("    <title>My JSP 'index.html' starting page</title>\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\n");
      out.write("\t<!--\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\n");
      out.write("\t-->\n");
      out.write("  </head>\n");
      out.write("  \n");
      out.write("  ");
      login_test.UserBean obj = null;
      synchronized (request) {
        obj = (login_test.UserBean) _jspx_page_context.getAttribute("obj", PageContext.REQUEST_SCOPE);
        if (obj == null){
          obj = new login_test.UserBean();
          _jspx_page_context.setAttribute("obj", obj, PageContext.REQUEST_SCOPE);
          out.write("\n");
          out.write("      ");
          out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((login_test.UserBean)_jspx_page_context.findAttribute("obj")).getL_name())));
          out.write("\n");
          out.write("      ");
          out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((login_test.UserBean)_jspx_page_context.findAttribute("obj")).getF_name())));
          out.write(" \n");
          out.write("  ");
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("  ");

      String[][] arr = new String[2][2];

      out.println("Hello   "+obj.getF_name());
arr[0][0]=obj.getF_name();arr[0][1]=obj.getL_name();
for(String s[]:arr)
{
        for(String a:s)
        {
            System.out.println(a);
        }
}
      // String sid=response.getHeader("sid");
     // out.println("sid:::"+sid);
      //String sid=request.getParameter(id);
  
      out.write("\n");
      out.write("  <body>\n");
      out.write("  <center><h1>DATA DICTIONARY</h1></center>\n");
      out.write("  <form action=\"./receiver.jsp\">\n");
      out.write("  <center>\n");
      out.write("  <br><br>\n");
      out.write("  User name<input type=\"text\" name=\"u_name\"><br><br>\n");
      out.write("\n");
      out.write("  <textarea rows=\"20\" cols=\"50\" name=\"t1\">\n");
      out.write("  </textarea><br/>\n");
      out.write(" <input type=\"submit\" value=\"submit\"/></center>\n");
      out.write("  </form>\n");
      out.write("  \n");
      out.write("  <a href=\"logout.jsp\">Logout</a>\n");
      out.write("    \n");
      out.write("  </body>\n");
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

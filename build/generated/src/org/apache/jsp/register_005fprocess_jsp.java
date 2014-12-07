package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import login_test.UserBean;
import login_test.TestDAO;

public final class register_005fprocess_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");
      login_test.UserBean obj = null;
      synchronized (request) {
        obj = (login_test.UserBean) _jspx_page_context.getAttribute("obj", PageContext.REQUEST_SCOPE);
        if (obj == null){
          obj = new login_test.UserBean();
          _jspx_page_context.setAttribute("obj", obj, PageContext.REQUEST_SCOPE);
          out.write("\n");
          out.write("            ");
          org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("obj"), "f_name", request.getParameter("f_name"), request, "f_name", false);
          out.write("\n");
          out.write("            ");
          org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("obj"), "l_name", request.getParameter("l_name"), request, "l_name", false);
          out.write("\n");
          out.write("        ");
        }
      }
      out.write('\n');
            int x=new TestDAO().ins_data(obj);
       // out.println(obj.f_name);
      //  String ff_name=request.getParameter(f_name);
        out.println("request:::::: "+request);
//        request.getParameter(f_name);
        out.println("x="+x);
            if(x!=0)
            out.println("<center>Your account has been created.<br>Thanks for registering!</center>");
            else 
            out.println("Sorry ...your data has not been saved!");
            
      out.write("\n");
      out.write("    <center>         <br/><br/><br/><a href=\"index.html\" >Return to Login</a></center>\n");
      out.write("    \n");
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

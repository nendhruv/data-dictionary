<%-- 
    Document   : reciever2.jsp
    Created on : 4 Dec, 2014, 1:33:24 PM
    Author     : SHUBHANSHU
--%>

<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="filediff.DiffUtils"%>
<%@page import="filediff.Diff"%>
<%@page import="filediff.Patch"%>
<%@page import="minortest.Minortest"%>
<%@page import="login_test.*"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        
   <% 
       
       String s=(String)session.getAttribute("u_name");
       System.out.println("s new new :::::::: "+s);
       
     String file_name1 = request.getParameter("input_file1");
     String file_name2 = request.getParameter("input_file2");
    String code1=request.getParameter("t1");
    String code2=request.getParameter("t2");
    out.println("input file1:"+file_name1+"<br>");  
    out.println("input file2:"+file_name2+"<br>");
    
    String files_dir= "D:/minor_final/user_files/"+s+"/";
   String comp_dir="D:/minor_final/compare/"+s+"/";
    File f1=new File(files_dir);
 if(!f1.exists())
    f1.mkdir();
   
 
 PrintWriter o = new PrintWriter(files_dir+file_name1+".txt");
    o.println(code1);
    o.close();
    PrintWriter o1 = new PrintWriter(files_dir+file_name2+".txt");
    o1.println(code2);
    o1.close();
    
                List<String> original = Minortest.fileToLines(files_dir+file_name1+".txt");
                List<String> revised  = Minortest.fileToLines(files_dir+file_name2+".txt");
                
                // Compute diff. Get the Patch object. Patch is the container for computed deltas.
                Patch patch = DiffUtils.diff(original, revised);

                for (Diff delta: patch.getDeltas()) 
                {
                    out.println(delta+"<br>");
                }
 File f2=new File(comp_dir);
 if(!f2.exists())
     f2.mkdir();
 String comp_file_name=file_name1+"_"+file_name2+"_compare.txt";
  String comp_file_path=comp_dir+comp_file_name;
       out.println("comp_file_path::::: "+comp_file_path);
       
       File file = new File(comp_file_path);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
		 String sgh=null;
       for (Diff delta: patch.getDeltas()) 
                       
                       
                {
                   sgh= delta.toString();
                //    out.println("sgh::::: "+sgh);
                //    lis.add(sgh);
       	bw.write(sgh+"\n");
	        }
      bw.close();
       
                        
int x=new TestDAO().ins_file(s,file_name1);
 System.out.println("x = "+x);

int y=new TestDAO().ins_file(s,file_name2);

              
int comp=new TestDAO().ins_compared_file(s,file_name1,file_name2,comp_file_path);



   
   %>
   <a href="login_process.jsp" >HOME</a>
    </body>
</html>

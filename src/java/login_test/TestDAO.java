/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login_test;
import java.sql.*;
import login_test.UserBean;
import login_test.FileBean;
/**
 *
 * @author user
 */
public class TestDAO {
 private Connection con=null;
 public TestDAO()
         {
          
             try
             {
                   Class.forName("com.mysql.jdbc.Driver");
//         System.out.println("con1:::"+con);
                   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
   //             System.out.println("con2:::"+con);  
                   System.out.println("connection established successfully");
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
         }

public int ins_data(UserBean t1)
{
int x=0;
try
{
    System.out.println("con:::"+con);
    String query="insert into user values(?,?,?,?)";
    PreparedStatement ps=con.prepareStatement(query);
    ps.setString(1,t1.getF_name());  
    ps.setString(2,t1.getU_name());
    ps.setString(3,t1.getPwd());
    ps.setString(4,t1.getL_name());
    
    System.out.println("testing 123");
    x=ps.executeUpdate();
    System.out.println("x====== "+x);
System.out.println("successs");
}
catch(Exception e)
{
    e.printStackTrace();
}   
return x;

}

public boolean validate(UserBean t1)
{   
   boolean status=false;
    try
    {
        String query="select * from user where u_name=? and pwd=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,t1.getU_name());  
        ps.setString(2,t1.getPwd());  
              
        ResultSet rs=ps.executeQuery();  
        System.out.println(rs);
        status=rs.next();  
              

    }
    catch(Exception e)
   {
       e.printStackTrace();
    }
    return status;
    
}
public int ins_file(String u_name,String file_name)
{
    int x=0;
    try
    {
          String query="insert into file(u_name,file) values(?,?)";
          PreparedStatement ps=con.prepareStatement(query);
          ps.setString(1,u_name);  
          ps.setString(2,file_name);
           x=ps.executeUpdate();
       System.out.println("successs");
    
    }
    catch(Exception e)
    {
        System.out.println("file error::: ");
        e.printStackTrace();
    }
    return x;
}

public ResultSet showFiles(String u_name)
{
   boolean status=false;
     ResultSet rs=null;
    try
    {
        String query="select file from file where u_name=? ";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,u_name);  
       rs=ps.executeQuery();  
       System.out.println("rs sent::::: "+rs);
   //     while(rs.next())
    //   System.out.println("file:::::     "+rs.getString("file"));
   //     status=rs.next();  
     
 //       System.out.println("status:::::   "+status);
  // rs.next();
       return rs;
        }
    catch(Exception e)
   {
       e.printStackTrace();
   }
    return rs;
//    return status;
    
}


public int ins_compared_file(String u_name,String file_name1,String file_name2,String comp_file)
{
    int x=0;
    try
    {
          String query="insert into compare values(?,?,?,?)";
          PreparedStatement ps=con.prepareStatement(query);
          ps.setString(1,u_name);  
          ps.setString(2,file_name1);  
          ps.setString(3,file_name2);  
          ps.setString(4,comp_file);
           x=ps.executeUpdate();
       System.out.println("successs");
    
    }
    catch(Exception e)
    {
        System.out.println("file error::: ");
        e.printStackTrace();
    }
    return x;
}


}
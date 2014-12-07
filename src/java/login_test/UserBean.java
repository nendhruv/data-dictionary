/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login_test;
import java.io.*;
/**
 *
 * @author user
 */
public class UserBean implements Serializable {
private String f_name;
private String u_name;
private String pwd;
private String l_name;


    public String getF_name() {
        System.out.println(f_name);
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
   }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void showAll()
    {
        System.out.println("u_name:::: "+this.f_name);
        System.out.println("u_name:::: "+this.l_name);
        System.out.println("u_name:::: "+this.pwd);
        System.out.println("u_name:::: "+this.u_name);
    }

}

package diction;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class func 
{

    /**
     * @return the f1
     */
    public static List<func> getF1() {
        return f1;
    }

    /**
     * @param aF1 the f1 to set
     */
    public static void setF1(List<func> aF1) {
        f1 = aF1;
    }
    
private String mod;
private String stat;
private String ret_type;
private String f_name;
private String arg;
private static List<func> f1=null;    
static
{
        setF1((List<func>) new ArrayList());
}


    public static void main(String arg[])
    {
        
    }

    /**
     * @return the mod
     */
    public String getMod() {
        return mod;
    }

    /**
     * @param mod the mod to set
     */
    public void setMod(String mod) {
        this.mod = mod;
    }

    /**
     * @return the stat
     */
    public String getStat() {
        return stat;
    }

    /**
     * @param stat the stat to set
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    /**
     * @return the ret_type
     */
    public String getRet_type() {
        return ret_type;
    }

    /**
     * @param ret_type the ret_type to set
     */
    public void setRet_type(String ret_type) {
        this.ret_type = ret_type;
    }

    /**
     * @return the f_name
     */
    public String getF_name() {
        return f_name;
    }

    /**
     * @param f_name the f_name to set
     */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
     * @return the arg
     */
    public String getArg() {
        return arg;
    }

    /**
     * @param arg the arg to set
     */
    public void setArg(String arg) {
        this.arg = arg;
    }
       
    
    /*public void addObj(String mod,String stat,String ret_type,String f_name,String arg)
    {
        func f2=new func();
        f2.mod=mod;
        f2.stat=stat;
        f2.ret_type=ret_type;
        f2.f_name=f_name;
        f2.arg=arg;
        f1.add(f2);
    }*/
}

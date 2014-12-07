package diction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */

import java.util.ArrayList;
import java.util.List;

public class Var
{
    private static List<Var> f1=null;
    
    private String acc_specifier;
    private String modifier;
    private String data_type;
    private String v_name;
    
    static
{
        setF1((List<Var>) new ArrayList());
}

    public static List<Var> getF1() {
        return f1;
    }

    public static void setF1(List<Var> f1) {
        Var.f1 = f1;
    }

   

    public String getAcc_specifier() {
        return acc_specifier;
    }

    public void setAcc_specifier(String acc_specifier) {
        this.acc_specifier = acc_specifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

   
   
}
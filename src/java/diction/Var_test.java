package diction;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;


/**
 *
 * @author SHUBHANSHU
 */


public class Var_test 
{
    
    static List<String> l = new ArrayList<String> ();
    
    
    static List<Var> f1=Var.getF1();
        
    String reg1;
    
    //Matcher m1;
   
  public static List<String> fetch(String a)
    {   
       f1.removeAll(f1);
        String s=null;
         
         try (BufferedReader br1 = new BufferedReader(new FileReader(a+".txt")))
		{
                        
		   while ((s = br1.readLine()) != null) 
                        {
                               l.add(s);
			       //System.out.println(s);	
                         }
                        return l;
 
		} 
         catch (IOException e) 
                {
                    e.printStackTrace();
		} 
         return null;
    
    }
       

public void functionality(List<String> input){

f1.clear();
       
for( String var: input){

    if(var.contains("\n")) var = var.replaceAll("\n", "");
    var = var.trim();
    
    
    if(var.contains("=")){
        Var f=new Var();
        var = var.substring(0, var.indexOf("=")).trim() + "";
     
        System.out.println(var);
        
        Pattern p = Pattern.compile("(.+\\s)(.+)$");
        Matcher m = p.matcher(var);
         
        Pattern p1 = Pattern.compile("(public\\s|private\\s|protected\\s|package\\s)?+(static\\s)?+(int|char|boolean|float|String)?");
         
         

       if(m.find()){
           
           //System.out.println("flow");
           
           reg1=m.group(1);
          
 //System.out.println(reg1);

           Matcher m1= p1.matcher(reg1); 
            
           if(m1.find())
            {   
                System.out.println(m1.group(3));
                
             
                {
                  if(m.group(2).contains(",")){
                    // String [] tokens = m.group(1).split(" ");
                   String [] tokens = m.group(2).split(",");
                   for(String token : tokens){
                   
                       System.out.println("working!1");
                       f.setAcc_specifier(m1.group(1));
                       f.setModifier(m1.group(3));
                       f.setData_type(m1.group(2));
                       //System.out.println(token);
                       f.setV_name(token);    f1.add(f);
                       
                   }
                   
                }
                  else
                  {    f.setAcc_specifier(m1.group(1));
                       f.setData_type(m1.group(3));
                       f.setModifier(m1.group(2));
                       f.setV_name(m.group(2));
                           f1.add(f);
                       
                  }
              }
            }
           
         
        
             
                    
        }
        
     
        }
       
       
    
       
     else{
        
           Var f=new Var();
           Pattern p = Pattern.compile("(.+\\s)(.+?)(;|=)");
           Matcher m = p.matcher(var);
           
        
          // System.out.println("shubh");
        
            
            Pattern p1 = Pattern.compile("(public\\s|private\\s|protected\\s|package\\s)?+(static\\s)?+(int|char|boolean|float|String)?");
            
          

        if(m.find()){
            
            reg1 = m.group(1);
           
            
            Matcher m1= p1.matcher(reg1);
           
            if(m1.find())
            {    
             
              
               if(m.group(2).contains(","))
                 {
            String [] tokens = m.group(2).split(",");
            for(String token : tokens){
                f.setAcc_specifier(m1.group(1));
                f.setData_type(m1.group(3));
                f.setModifier(m1.group(2));
                f.setV_name(token);
                
                    f1.add(f);
            
            }
                 }
              
              else{
                       f.setAcc_specifier(m1.group(1));
                       f.setData_type(m1.group(3));
                       f.setModifier(m1.group(2));
                       f.setV_name(m.group(2));
                      f1.add(f);
              }
              
             
           
       
       } 
        }
    
        }
        
   }


 }

 void show()
   {
      
       
       for (Var f : f1) 
       {
           System.out.println("access specifier:"+ f.getAcc_specifier());
           System.out.println("modifier:"+f.getModifier());
           System.out.println("datatype:"+f.getData_type());
           System.out.println("Varable name:"+f.getV_name());
       }
   }

public void write(String a) throws FileNotFoundException
    {  
        List<Var> f1=Var.getF1();
      //  List<func> ff=func.getF1();
    
PrintWriter o = new PrintWriter(a);
o.println("Variable:");
o.println("Modifier"+","+"Static"+","+"Return_Type"+","+"Variable_name");

//System.out.println("tester1");
 for (Var f : f1) 
        o.println(f.getAcc_specifier()+","+f.getModifier()+","+f.getData_type()+","+f.getV_name());
//for(func ff:f2)
    
//System.out.println("tester2");
  //      o.println(ff.getMod()+","+ff.getStat()+","+ff.getRet_type()+","+ff.getF_name()+","+ff.getArg()+"\n");
//o.println();           
       
o.close();

f1.clear();
    }
  public static void main(String arg[]) throws FileNotFoundException
            {
                Var_test f= new Var_test();
              //  List<String> li= f.fetch();
                //f.functionality(li);
                
                f.show();
               //f.write();
            }
}
 
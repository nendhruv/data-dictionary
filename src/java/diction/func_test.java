package diction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
//import func;




public class func_test 
{
    
    
      static  List<String> l = new ArrayList<String> ();
   
 private static List<func> f1=func.getF1();
        
   public void functionality(List<String> input)
    { 
            
       // String var="private int main() "; //"private int f1(){}";
      //  String var="private char foo()";

Pattern p = Pattern.compile("(public\\s|private\\s|protected\\s|package\\s)?+(static\\s)?+(void|int|char|boolean|float|String)+\\s+(.*[a-z]+)+([(])+(.*[a-z]+)?");//+([a-z]+)+\\s+([a-z]+)");

        for(String var:input)
        {
        Matcher m = p.matcher(var);
          //   System.out.println("var::"+var);
//System.out.println("var:::"+var);
         if(m.find())
         {
  //      System.out.println("inside var:::"+var);
             
            // System.out.println("var::"+var);
             
      //     System.out.println(m.group(0));
             System.out.println(m.group(1));
             System.out.println(m.group(2));
             System.out.println(m.group(3));
             System.out.println(m.group(4));
             //System.out.println(m.group(5));
             System.out.println(m.group(6));
         //    f.addObj(m.group(1), m.group(2),m.group(3) ,m.group(4),m.group(6));
            
            
            func f=new func();
            f.setMod(m.group(1));
            f.setStat(m.group(2));
            f.setRet_type(m.group(3));
            f.setF_name(m.group(4));
            f.setArg(m.group(6));
            f1.add(f);
            
            }
         }
        
    }
    
   void show()
   {
       List<func> f1=func.getF1();
       /*for(int i=0;i<f1.size();i++)
       {
           System.out.println("mod:"+f1.)
       }*/
       for (func f : f1) 
       {
          System.out.println("mod:  "+f.getMod());
           System.out.println("stat:  "+f.getStat());
           System.out.println("ret type:  "+f.getRet_type());
           System.out.println("f_name:  "+f.getF_name());
           System.out.println("arg:  "+f.getArg());
       
       }
   }
    public static List<String> fetch(String a)
    {
         String s=null;
         try (BufferedReader br1 = new BufferedReader(new FileReader(a+".txt")))
		{
                        
		   while ((s = br1.readLine()) != null) 
                        {
                               l.add(s);
			    //   System.out.println("s::::"+s);	
                         }
                   
    /*                for(String a:l)
    {
        System.out.println("a=="+a);
    }*/
   
                        br1.close();
                        return l;
		} 
         catch (IOException e) 
                {
                    e.printStackTrace();
		} 
         return null;
   
    }
    
    public void write(String a) throws FileNotFoundException, IOException
    {
       
List<func> f1=func.getF1();
PrintWriter o = new PrintWriter(new FileOutputStream(new File(a),true /* append = true */)); 

//PrintWriter o = new PrintWriter("new2.txt");
o.println("Functions:");
o.println("Modifier"+","+"Static"+","+"Return_Type"+","+"Function_name"+","+"Arguements");
 for (func f : f1) 
     o.println(f.getMod()+","+f.getStat()+","+f.getRet_type()+","+f.getF_name()+","+f.getArg());
 for (func f : f1) 
     System.out.println(f.getMod()+"    "+f.getStat()+"      "+f.getRet_type()+"        "+f.getF_name()+"        "+f.getArg()+"\n");
           
       
o.close();


/*    String path="C:\\Users\\user\\Documents\\NetBeansProjects\\dictionary_generator\\new2.txt";
        File file = new File(path);

FileWriter fileWritter = new FileWriter(file,true);
BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
System.out.println("file opened!");
fileWritter.append("This text should be appended in File form Java Program");
  bufferWritter.close();*/
/*      for (func f : f1) 
bufferWritter.write(f.getMod()+","+f.getStat()+","+f.getRet_type()+","+f.getF_name()+","+f.getArg()+"\n");
    	        bufferWritter.close();
 */   }
    public static void main(String arg[]) throws FileNotFoundException, IOException
            {
                func_test f=new func_test();
                //List<String> l= f.fetch();
                f.functionality(l);
                
            //    f.show();
 //               f.write();
            }
}

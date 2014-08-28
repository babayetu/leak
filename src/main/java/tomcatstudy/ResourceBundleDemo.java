package tomcatstudy;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
	public static void main(String[] args) {
		
		  System.out.println(System.getProperty("sun.boot.class.path"));
		  System.out.println(System.getProperty("java.ext.dirs"));
		  System.out.println(System.getProperty("java.class.path"));
		 
		  // copy myres file to C:\j2ee_repo\leak\target\classes 才能被本类的classloader找到
		 Locale locale1 = new Locale("zh", "CN");   
         ResourceBundle resb1 = ResourceBundle.getBundle("myres", locale1);   
         System.out.println(resb1.getString("aaa"));   

         ResourceBundle resb2 = ResourceBundle.getBundle("myres", Locale.getDefault());   
         System.out.println(resb2.getString("aaa"));   

         Locale locale3 = new Locale("en", "US");   
         ResourceBundle resb3 = ResourceBundle.getBundle("myres", locale3);   
         System.out.println(resb3.getString("aaa")); 
	}
}
package zzm.ch7;

public class ClassLoaderSameClassDemo {
	public static void main(String[] args) {

		        try {  
		            //测试加载网络中的class文件  
		            String rootUrl = "http://localhost:8080/karl";  
		            String className = "NetClassLoaderSimple";  
		            NetworkClassLoader ncl1 = new NetworkClassLoader(rootUrl);  
		            NetworkClassLoader ncl2 = new NetworkClassLoader(rootUrl);  
		            Class<?> clazz1 = ncl1.loadClass(className);  
		            Class<?> clazz2 = ncl2.loadClass(className);  
		            Object obj1 = clazz1.newInstance();  
		            Object obj2 = clazz2.newInstance();  
		            clazz1.getMethod("setNetClassLoaderSimple", Object.class).invoke(obj1, obj2);  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
		    }  

}
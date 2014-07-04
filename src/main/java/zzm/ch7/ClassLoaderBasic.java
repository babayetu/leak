package zzm.ch7;

public class ClassLoaderBasic {
	public static void main(String[] args) {
		// 方法 1: 使用 .class
		Class c = String[].class;
		System.out.println(c);

		// 方法 2: 使用实例的getClass()方法
		c = new String[1].getClass();
		System.out.println(c);

		// 方法 3: 使用 Class.forName
		try {
			c = Class.forName("[Ljava.lang.String;");
			System.out.println(c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("byte[]: " + byte[].class.getName());
		System.out.println("char[]: " + char[].class.getName());
		System.out.println("int[]: " + int[].class.getName());
		System.out.println("long[]: " + long[].class.getName());
		System.out.println("double[]: " + double[].class.getName());
		System.out.println("float[]: " + float[].class.getName());

	}
}
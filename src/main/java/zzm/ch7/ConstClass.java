package zzm.ch7;

public class ConstClass {
	static {
		System.out.println("const class init");
	}
	
	public static final String HELLOWORLD = "hello world";
}
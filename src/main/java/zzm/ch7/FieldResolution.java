package zzm.ch7;

public class FieldResolution {
	interface if0 {
		int A = 0;
	}
	
	interface if1 extends if0 {
		int A = 1;
	}
	
	interface if2 {
		int A = 2;
	}
	
	static class Parent implements if1 {
		public static int A = 3;
	}

	static class Sub extends Parent implements if2 {
		public static int A = 4;
	}
	
	public static void main(String[] args) {
		System.out.println(Sub.A);
	}
}
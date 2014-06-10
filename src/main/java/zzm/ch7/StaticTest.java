package zzm.ch7;

public class StaticTest {
	static class Parent {
		public static int A =1 ;
		static {
			System.out.println("A="+A);
			A=2;
		}
	}
	
	static class Child extends Parent {
		public static int B = A;
	}
	
	public static void main(String[] args) {
		System.out.println(Child.B);
		
	}
}
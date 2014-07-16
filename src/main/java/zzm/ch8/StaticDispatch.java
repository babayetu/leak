package zzm.ch8;

public class StaticDispatch {
	static class Human { }
	static class Man extends Human { }
	static class Woman extends Human { }
	
	public static void sayHello(Human a) {
		System.out.println("hello human");
	}
	public static void sayHello(Man a) {
		System.out.println("hello man");
	}
	public static void sayHello(Woman a) {
		System.out.println("hello woman");
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		sayHello(man);
		sayHello(woman);
	}
}
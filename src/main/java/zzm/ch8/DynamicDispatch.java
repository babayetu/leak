package zzm.ch8;

public class DynamicDispatch {
	public static abstract class Human {
		public void say() {
			System.out.println("human");
		}
	}
	
	public static class Man extends Human {
		@Override
		public void say() {
			System.out.println("man");
		}		
	}
	
	public static class Woman extends Human {
		@Override
		public void say() {
			System.out.println("woman");
		}		
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		man.say();
		woman.say();
		man = new Woman();
		man.say();
	}
}
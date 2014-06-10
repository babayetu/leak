package zzm.ch7;

public class DeadLoopClass {
	static {
		if (true) {
			System.out.println(Thread.currentThread() + " init DeadLoopClass");
			while (true) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		Runnable script = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread() + " start");
				DeadLoopClass dlcClass = new DeadLoopClass();
				System.out.println(Thread.currentThread() + " run over");
			}
		};
		Thread th1 = new Thread(script);
		Thread th2 = new Thread(script);
		th1.start();
		th2.start();
	}
}
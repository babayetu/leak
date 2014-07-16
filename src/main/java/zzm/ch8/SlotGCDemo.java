package zzm.ch8;

public class SlotGCDemo {
	public static void main(String[] args) {
		// test one, no collection
//		byte[] slot1 = new byte[64 * 1024 * 1024];
//		System.gc();
		
		//test two, no collection
//		{
//		byte[] slot1 = new byte[64 * 1024 * 1024];
//		}
//		System.gc();
		
		//test three
		{
		byte[] slot1 = new byte[64 * 1024 * 1024];
		}
		int a = 0;
		
		System.gc();
	}
}
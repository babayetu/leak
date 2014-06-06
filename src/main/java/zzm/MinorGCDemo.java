package zzm;

/**
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGC -XX:+PrintGCDetails 
-XX:+UseConcMarkSweepGC


 * @author jingjliu
 *
 */
public class MinorGCDemo {
	private static final int _1MB = 1024 * 1024;
	
	public static void testAllocation() {
		byte[] a1, a2, a3, a4;
		a1 = new byte[2 * _1MB];
		a2 = new byte[2 * _1MB];
		a3 = new byte[2 * _1MB];
		a4 = new byte[4 * _1MB];    // minor GC happens
	}
	
	public static void main(String[] args) {
		testAllocation();
	}
}
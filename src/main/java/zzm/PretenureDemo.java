package zzm;

/**
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGC -XX:+PrintGCDetails 
-XX:+UseConcMarkSweepGC -XX:PretenureSizeThreshold=3145728
 * @author jingjliu
 *
 */
public class PretenureDemo {
	private static final int _1MB = 1024 * 1024;
	
	public static void testPretenureSizeThreshold() {
		byte[] a1;
		a1 = new byte[4 * _1MB];    // directly put into old generation
	}
	
	public static void main(String[] args) {
		testPretenureSizeThreshold();
	}
}
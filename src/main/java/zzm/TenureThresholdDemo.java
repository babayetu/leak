package zzm;

/**
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGC -XX:+PrintGCDetails 
-XX:+UseConcMarkSweepGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * @author jingjliu
 *
 */
public class TenureThresholdDemo {
private static final int _1MB = 1024 * 1024;
	
	public static void testTenuringThreshold() {
		byte[] a1,a2,a3;
		a1 = new byte[_1MB / 4];    // 256k
		a2 = new byte[4 * _1MB];
		a3 = new byte[4 * _1MB];    
		a3 = null;                 // the first minor gc 256k-> survivor, 4M to old
		a3 = new byte[4 * _1MB];   // 
	}
	
	public static void main(String[] args) {
		testTenuringThreshold();
	}
}
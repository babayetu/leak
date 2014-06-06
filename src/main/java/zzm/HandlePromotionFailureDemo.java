package zzm;

/**
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGC -XX:+PrintGCDetails 
-XX:+UseConcMarkSweepGC -XX:-HandlePromotionFailure -XX:+PrintTenuringDistribution

*/
public class HandlePromotionFailureDemo {
	private static final int _1MB = 1024 * 1024;
	
	public static void testTenuringThreshold3() {
		byte[] a1,a2,a3,a4,a5,a6,a7;
		a1 = new byte[2 * _1MB];    
		a2 = new byte[2 * _1MB];    
		a3 = new byte[2 * _1MB];
		a1 = null;
		a4 = new byte[2 * _1MB];    // minor gc
		a5 = new byte[2 * _1MB];    
		a6 = new byte[2 * _1MB];
		a4 = null;
		a5 = null;   
		a6 = null;		
		a7 = new byte[2 * _1MB];
	}
	
	public static void main(String[] args) {
		testTenuringThreshold3();
	}
}
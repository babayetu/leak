package zzm;

/**
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGC -XX:+PrintGCDetails 
-XX:+UseConcMarkSweepGC -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution

[GC [ParNew
Desired survivor size 524288 bytes, new threshold 1 (max 15)
- age   1:     655080 bytes,     655080 total
: 4935K->676K(9216K), 0.0027360 secs] 4935K->4774K(19456K), 0.0027714 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC [ParNew
Desired survivor size 524288 bytes, new threshold 15 (max 15)
: 4772K->0K(9216K), 0.0010478 secs] 8870K->4752K(19456K), 0.0010657 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 par new generation   total 9216K, used 4423K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
  eden space 8192K,  54% used [0x00000000f9a00000, 0x00000000f9e51f98, 0x00000000fa200000)
  from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
  to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
 concurrent mark-sweep generation total 10240K, used 4752K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
 concurrent-mark-sweep perm gen total 21248K, used 2608K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
 
 * @author jingjliu
 *
 */

public class DynamicTenuring {
	private static final int _1MB = 1024 * 1024;
	
	public static void testTenuringThreshold2() {
		byte[] a1,a2,a3,a4;
		a1 = new byte[_1MB / 4];    // 256k
		a2 = new byte[_1MB / 4];    // 256k, a1+a2 is bigger than half of survivor
		a3 = new byte[4 * _1MB];
		a4 = new byte[4 * _1MB];    // the first minor gc, 256k-> survivor, 4M to old
		a4 = null;                 
		a4 = new byte[4 * _1MB];   // 
	}
	
	public static void main(String[] args) {
		testTenuringThreshold2();
	}
}
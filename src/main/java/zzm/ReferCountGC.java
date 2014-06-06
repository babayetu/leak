package zzm;

/**
 * VM args:  -verbose:gc -XX:+PrintGCDetails
 * @author jingjliu
 *
 */

public class ReferCountGC {
	private Object instanceObject = null;
	private static final int _1MB = 1024 * 1024;
	
	/*
	 * the only purpose of this member is to occupy 2MB memory
	 * so gc will show difference
	 */
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void testGC() {
		ReferCountGC instA = new ReferCountGC();
		ReferCountGC instB = new ReferCountGC();
		instA.instanceObject = instB;
		instB.instanceObject = instA;
		instA = null;
		instB = null;
		System.gc();
	}
	
	public static void main(String[] args) {
		ReferCountGC.testGC();
	}
}
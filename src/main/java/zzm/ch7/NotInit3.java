package zzm.ch7;

/**
 * VM args: -XX:+TraceClassLoading
 * const class is not inited
 * @author jingjliu
 *
 */
public class NotInit3 {
	public static void main(String[] args) {
		System.out.println(ConstClass.HELLOWORLD);
		
	}
}
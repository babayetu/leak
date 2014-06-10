package zzm.ch7;

/**
 * VM args: -XX:+TraceClassLoading
 * only super class is inited, but sub class also loaded
 * @author jingjliu
 *
 */
public class NotInit {
	public static void main(String[] args) {
		//subclass is not inited
		System.out.println(SubClass.value);
	}
}
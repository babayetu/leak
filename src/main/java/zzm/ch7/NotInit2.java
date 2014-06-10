package zzm.ch7;

/**
 * VM args: -XX:+TraceClassLoading
 * super class is not inited
 * @author jingjliu
 *
 */
public class NotInit2 {
	public static void main(String[] args) {
		//superclass is not inited
		SuperClass[] sc = new SuperClass[10];
	}
}
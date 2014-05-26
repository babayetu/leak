package zzm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author jingjliu
 *
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		long i=0;
		while (true) {
			list.add(String.valueOf(i++).intern());	
			if (i % 10000 == 0) {
				System.out.println(i);
			}
		}
	}
}
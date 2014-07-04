package zzm;

import java.util.Iterator;
import java.util.*;
import java.util.Set;

public class ThreadStackTrace {
	public static void main(String[] args) {
		Set<Map.Entry<Thread,StackTraceElement[]>> stackTrace = Thread.getAllStackTraces().entrySet();
		Iterator<Map.Entry<Thread,StackTraceElement[]>> it = stackTrace.iterator();
		
		while (it.hasNext())
		{
			Map.Entry<Thread,StackTraceElement[]> nextElementEntry = it.next();
			Thread thread = (Thread) nextElementEntry.getKey();
			StackTraceElement[] stack = (StackTraceElement[]) nextElementEntry.getValue();
			if (thread.equals(Thread.currentThread())) {
				continue;
			}
			System.out.print("\n thread: "+thread.getName() + "\n");
			for (StackTraceElement se:stack) {
				System.out.print("\t"+se+"\n");
			}
			
		}
	}
}
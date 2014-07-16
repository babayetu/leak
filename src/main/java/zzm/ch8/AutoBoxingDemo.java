package zzm.ch8;

public class AutoBoxingDemo {
	public static void main(String[] args) {
		Integer i = 100;
		Integer j  = 100;
		System.out.println(i == j);
		System.out.println(i.equals(j));

		Integer m = 200;
		Integer n = 200;
		System.out.println(m == n);
		System.out.println(m.equals(n));

		int k = 100;
		System.out.println(k == j);
		System.out.println(j.equals(k));
		
//		Integer i = 100;
//
//		Integer j = new Integer(100);
//
//		Integer k = Integer.valueOf(100);
//
//		System.out.println(i == j);
//
//		System.out.println(j == k);
		
	}
}
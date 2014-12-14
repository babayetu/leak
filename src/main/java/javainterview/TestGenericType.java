package javainterview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class TestGenericType {
	public static double totalArea(Collection<? extends Shape> arr) {
		double total = 0;
		for (Shape a:arr) {
			if (a!=null) {
				total += a.area();
			}			
		}
		return total;
	}

	public static <T> boolean find(T[] arr, T x) {
		for (T a:arr) {
			if (x.equals(a)) {
				return true;
			}			
		}
		return false;
	}
	
	public static <T extends Comparable<? super T>> T findMax(T[] arr) {
		int maxInt = 0;
		for (int i=0;i<arr.length;i++) {
			if (arr[maxInt].compareTo(arr[i]) < 0) {
				maxInt = i;
			}
		}
		return arr[maxInt];
	}

	public static <T> T findMin(T[] arr, Comparator<? super T> c) {
		int minInt = 0;
		for (int i=0;i<arr.length;i++) {
			if (c.compare(arr[minInt],arr[i]) > 0) {
				minInt = i;
			}
		}
		return arr[minInt];
	}
	
	public static void main(String[] args) {
		Square s1 = new Square(2,3);
		Square s2 = new Square(4,5);
		Square s3 = new Square(6,7);
		
		Collection<Square> cs = new ArrayList<Square>();
		cs.add(s1);
		cs.add(s2);
		double result = TestGenericType.totalArea(cs);
		System.out.println(result);
		
		Square[] sa = new Square[] {s1, s2, s3};
		
		System.out.println(TestGenericType.find(sa, s1));
		
		SquareComparator sc = new SquareComparator();
		System.out.println(TestGenericType.findMin(sa,sc));
	}
}
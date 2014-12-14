package algorithm;


public class BinarySearchGeneric {
	public static <T extends Comparable<? super T>> int find(T[] arr, T x, int low, int high) {
		if (arr.length <= 0 ) {
			return -1;
		}
		
		int mid = low + (high - low) / 2;
		if (low == high) {
			if (x.compareTo(arr[mid]) != 0 ) {
				return -1;
			} else {
				return low;
			}
		} else {
			if (x.compareTo(arr[mid]) < 0) {
				return find(arr, x, low, mid -1);
			} else if (x.compareTo(arr[mid]) > 0) {
				return find(arr, x, mid+1, high);
			} else {
				return mid;
			}
		}		
	}
	
	public static void main(String[] args) {
		String[] arr = new String[] {"a","b","c","d","e","f","g"};
		System.out.println(BinarySearchGeneric.find(arr, "g", 0, arr.length-1));
	}
}
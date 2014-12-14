package javainterview;

public class FindMaxDemo {
	public static Comparable find(Comparable[] arr) {
		int maxIndex = 0;
		
		for (int i=0;i<arr.length; i++) {
			if (arr[i].compareTo(arr[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		
		return arr[maxIndex];
	}
	
	public static void main(String[] args) {
		String[] arr1 = new String[]{"123","789", "456"};
		Comparable result = FindMaxDemo.find(arr1);
		System.out.println(result);
	}
}
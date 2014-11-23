package algorithm;

import util.Print;

public class MergeSort {
	public void mergeSortedArray(int[] a, int[] b, int[] all) {		
		assert(all.length >= a.length+b.length);
		int aLength = a.length;
		int bLength = b.length;
		int i=0;
		int j=0;
		int k=0;
		
		while (i<aLength && j<bLength) {
			if (a[i] <= b[j]) {
				all[k] = a[i];
				i++;
				k++;
			} else {
				all[k] = b[j];
				j++;
				k++;
			}
		}
		
		while (i<aLength) {
			all[k] = a[i];
			i++;
			k++;
		}
		
		while (j<aLength) {
			all[k] = b[j];
			j++;
			k++;
		}		
	}
	
	public void mergeSortedArray2(int[] a, int first, int mid, int last, int[] all) {	
		int i=first;
		int j=mid + 1;
		int aLength = mid;
		int bLength = last;
		int k=0;
		
		while (i<=aLength && j<=bLength) {
			if (a[i] <= a[j]) {
				all[k] = a[i];
				i++;
				k++;
			} else {
				all[k] = a[j];
				j++;
				k++;
			}
		}
		
		while (i<=aLength) {
			all[k] = a[i];
			i++;
			k++;
		}
		
		while (j<=bLength) {
			all[k] = a[j];
			j++;
			k++;
		}
		
		for (i = 0; i < k; i++)  
	        a[first + i] = all[i];
	}
	
	public void splitArray(int[] a, int first, int last, int[] all) {
		if (first < last) {
			int mid = (first + last) / 2;
			splitArray(a, first, mid,all);
			splitArray(a, mid + 1, last, all);
			mergeSortedArray2(a, first, mid, last, all); // 合并两个有序数
		}
	}
	
	public static void main(String[] args) {
		int[] a = {1,3,5,7,9,11,2,4,6,8,10,12};
		//int[] a = {1};
		int[] c = new int[a.length];
		new MergeSort().splitArray(a,0, a.length - 1, c);
		Print.array(a);
	}
}
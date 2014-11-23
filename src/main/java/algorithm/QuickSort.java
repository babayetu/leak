package algorithm;

import util.Print;

/**
 * 分而治之 + 挖坑填埋法
 * http://blog.csdn.net/one_in_one/article/details/7053525
 * @author jingjliu
 *
 */
public class QuickSort {
	public void sort(int[] oldArray, int low, int high) {
		//游标从小到大扫描
		int i = low;
		//游标从大到小扫描
		int j = high;
		int key = oldArray[i];
		
		//停止条件
		if (low>=high) return;
		
		while (i!=j) {
			//第一遍从后面扫描，找出第一个小于key的
			while (i<j && oldArray[j] >= key) {
				j--;
			}		
			//填坑
			oldArray[i] = oldArray[j];
			
			//第二遍从前面扫描，找出第一个大于key的
			while (i<j && oldArray[i] <= key) {
				i++;
			}		
			//填坑
			oldArray[j] = oldArray[i];
		}
		
		//key填在中间
		oldArray[i] = key;
		
		//递归分治前一半和后一半
		sort(oldArray,low,i-1);
		sort(oldArray,i+1,high);
	}
	
	public static void main(String[] args) {
		int[] test = {49,38,65,97,76,13,27};
		new QuickSort().sort(test,0,test.length-1);
		Print.array(test);
	}
}
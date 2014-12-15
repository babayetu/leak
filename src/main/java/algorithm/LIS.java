package algorithm;

/**
 * http://shenxingfeng1988.blog.163.com/blog/static/106376005201361784928645/
 * 
 * 遍历输出
 * http://www.ahathinking.com/archives/117.html
 * 
 * d[i] 代表以元素a[i]结尾的LIS(最长递增子序列)
 * 如果存在a[j]<=a[i], 0<=j<i，则以a[i]结尾的LIS是前面所有小于a[i]的字串的d[j] + 1，找个最大的
 * 则 d[i] = max{d[j] + 1} 0<=j<i
 * 如果不存在a[j]>a[i]，则a[i]是以a[i]结尾字符串中最小的，
 * d[i] = 1
 * 
 * 想要求d(i)，就把i前面的各个子序列中， 最后一个数不大于A[i]的序列长度加1，
 * 然后取出最大的长度即为d(i)。 当然了，有可能i前面的各个子序列中最后一个数都大于A[i]，
 * 那么d(i)=1， 即它自身成为一个长度为1的子序列。
 * 
 * 
 * N*LOG(N)算法
 * 从LIS的性质出发，要想得到一个更长的上升序列，该序列前面的数必须尽量的小。

 * 对于原序列1,5,8,3,6,7来说，当子序列为1,5,8时，遇到3时，序列已经不能继续变长了。但
 * 是，我们可以通过替换，使“整个序列”看上去更小，从而有更大的机会去变长。这样，当替换5-3和替换8-6完成后（此时序列为1,3,6），我们可以在序列末尾添加一个7了。
 * 那为什么复杂度可以是O(NlogN)呢？
 * 关键就在“替换”这一步上，若直接遍历序列替换，每次替换都要O(N)的时间。但是只要我们再次利用LIS的性质——序列是有序的（单调的），
 * 就可以用二分查找，在O(logN)的时间内完成一次替换，所以算法的复杂度是O(NlogN)的
 * 
 * 
 * @author jingjliu
 *
 */
public class LIS {
	private static int[] dp = null;
	private static int lisLen = 1;
	
	//nlogn算法最终结果b
	private static int[] bRecord = null;
	private static int bLen = 0;
	
	public static int find(int[] arr) {
		if (arr.length == 0) {
			return -1;
		}
		
		int[] d = new int[arr.length];
		int len = 1;
		
		for (int i =0; i<arr.length;i++) {
			d[i] = 1;
			
			for (int j=0;j<i;j++) {				
				if (arr[j] <= arr[i] && d[j] + 1 > d[i]) {
					d[i] = d[j] + 1;
					if (d[i] > len) {
						len = d[i];
					}
				}
			}
		}
		
		dp = d;
		lisLen = len;
		
		return len;
	}
	
	//动态规划O(N^2)结果的遍历输出
	public static void printLIS(int[] arr, int i) {	
		boolean isLIS = false;
		if (arr.length == 0 || i<0) {
			return;
		}
		
		if (dp[i] == lisLen) {
			lisLen--;
			isLIS = true;
		}
		
		printLIS(arr, --i);
		if (isLIS) {
			System.out.println(arr[i+1]);
		}
	}
	
	public static int nlognFind(int[] arr) {
		if (arr.length <= 0 ) {
			return -1;
		}
		
		int[] b = new int[arr.length];
		//数组b有效数据游标
		int len = 0;
		
		for (int i=0;i<arr.length;i++) {
			//第一次
			if (len == 0) {
				b[len] = arr[i];
				len++;
				continue;
			}
			//b数组最后一个数据是b[len-1]
			//如果arr新的元素比b的最后一个元素大，则LIS可以更长，b数组增加一位
			//b是递增数列，用二分法查找
			if (arr[i] >= b[len-1]) {
				b[len] = arr[i];
				len++;
			} else {
				//否则用arr来替换b里面刚好比arr这个元素大的元素
				//这样降低了b（也就是未来的LIS）的整体元素的大小，有可能延伸到更长的LIS长度			
				
				if (len == 1) {
					//b只有一个元素，直接替换
					b[len-1] = arr[i];
				} else {
					//LOG(N)时间复杂度，二分查找
					int left = 0;
					int right = len -1;
					int mid = 0;
					
					while (left < right) {
						mid = left + (right -left) / 2;
						if (b[mid] < arr[i]) {
							left = mid + 1;
						} else if (b[mid] > arr[i]) {
							right = mid - 1;
						} else {
							//已经存在了，不更新
							break;
						}
					}
					
					if (left >= right) {
						b[left] = arr[i];
					}
				}
			}
		}
		
		bRecord = b;
		bLen = len;
		
		return len;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {5,3,4,8,6,7};
		LIS.find(arr);		

		//LIS.printLIS(arr, arr.length-1);
		System.out.println(LIS.nlognFind(arr));
		
		for (int i =0;i<bLen;i++) {
			System.out.println(bRecord[i]);
		}
	}
	
}
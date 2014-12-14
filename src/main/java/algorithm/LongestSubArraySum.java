package algorithm;

public class LongestSubArraySum {
	/**
	 * 1.找到下一个非负整数，如果到数组最后都找不到，返回0
	 * 如果找到重复2
	 * 2. 找到后，扫描之后的数，如果sum为0，重复1
	 * 如果sum不为0，记录max sum
	 */
	
	public int MaxSubArraySum(int[] input) {
		if (input.length <= 0 ) {
			System.out.println("invalid input array length");
		}
		
		int tempSum = 0, maxSum = 0;
		for (int i =0;i<input.length; i++) {			
			tempSum += input[i];
			if (tempSum > maxSum) {
				maxSum = tempSum;				
			} else if (tempSum < 0) {
				tempSum = 0;
			}
		}
		
		return maxSum;		
		
	}
	
	public static void main(String[] args) {
		int[] input = new int[] {-2,11,-4,13,-5,-2};
		LongestSubArraySum lsas = new LongestSubArraySum();
		System.out.println(lsas.MaxSubArraySum(input));
	}
}
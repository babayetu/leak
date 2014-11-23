package algorithm;

public class PackGemAgain {
	int[] gems_value;
	int[] gems_capacity;
	int packCapacity;
	// 第一个参数：宝石数量，第二个参数：背包容量
	int[][] status;
	
	public PackGemAgain (int[] gemV, int[] gemC, int packC) {
		if (gemV ==null || gemC == null) {
			throw new IllegalArgumentException();
		}
		gems_value = gemV;
		gems_capacity = gemC;
		packCapacity = packC;
		status = new int[gemV.length+1][packC+1];
	}
	
	private int statusTransfer(int n, int c) {
		if (n==0) return 0;
		if (status[n][c] != 0) return status[n][c];
		if (c >= gems_capacity[n-1]) {
			status[n][c] = max(statusTransfer(n-1, c - gems_capacity[n-1]) + gems_value[n-1], statusTransfer(n-1, c));
		}
		
		return status[n][c];
	}
	
	private int max(int a, int b) {
		if (a>b) {
			return a;
		} else {
			return b;
		}
	}

	public static void main(String[] args) {
		int[] gemC = {4,3,5,2,5}; 
		int[] gemV = {9,6,1,4,1};
		int packC = 10;
		PackGemAgain aIns = new PackGemAgain(gemV,gemC,packC);
		System.out.println(aIns.statusTransfer(5,10));
	}
}
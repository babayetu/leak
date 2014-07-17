package algorithm;

/**
 * 话说有一哥们去森林里玩发现了一堆宝石，他数了数，一共有n个。 但他身上能装宝石的就只有一个背包， 背包的容量为C。这哥们把n个宝石排成一排并编上号：
 * 0,1,2,…,n-1。 第i个宝石对应的体积和价值分别为S[i]和V[i] 。排好后这哥们开始思考：
 * 背包总共也就只能装下体积为C的东西，那我要装下哪些宝石才能让我获得最大的利益呢？
 */

public class DPPackGem {
	private int mPackCapacity = 0;
	private int mGemNum = 0;
	private int d[][] = null;
	private int s[] = null;
	private int v[] = null;

	// d[i][j] = maximum value for total i gem with total j pack capacity

	public DPPackGem(int mPackCapacity, int mGemNum) {
		if (mPackCapacity <= 0 || mGemNum <= 0) {
			throw new IllegalArgumentException();
		}
		this.mPackCapacity = mPackCapacity;
		this.mGemNum = mGemNum;
		initAllZero();
	}

	public DPPackGem(int capacity, int[] size, int[] value) {
		if (size == null || value == null || size.length == 0
				|| value.length == 0 || capacity <= 0 || size.length != value.length) {
			throw new IllegalArgumentException();
		}
		s = size;
		v = value;
		d = new int[size.length][capacity];
		this.mPackCapacity = capacity;
		this.mGemNum = size.length;
	}

	private void initAllZero() {
		d = new int[mGemNum][mPackCapacity];
		s = new int[mGemNum];
		v = new int[mGemNum];
	}

	public int findMostValuable() {
		for (int i = 0; i < mGemNum; i++) {
			for (int j = 0; j < mPackCapacity; j++) {
				if (i == 0) {
					d[i][j] = 0;
				}

				if (i > 0 && j >= s[i - 1]) {
					int bigger = d[i - 1][j];
					if (bigger < d[i-1][j - s[i - 1]] + v[i - 1]) {
						bigger = d[i-1][j - s[i - 1]] + v[i - 1];
					}
					d[i][j] = bigger;
				}
			}
		}

		return d[mGemNum - 1][mPackCapacity - 1];
	}
	
	private void printAll() {
		StringBuffer sb = new StringBuffer();
		for (int i =0; i<mGemNum;i++) {
			
			for (int j=0;j<mPackCapacity;j++) {
				sb.append(d[i][j]+ " ");
			}
			System.out.println(sb.toString());
			
		}
	}

	public static void main(String[] args) {
		int[] size = new int[] { 5, 4, 3 };
		int[] value = new int[] { 20, 10, 12 };
		int cap = 10;
		DPPackGem dpm = new DPPackGem(cap, size, value);
		System.out.println(dpm.findMostValuable());
		dpm.printAll();
	}
}
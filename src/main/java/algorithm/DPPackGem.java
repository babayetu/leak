package algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import sun.net.www.content.audio.x_aiff;
import util.ReadData;

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
	private int select[] = null;

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
				|| value.length == 0 || capacity <= 0
				|| size.length != value.length) {
			throw new IllegalArgumentException();
		}
		s = size;
		v = value;
		d = new int[size.length + 1][capacity + 1];
		select = new int[size.length];
		this.mPackCapacity = capacity;
		this.mGemNum = size.length;
	}

	public DPPackGem(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException();
		}
		initFromFile(fileName);
	}

	private void initFromFile(String name) {
		HashMap<Integer, ArrayList<Integer>> hm = ReadData.readFile(name);
		// 文件第一行是宝石的数量N，背包容量C
		ArrayList<Integer> a1 = hm.get(new Integer("0"));
		ArrayList<Integer> a2 = hm.get(new Integer("1"));
		mGemNum = a1.get(0).intValue();
		mPackCapacity = a2.get(0).intValue();

		if (mGemNum + 1 > a1.size()) {
			throw new ArrayStoreException();
		}

		d = new int[mGemNum + 1][mPackCapacity + 1];
		s = new int[mGemNum];
		v = new int[mGemNum];
		select = new int[mGemNum];
		for (int i = 0; i < mGemNum; i++) {
			s[i] = a1.get(i + 1).intValue();
			v[i] = a2.get(i + 1).intValue();
		}
	}

	private void initAllZero() {
		d = new int[mGemNum + 1][mPackCapacity + 1];
		s = new int[mGemNum];
		v = new int[mGemNum];
		select = new int[mGemNum];
	}

	public int findMostValuable() {
		for (int i = 0; i < mGemNum + 1; i++) {
			for (int j = 0; j < mPackCapacity + 1; j++) {
				if (i == 0) {
					d[i][j] = 0;
				}

				if (i > 0 && j >= s[i - 1]) {
					int bigger = d[i - 1][j];
					if (bigger < d[i - 1][j - s[i - 1]] + v[i - 1]) {
						bigger = d[i - 1][j - s[i - 1]] + v[i - 1];
					}
					d[i][j] = bigger;
				}
			}
		}

		// find gem selection
		int c = mPackCapacity; // 初始化的时候已经加过1了，这里就不用了
		for (int i = mGemNum; i > 0; i--) { // 操作d数组的时候都要gem number+1,
													// capacity+1
			if (d[i][c] > d[i - 1][c]) {
				select[i-1] = 1; // 0 不选， 1选
				c = c - s[i-1];
			}
		}

		return d[mGemNum][mPackCapacity];
	}

	private void printAll() {
		StringBuffer sb = new StringBuffer();
		System.out.println("-------d matrix--------");
		for (int i = 0; i < mGemNum; i++) {

			for (int j = 0; j < mPackCapacity; j++) {
				sb.append(d[i][j] + " ");
			}
			System.out.println(sb.toString());
			sb.delete(0, sb.length());

		}

		System.out.println("-------selection map--------");
		System.out.println("gemnum="+mGemNum+" capacity="+mPackCapacity);
		
		for (int i = 0; i < select.length; i++) {
			if (select[i] == 1) {
				System.out.println(s[i] + " " + v[i]);
			}
		}
	}

	public static void main(String[] args) {
		int[] size = new int[] { 5, 4, 3 };
		int[] value = new int[] { 20, 10, 12 };
		int cap = 10;
		// DPPackGem dpm = new DPPackGem(cap, size, value);
		DPPackGem dpm = new DPPackGem("./src/main/resources/algorithm/data.in");
		System.out.println(dpm.findMostValuable());
		dpm.printAll();
	}
}
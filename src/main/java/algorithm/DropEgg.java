package algorithm;


public class DropEgg {
	private static int[] myF;
	private int totalN;
	
	public DropEgg (int N) {
		myF = new int[N];
		totalN = N;
	}
	
	public int FindResult() {
		return F(totalN);
	}
	
	private static int F (int x) {
		if (x == 1) {
			return 1;
		}
		if (x == 2) {
			return 2;
		}
		//记录下中间计算值，加快递归过程
		if (myF[x-1] != 0) {
			return myF[x-1];
		}
		int min = 65535;
		for (int i=2; i<x; i++) {
			int max_value = max(i, F(x-i) +1);
			if (min > max_value) {
				min = max_value;
			}
		}
		//记录下中间计算值，加快递归过程
		myF[x-1] = min;
		return min;
	}
	
	public static int max(int a, int b) {
		if (a>=b) {
			return a;
		} else {
			return b;
		}
	}

	
	public static void main(String[] args) {
		DropEgg de = new DropEgg(100);
		System.out.println(de.FindResult());		
	}
}
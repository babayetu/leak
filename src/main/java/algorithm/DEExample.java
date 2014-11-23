package algorithm;

//too slow
public class DEExample {
	// times[i]表示N取值为i的时候，需要扔多少次
		private static int[] times;

		public static int dropEgg(int N) {
			// 初始化数组
			times = new int[N + 1];
			return dp(N);
		}

		private static int dp(int N) {
			// 两层楼或一层楼就没有计算的必要了
			if (1 == N)
				return 1;
			else if (2 == N)
				return 2;

			for (int x = 2; x < N; x++) {
				int max = maxTimes(N, x);
				if (0 == times[N] || times[N] > max)
					times[N] = max;
			}

			return times[N];
		}

		// 碎和不碎的次数最大值
		private static int maxTimes(int N, int x) {
			int sum = dp(N - x) + 1;
			if (x < sum)
				return sum;
			else
				return x;
		}
		
		public static void main(String[] args) {
			DEExample dee = new DEExample();
			System.out.println(DEExample.dropEgg(8));
		}
}
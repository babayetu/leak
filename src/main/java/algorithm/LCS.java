package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符串最长公共子序列，可以不连续
 *  leetcode passed
 *  * @author jingjliu
 *
 */

public class LCS {
	public static void main(String[] args) {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
//			String a = br.readLine();
//			String b = br.readLine();
			String a = "abcbdab";
			String b = "bdcaba";
			char[] x = a.toCharArray();
			char[] y = b.toCharArray();
			
			/**
			 * c[i,j] 是X0...Xi-1,Y0...Yj-1的最长公共子序列
			 * c[i,j] = 0                  if i=0 or j=0  有一个字符串为空
			 * c[i,j] = c[i-1,j-1] + 1     if i>0 and j>0 and Xi-1 == Yj-1
			 * c[i,j] = max{c[i-1,j],c[i,j-1]}  if i>0 and j>0 and Xi-1 !=Yj-1
			 */
			/**
			 * 注意c[n,m] = c[n-1,m-1] + 1, 当x[n-1]==y[m-1]的时候
			 */
			int n = x.length;
			int m = y.length;
			int[][] c = new int[n+1][m+1];
			char[][] trace = new char[n+1][m+1];
			
			// y字符串为空，LCS=0
			for (int i=0;i<=n;i++) {
				c[i][0] = 0;
			}
			// x字符串为空，LCS=0
			for (int j=0;j<=m;j++) {
				c[0][j] = 0;
			}
			
			for (int i=1;i<=n;i++) {
				for (int j=1;j<=m;j++) {
					if (x[i-1] == y[j-1]) {  // 注意：这句解决了bug
						c[i][j] = c[i-1][j-1] +1;
						trace[i][j] = '=';
					} else  {
						if (c[i-1][j] > c[i][j-1]) {
							c[i][j] = c[i-1][j];
							trace[i][j] = 'u';
						} else {
							c[i][j] = c[i][j-1];
							trace[i][j] = 'l';
						}												
					}
				}
			}
			System.out.println(c[n][m]);

			StringBuffer sb = new StringBuffer();
			for (int i=0;i<=n;i++) {
				for (int j=0;j<=m;j++) {
					sb.append(c[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			sb.delete(0,sb.length());
			
			//打印公共子串
			printLCS(trace, x, n, m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void printLCS(char[][] trace, char[] x, int i, int j) {
		if (i==0 || j==0) 
			return;
		if (trace[i][j] == '=') {
			printLCS(trace, x, i-1 ,j-1);
			System.out.println(x[i-1]);
		} else {
			if (trace[i][j] == 'u') {
				printLCS(trace, x, i-1 ,j);
			} else {
				printLCS(trace, x, i ,j-1);
			}
		}
	}
}
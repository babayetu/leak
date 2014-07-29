package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

// http://blog.csdn.net/taojiayu/article/details/1351235

public class EightQueens {
	public ArrayList<int[]> findResult() {
		// int[0] = 3 第1列的queen在第4行
		//00000000 ... 77777777
		ArrayList<int[]> result = new ArrayList<int[]>();
		int[] colOccupy = new int[] {0,0,0,0,0,0,0,0};
		//total cases
		int count = 1;
		for (int i=0;i<colOccupy.length;i++) {
			count = count * colOccupy.length;
		}
		
		for (int i=0; i<count; i++) {	
				colOccupy[0]++ ; 
				if (colOccupy[0] >= colOccupy.length) {
					boolean findPos = false;
					// 产生进位了
					for (int j=1; j<colOccupy.length && !findPos; j++) {	
						if (colOccupy[j] == (colOccupy.length-1)) {
							// ==7 跳过
							
						} else {
							findPos = true;
							colOccupy[j]++;
							for (int k =j-1;k>=0;k--) {
								colOccupy[k] = 0;
							}
						}
					}
				}
				//EightQueens.printArray(colOccupy);
				if (!conflict(colOccupy)) {
					result.add(colOccupy.clone());
				}
		}
		
		return result;
	}
	
	private boolean conflict(int[] map) {
		// 算法 map[i] == map[j] 两个皇后在同一行
		// i + map[i] = j + map[j]
		// i - map[i] = j - map[j]
		for (int i=0;i<map.length;i++) {
			for (int j =i+1; j<map.length;j++) {
				if (map[i] == map[j]) {
					return true;
				}
				if (i + map[i] == j + map[j]) {
					return true;
				}
				if (i - map[i] == j - map[j]) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static void printArray(int[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i =0 ; i<array.length; i++) {
			sb.append(array[i]).append(" ");
		}
		System.out.println(sb.toString());
		sb.delete(0, sb.length());
	}
	
	public static void main(String[] args) {
		EightQueens eq = new EightQueens();
		ArrayList<int[]> result = eq.findResult();
		System.out.println(result.size());
		Iterator it = result.iterator();
		
		while (it.hasNext()) {
			int[] array = (int[])it.next();
			printArray(array);
		}
		
	}
	
}
package algorithm;

import util.Print;
/**
 * Refer to http://news.cnblogs.com/n/176771/
 * @author jingjliu
 *
 */

public class KmpStringMatch {
	private char[] sTotal;
	private char[] sSub;
	private int[] matchT;

	KmpStringMatch() {
		matchT = new int[] {0,0,0,0,1,2,0}; 
		sTotal = "ABCDABABCDABABCDABABCDABABCDABABCDABABCDABBBC ABCDAB AABBABCABACDABCDABCDABDE".toCharArray();
		sSub = "ABCDABDABCD".toCharArray();
	}

	public char[] getsTotal() {
		return sTotal;
	}

	public void setsTotal(char[] sTotal) {
		this.sTotal = sTotal;
	}

	public char[] getsSub() {
		return sSub;
	}

	public void setsSub(char[] sSub) {
		this.sSub = sSub;
	}

	public int[] getMatchT() {
		return matchT;
	}

	public void setMatchT(int[] matchT) {
		this.matchT = matchT;
	}

	public boolean kmpMatch() {
		//两个字符串的游标
		int curT = 0;
		int curS = 0;
		int alreadyMatched = 0;
		
		while (curT < sTotal.length) {
			//当两个字符不匹配
			if (sTotal[curT] != sSub[curS]) {
				if (alreadyMatched == 0) {
					//尚未有匹配的
					curT++;
					continue;
				} else {
					int moveSteps = alreadyMatched - matchT[alreadyMatched - 1];
					curS = curS - moveSteps;
					alreadyMatched = curS;
				}
			}
			
			//当前两个字符匹配
			if (sTotal[curT] == sSub[curS]) {
				if (curS == sSub.length - 1) {
					System.out.println("Start Pos: "+ (curT - sSub.length)+ " End Pos:"+curT);
					return true;
				}
				curT++;
				curS++;
				//已匹配字符加一
				alreadyMatched++;
				continue;
			}		
		}
		return false;		
	}
	
	private int[] partialMatchMapGenerator() {
		int curS = 0;
		//从第一个字符开始，计算前缀，后缀，再计算共有元素
		int length = sSub.length;
		int[] result = new int[length];
		while (curS < result.length) {
			if (curS == 0 || curS == result.length -1) {
				//第一个和最后一个字符的，前缀后缀都是空，所以公有元素也是0个
				result[curS] = 0;
				curS ++;
				continue;
			}
			
			//目前处理的字符串是0..nowS
			int nowS = curS;
			//前缀后缀开始移动的位置
			int frontDec = 1;
			int backDec = nowS - 1;
			int matchPartLen = 0;
			while (frontDec <= nowS && backDec >= 0) {
				//在每个前后缀里面，逐个比较字符串
				//字符串为frontDec..nowS由f1从前到后遍历, 和0..backDec由f2从前到后遍历
				boolean matched = true;
				
				for (int i=0;i<=backDec;i++) {
					if (sSub[i] == sSub[i+frontDec]) {
						//continue
					} else {
						matched = false;
						break;
					}
				}
				
				if (matched) {
					if (backDec + 1 > matchPartLen) {
						matchPartLen = backDec + 1;
					}					
				}
				
				frontDec++;
				backDec--;
			}
			result[curS] = matchPartLen;
			
			//主字符游标移动
			curS++;			
		}
		
		setMatchT(result);
		return result;
	}
	
	public boolean normalNxMMatch() {
		//两个字符串的游标
			int curT = 0;
			int curS = 0;
			int matchBegin = 0;
				
			while (curT < sTotal.length) {
				//当两个字符不匹配
				if (sTotal[curT] != sSub[curS]) {
					if (matchBegin !=0) {
						curT = matchBegin + 1;
					} else {
						curT++;
					}
					curS = 0;
					matchBegin = 0;
					continue;
				}
					
				//当前两个字符匹配
				if (sTotal[curT] == sSub[curS]) {
					if (matchBegin == 0) {
						matchBegin = curT;
					}
					if (curS == sSub.length - 1) {
						//System.out.println("Start Pos: "+ (curT - sSub.length)+ " End Pos:"+curT);
						return true;
					}
					curT++;
					curS++;
					continue;
				}		
			}
			return false;		
	}
	
	public static void main(String[] args) {
//		long startTime=System.currentTimeMillis();   //获取开始时间
		String aLong = "ABCDABABCDABABCDABABCDABABCDABABCDABABCDABBBC ABCDAB AABBABCABACDABCDABCDABDE";
		String bShort = "ABCDABC";
		KmpStringMatch kmpsm = new KmpStringMatch();
		kmpsm.setsTotal(aLong.toCharArray());
		kmpsm.setsSub(bShort.toCharArray());
		
		Print.array(kmpsm.partialMatchMapGenerator());
		
		System.out.println(kmpsm.kmpMatch());
		
//		for (int i =0;i<1000000; i++) {
//			kmpsm.kmpMatch();
//		}
//		
//        long endTime=System.currentTimeMillis(); //获取结束时间
//		System.out.println("KMP程序运行时间： "+(endTime-startTime)+"ms"); // O(n+m)
		
//		startTime=System.currentTimeMillis();
//		for (int i =0;i<1000000; i++) {
//			kmpsm.normalNxMMatch();
//		}
//		endTime=System.currentTimeMillis();
//		System.out.println("普通程序运行时间： "+(endTime-startTime)+"ms"); // O(N*M)
	}
}
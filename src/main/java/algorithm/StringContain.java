package algorithm;

import java.util.HashMap;

public class StringContain {
	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	private static long[] prime = {2,3,5,7,11,13,17,19,23,29,
			              31,37,41,43,47,53,59,61,67,71,
			              73,79,83,89,97,101,103,107};
	
	// 4byte = 32 bits 可以容纳大小写共26bit字符
	// 大写
	int bitMapCap = 1;
	//小写
	int bitMapNormal = 1;
	
	public boolean include(char[] input) {
		for (int i=0;i<input.length;i++) {
			if (!map.containsKey(new Character(input[i]))) {
				return false;
			}
		}
		return true;
	}
	
	public void setupMap(char[] longer) {
		for (int i=0;i<longer.length;i++) {
			map.put(new Character(longer[i]), 1);
		}
	}
	
	public void setupBitMap(char[] longer) {
		for (int i=0;i<longer.length;i++) {
			if (longer[i] < 'a' ) {
				//是大写字母
				bitMapCap = bitMapCap | (1<< (longer[i] -'A'));
			} else {
				//小写字母
				bitMapNormal = bitMapNormal | (1<< (longer[i] -'a'));
			}			
		}
	}
	
	public void sushu(int n ,int m){  
		//计算前26个，小写
		int k = 0;
		StringBuffer sb = new StringBuffer();
        for (int i = n; i < m && k<26; i++) {  
            int temp = (int)Math.sqrt(i);  
            if(i<=3){
            	sb.append(i).append(","); 
            }  
            for (int j = 2; j <= temp; j++) {  
                if(i%j==0){  
                    break;  
                }  
                if(j>=temp){  
                	sb.append(i).append(",");
                    k++;
                }  
            }             
        } 
        System.out.println(sb.toString());
    }  
	
	public static void main(String[] args) {
		String a = "aabbccabc";
		String b = "abc";
		StringContain sc = new StringContain();
		sc.setupMap(a.toCharArray());
		System.out.println(sc.include(b.toCharArray()));
		
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		long multi = 1;
		boolean include = true;

		for (int i=0;i<ac.length;i++) {
			multi *= prime[ac[i]-'a'];			
		}
		System.out.println(multi);
		
		for (int i=0;i<bc.length;i++) {
			if (multi % prime[bc[i]-'a'] == 0) {
				
			} else {
				include = false;
			}			
		}
		
		System.out.println("include: "+include);
		
		//a中消除b
		long multiB = 1;
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<bc.length;i++) {
			multiB *= prime[bc[i]-'a'];			
		}
		for (int i=0;i<ac.length;i++) {
			if (multiB % prime[ac[i]-'a'] == 0) {
				//说明a字符在b中
				//不增加
			} else {
				sb.append(ac[i]);
			}			
		}
		System.out.println(sb.toString());
		
		//a中包含b的最短字串
		//记录某个最近的位置
		//左指针左移的条件，1.左右指针之间的子串包含b，right再向右移动也不可能是最小的
		// 2. right当前指到的字符和left字符相同，则left位置多余了
		// 3. left不在b中
		// 4. left和right指针位置相同
		int left = 0;  
		int right = 0;
		int recordLeft = 0;
		int recordRight = 0;
		
		while (left<ac.length && right<ac.length) {
			long value = multiB;
			if (left == right) {
				right++;
				continue;
			}
			
			if (ac[left] == ac[right]) {
				left++;  //left,right指向同一个，则left左移
				right = left;
				continue;
			}
			
			if (value % prime[ac[left] - 'a'] != 0) {
				//left不在b中
				left++;
				right = left;
				continue;
			} else {
				// left在 b中，right > left
				value = value / prime[ac[left] - 'a'];				
				while (value != 1 && right <ac.length) {
					if (value % prime[ac[right] - 'a'] == 0) {
						value = value / prime[ac[right] - 'a'];
					}
					if (value !=1) {
						right++;
					}					
				}
				if (right <ac.length) {
					//有左，有右
					if (recordLeft == 0 || recordRight == 0) {
					recordLeft = left;
					recordRight = right;
					} else {
						if ((right - left) < (recordRight - recordLeft)) {
				    	recordLeft = left;
						recordRight = right;
						}
					}
				    left++;
				    right = left;
				}
			}
		}
		System.out.println("recordLeft:"+recordLeft+ " recordRight:"+recordRight);
		System.out.println(a);
		System.out.println(b);
		sb.delete(0, sb.length());
		for (int i=recordLeft;i<=recordRight;i++) {
			sb.append(ac[i]);
		}
		System.out.println(sb.toString());
		
		//sc.sushu(2,5000);
		
		//用bit数组的办法设置hashmap
		//把b放到数组里面
		String c = "abcdefghijklmndefab";
		String d = "def";
		sc.setupBitMap(d.toCharArray());
		char[] cc = c.toCharArray();
		//新的数组
		char[] e = new char[cc.length];
		//数组长度标识
		int len = 0;
		for (int i = 0;i<cc.length;i++) {
			if ((sc.bitMapCap & (1<< (cc[i] - 'A' ))) == 0 && (sc.bitMapNormal & (1<< (cc[i] - 'a' ))) == 0) {
				e[len] = cc[i];
				len++;
			}
		}
		
		System.out.println(String.copyValueOf(e, 0, len));
		
		
	}
}
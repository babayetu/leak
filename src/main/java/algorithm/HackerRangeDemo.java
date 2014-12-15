package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HackerRangeDemo {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        int diff = -1;
        int lastDiff = -1;
        
        String str = br.readLine();
	    String[] strArr = str.split(" ");
        for (int i=0;i<n;i++) {	       
	       arr[i] = Integer.parseInt(strArr[i]);
	       
           if (i>0) {
               diff = arr[i] - arr[i-1];
               if (lastDiff == -1) {
                   lastDiff = diff;
               } else {
                   if (lastDiff != diff) {
                       System.out.println(arr[i-1] + (arr[i] - arr[i-1]) / 2 );
                   }
               }
           } 
        }
    }
}
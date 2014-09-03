package test;

import java.util.ArrayList;
import java.util.List;

public class ListToString {
	public static void main(String[] args) {
		List<String> ls = new ArrayList<String>();
		ls.add("aa");
		ls.add("bb");
		ls.add("cc");
		
		StringBuffer sb = new StringBuffer();
    	sb.append("\nACCT_GUID_TRUSTED: ").append(ls.toString());
    	System.out.println(sb.toString());
	}
}
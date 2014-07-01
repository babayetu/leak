package javainterview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/** Checking Array for duplicate elements Java
 *  http://javarevisited.blogspot.sg/2012/02/how-to-check-or-detect-duplicate.html
 *  @author liukarl
 */

public class CheckArrayForDuplicate {
	
	/*
     * Since Set doesn't allow duplicates add() return false
     * if we try to add duplicates into Set and this property
     * can be used to check if array contains duplicates in Java
     */
	public static boolean IsDuplicateWithHashSetCheck(String[] input) {
		HashSet<String> hs = new HashSet<String>();
		boolean notFound = false;
		for (int i=0; i<input.length; i++) {
			notFound = hs.add(input[i]);  // return false if found
			if (!notFound) {
				return true;
			}
		}
		return false;
	}
	
	/*
     * detect duplicate in array by comparing size of List and Set
     * since Set doesn't contain duplicate, size must be less for an array which contains duplicates
     */
	public static boolean IsDuplicateWithListAndSetCompare(String[] input) {
		List<String> ls = Arrays.asList(input);
		Set<String> ss = new HashSet<String>(ls);
		if (ls.size() > ss.size()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String[] str = new String[] {"abc","efg","def","crt","abc","xyz"};
		System.out.println(IsDuplicateWithHashSetCheck(str));
		System.out.println(IsDuplicateWithListAndSetCompare(str));
	}
}
